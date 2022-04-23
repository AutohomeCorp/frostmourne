package com.autohome.frostmourne.core.log4j2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.CsvLogEventLayout;
import org.apache.logging.log4j.status.StatusLogger;

import com.google.common.base.Strings;

/**
 * Created by kcq on 2017/6/8.
 */
@Plugin(name = "Autolog4jCsvLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class Autolog4jCsvLayout extends CsvLogEventLayout {

    private static final List<String> defaultFieldNames = Arrays.asList(FieldName.LOG_AT, FieldName.LEVEL, FieldName.LOGGER, FieldName.LINE,
        FieldName.EXCEPTION_TYPE, FieldName.EXCEPTION_MESSAGE, FieldName.CUSTOM_MESSAGE, FieldName.CLASS_NAME, FieldName.METHOD_NAME, FieldName.METHOD_PARAMS,
        FieldName.TRACE_ID, FieldName.DEPARTMENT, FieldName.TEAM, FieldName.PROJECT, FieldName.APP_ID, FieldName.THREAD_ID, FieldName.THREAD_NAME,
        FieldName.HOST, FieldName.SERVER_IP, FieldName.URI_STEM, FieldName.QUERY_STRING, FieldName.FORM_STRING, FieldName.USER_AGENT, FieldName.STACK_TRACE);

    private String team;
    private String department;
    private String project;
    private String appId;

    private List<LayoutField> fields;
    private final FieldFactory fieldFactory;

    protected Autolog4jCsvLayout(Configuration config, Charset charset, CSVFormat csvFormat, String header, String footer, String department, String team,
        String project, String appId, FieldFactory fieldFactoryIn) {
        super(config, charset, csvFormat, header, footer);
        this.department = department;
        this.team = team;
        this.project = project;
        this.appId = appId;
        if (fieldFactoryIn == null) {
            this.fieldFactory = new DefaultLog4jFieldFactory();
        } else {
            this.fieldFactory = fieldFactoryIn;
        }
    }

    @PluginFactory
    public static Autolog4jCsvLayout createLayout(@PluginConfiguration final Configuration config,
        @PluginAttribute(value = "format", defaultString = DEFAULT_FORMAT) final String format,
        @PluginAttribute(value = "delimiter", defaultChar = '\t') final Character delimiter,
        @PluginAttribute(value = "escape", defaultChar = '\\') final Character escape,
        @PluginAttribute(value = "quote", defaultChar = '\"') final Character quote,
        @PluginAttribute(value = "quoteMode", defaultString = "ALL") final QuoteMode quoteMode,
        @PluginAttribute(value = "nullString", defaultString = "\"-\"") final String nullString,
        @PluginAttribute(value = "recordSeparator", defaultString = "\r\n") final String recordSeparator,
        @PluginAttribute(value = "charset", defaultString = DEFAULT_CHARSET) final Charset charset, @PluginAttribute("header") final String header,
        @PluginAttribute("footer") final String footer, @PluginAttribute("department") final String department, @PluginAttribute("team") final String team,
        @PluginAttribute("project") final String project, @PluginAttribute("appId") final String appId,
        @PluginElement("FieldFactory") final FieldFactory fieldFactory) {
        final CSVFormat csvFormat = createFormat(format, delimiter, escape, quote, quoteMode, nullString, recordSeparator);
        return new Autolog4jCsvLayout(config, charset, csvFormat, header, footer, department, team, project, appId, fieldFactory);
    }

    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilder buffer = getStringBuilder();
        final CSVFormat format = getFormat();
        if (this.fields == null || this.fields.size() == 0) {
            this.fields = getDefaultFields();
        }
        try {
            Iterator<LayoutField> fieldIterator = this.fields.iterator();
            if (fieldIterator.hasNext()) {
                format.print(handleEscapeChar(fieldIterator.next().format(event)), buffer, true);
                while (fieldIterator.hasNext()) {
                    format.print(handleEscapeChar(fieldIterator.next().format(event)), buffer, false);
                }
            }
            format.println(buffer);
            return buffer.toString();
        } catch (final IOException e) {
            StatusLogger.getLogger().error(event.toString(), e);
            return format.getCommentMarker() + " " + e;
        }
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private LayoutField createProjectField() {
        if (!Strings.isNullOrEmpty(this.project)) {
            this.project = this.project.toLowerCase();
        }
        return new LayoutField("Project", this.project);
    }

    private LayoutField createTeamField() {
        if (!Strings.isNullOrEmpty(this.team)) {
            this.team = this.team.toLowerCase();
        }
        return new LayoutField("Team", this.team);
    }

    private LayoutField createAppIdField() {
        return new LayoutField("HawkKey", this.appId);
    }

    private List<LayoutField> getDefaultFields() {
        List<LayoutField> fields = new ArrayList<>();

        for (String fieldName : defaultFieldNames) {
            switch (fieldName) {
                case FieldName.DEPARTMENT:
                    fields.add(createDepartmentField());
                    break;
                case FieldName.TEAM:
                    fields.add(createTeamField());
                    break;
                case FieldName.PROJECT:
                    fields.add(createProjectField());
                    break;
                case FieldName.APP_ID:
                    fields.add(createAppIdField());
                    break;
                default:
                    fields.add(this.fieldFactory.fetchField(fieldName));
                    break;
            }
        }

        return fields;
    }

    private String handleEscapeChar(String value) {
        if (Strings.isNullOrEmpty(value) || !value.contains("\\\"")) {
            return value;
        }

        return value.replace("\\\"", "\"");
    }

    private LayoutField createDepartmentField() {
        if (!Strings.isNullOrEmpty(this.department)) {
            this.department = this.department.toLowerCase();
        }
        return new LayoutField("Department", this.department);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
