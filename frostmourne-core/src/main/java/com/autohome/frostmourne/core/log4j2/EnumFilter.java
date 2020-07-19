package com.autohome.frostmourne.core.log4j2;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(name = "EnumFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE, printObject = true)
@PerformanceSensitive("allocation")
public class EnumFilter extends AbstractFilter {

    private final List<Level> allowLevelList;

    private EnumFilter(List<Level> allowLevelList) {
        super(Result.ACCEPT, Result.DENY);
        this.allowLevelList = allowLevelList;
    }

    @PluginFactory
    public static EnumFilter createFilter(
            @PluginAttribute("allowLevels") final String allowLevels) {
        List<Level> allowLevelList = Splitter.on(",").trimResults().splitToList(allowLevels)
                .stream().map(Level::getLevel).collect(Collectors.toList());
        return new EnumFilter(allowLevelList);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
                         final Object... params) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
                         final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
                         final Throwable t) {
        return filter(level);
    }

    @Override
    public Result filter(final LogEvent event) {
        return filter(event.getLevel());
    }

    private Result filter(final Level level) {
        if(allowLevelList.contains(Level.OFF)) {
            return onMismatch;
        }
        if (allowLevelList.contains(level)) {
            return onMatch;
        }
        return onMismatch;
    }
}
