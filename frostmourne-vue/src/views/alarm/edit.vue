<template>
  <div class="app-container">
    <el-form ref="form" size="medium" label-position="right" :model="form" :rules="rules" label-width="120px">
      <el-tabs>
        <el-tab-pane :label="$t('alarm.edit.label_basic')">
          <el-row>
            <el-col :span="12">
              <el-form-item :label="$t('alarm.edit.label_alarm_name') + ':'" prop="alarmName">
                <el-input v-model="form.alarmName"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-switch v-model="form.status" active-value="OPEN" :active-text="$t('alarm.edit.label_status_open')" inactive-value="CLOSE"
                           :inactive-text="$t('alarm.edit.label_status_close')"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label="$t('alarm.edit.label_service') + ':'">
                <el-select v-model="form.serviceInfo.id" reserve-keyword :placeholder="$t('alarm.edit.input_service')">
                  <el-option v-for="item in serviceOptions" :key="item.id" :label="item.serviceName" :value="item.id"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('alarm.edit.label_risk') + ':'">
                <el-select v-model="form.riskLevel" style="width:100px" :placeholder="$t('alarm.edit.label_risk')">
                  <el-option :label="$t('alarm.edit.label_info')" value="info"/>
                  <el-option :label="$t('alarm.edit.label_important')" value="important"/>
                  <el-option :label="$t('alarm.edit.label_emergency')" value="emergency"/>
                  <el-option :label="$t('alarm.edit.label_crash')" value="crash"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item :label="$t('alarm.edit.label_owner') +  ':'">
                <el-input v-model="form.ownerKey" :placeholder="$t('alarm.edit.label_owner_placeholder')"/>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('alarm.edit.label_team') + ':'" prop="teamName">
                <el-select v-model="form.teamName" :placeholder="$t('alarm.edit.label_team_placeholder')">
                  <el-option v-for="item in teamList" :key="item.name" :label="item.fullName" :value="item.name"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item :label="$t('alarm.edit.label_description') + ':'" prop="description">
                <el-input v-model="form.description" type="textarea"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

      <el-tabs>
        <el-tab-pane :label="$t('alarm.edit.label_data_config')">
          <el-row>
            <el-col :span="6">
              <el-form-item :label="$t('alarm.edit.label_data') + ':' " prop="metricContract.dataName">
                <el-cascader v-model="dataValue" size="medium" :show-all-levels="false" :options="dataOptions"
                             @change="dataChange"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch'" :label="$t('alarm.edit.label_aggregation_type') + ':' ">
                <el-select v-model="form.metricContract.aggregationType">
                  <el-option label="count" value="count"/>
                  <el-option label="avg" value="avg"/>
                  <el-option label="min" value="min"/>
                  <el-option label="max" value="max"/>
                  <el-option label="sum" value="sum"/>
                  <el-option label="unique count" value="cardinality"/>
                  <el-option label="standard deviation" value="standard_deviation"/>
                  <el-option label="percentiles" value="percentiles"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item
                  v-if="dataSourceType === 'elasticsearch' && form.metricContract.aggregationType === 'percentiles'"
                  :label="$t('alarm.edit.label_pecentile') + ':'">
                <el-input v-model="form.metricContract.properties.percent" :placeholder="$t('alarm.edit.label_percentile_placeholder')"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch' && form.metricContract.aggregationType !== 'count'"
                            :label="$t('alarm.edit.label_aggregation_field') + ':'">
                <el-autocomplete
                    v-model="form.metricContract.aggregationField"
                    class="inline-input"
                    :fetch-suggestions="searchElasticsearchFields"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch'" :label="$t('alarm.edit.label_bucket_type') + ':'">
                <el-select v-model="form.metricContract.bucketType">
                  <el-option label="none" value="none"/>
                  <el-option label="terms" value="terms"/>
                  <el-option label="date_histogram" value="date_histogram"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch' && form.metricContract.bucketType !== 'none'"
                            :label="$t('alarm.edit.label_bucket_field') + ':'">
                <el-autocomplete
                    v-model="form.metricContract.bucketField"
                    class="inline-input"
                    :fetch-suggestions="searchElasticsearchFields"/>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item
                  v-if="dataSourceType === 'elasticsearch' && form.metricContract.bucketType === 'date_histogram'"
                  :label="$t('alarm.edit.label_time_interval') + ':'">
                <el-select v-model="form.metricContract.properties.dateHistogramInterval">
                  <el-option :label="$t('alarm.edit.label_hour')" value="3600000"/>
                  <el-option :label="$t('alarm.edit.label_day')" value="86400000"/>
                  <el-option :label="$t('alarm.edit.label_minute')" value="60000"/>
                  <el-option :label="$t('alarm.edit.label_half_hour')" value="1800000"/>
                  <el-option :label="$t('alarm.edit.label_five_minute')" value="300000"/>
                  <el-option :label="$t('alarm.edit.label_week')" value="604800000"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item :label="$t('alarm.edit.label_query') + ':'" prop="metricContract.queryString">
            <el-input v-model="form.metricContract.queryString" type="textarea" rows="3"/>
          </el-form-item>
          <el-form-item v-if="dataSourceType === 'http'" :label="$t('alarm.edit.label_http_header') + ':'">
            <template v-if="httpHeaders.length === 0">
              <el-button type="primary" @click="addHeader">+</el-button>
            </template>
            <template v-else>
              <el-row v-for="(item, index) in httpHeaders" :key="'header-'+index">
                <el-input v-model="item.key" clearable style="width:160px;"/>
                =
                <el-input v-model="item.value" clearable style="width:300px;"/>
                <el-link :underline="false" icon="el-icon-delete" @click="removeHeader(index)"/>
                <el-link :underline="false" icon="el-icon-plus" @click="addHeader"/>
              </el-row>
            </template>
          </el-form-item>
          <el-form-item v-if="dataSourceType === 'http'" :label="$t('alarm.edit.label_post_data') + ':'">
            <el-input v-model="form.metricContract.postData" type="textarea"/>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane :label="$t('alarm.edit.label_alarm_rule')">
          <el-form-item :label="$t('alarm.edit.label_judge_type') + ':'" prop="metricContract.metricType">
            <el-select v-model="form.metricContract.metricType" @change="metricTypeChangeHandler">
              <el-option
                  v-if="dataSourceType !== 'http' && dataSourceType !== 'ping' && dataSourceType !== 'prometheus' && dataSourceType !== 'iotdb' && dataSourceType !== 'telnet'"
                  :label="$t('alarm.edit.label_number_compare')" value="numeric"/>
              <el-option
                  v-if="dataSourceType === 'http' || dataSourceType === 'mysql' || dataSourceType === 'clickhouse' || dataSourceType === 'prometheus' || 
                  dataSourceType === 'iotdb' || dataSourceType === 'sqlserver'"
                  :label="$t('alarm.edit.label_javascript_expression')" value="object"/>
              <el-option v-if="dataSourceType === 'elasticsearch' || dataSourceType === 'influxdb'" :label="$t('alarm.edit.label_ring_compare')" value="ring_compare"/>
              <el-option
                  v-if="dataSourceType !== 'http' && dataSourceType !== 'ping' && dataSourceType !== 'prometheus' && dataSourceType !== 'iotdb' && dataSourceType !== 'telnet'"
                  :label="$t('alarm.edit.label_same_time_compare')" value="same_time"/>
              <el-option v-if="dataSourceType === 'elasticsearch' && form.metricContract.bucketType !== 'none'"
                         :label="$t('alarm.edit.label_bucket_number_compare')" value="bucket_numeric"/>
              <el-option v-if="dataSourceType === 'ping'" label="ping" value="ping"/>
              <el-option v-if="dataSourceType === 'telnet'" label="telnet" value="telnet"/>
            </el-select>
          </el-form-item>
          <el-row>
            <el-form-item
                v-if="form.metricContract.metricType === 'numeric' || form.metricContract.metricType === 'bucket_numeric'"
                :label=" $t('alarm.edit.label_judge_rule') + ':'">
              {{ $t('alarm.edit.label_recent') }}
              <el-input-number v-model="form.ruleContract.settings.TIME_WINDOW" size="small" :min="1" label="minute"/>
              {{ $t('alarm.edit.text_minutes_metric_value') }}
              <el-select v-model="form.ruleContract.settings.OPERATOR" size="small" style="width:100px"
                         :placeholder="$t('alarm.edit.label_compare_operation')">
                <el-option label=">=" value="GTE"/>
                <el-option label="<=" value="LTE"/>
                <el-option label="==" value="EQUAL"/>
              </el-select>
              <el-input-number v-model="form.ruleContract.settings.THRESHOLD" :precision="2" size="small" :label="$t('alarm.edit.label_threshold')"/>
            </el-form-item>
          </el-row>
          <el-form-item v-if="form.metricContract.metricType === 'object'" :label="$t('alarm.edit.label_expression') + ':'">
            <el-input v-model="form.ruleContract.settings.EXPRESSION" type="textarea"/>
          </el-form-item>
          <el-form-item v-if="form.metricContract.metricType === 'ring_compare'" :label="$t('alarm.edit.label_judge_rule') + ':'">
            <el-select v-model="form.ruleContract.settings.PERIOD_UNIT">
              <el-option :label="$t('alarm.edit.label_week')" value="week"/>
              <el-option :label="$t('alarm.edit.label_day')" value="day"/>
              <el-option :label="$t('alarm.edit.label_hour')" value="hour"/>
              <el-option :label="$t('alarm.edit.label_minute')" value="minute"/>
            </el-select>
            {{ $t('alarm.edit.label_ring_compare') }}
            <el-select v-model="form.ruleContract.settings.COMPARE_TYPE">
              <el-option :label="$t('alarm.edit.label_increase')" value="increase"/>
              <el-option :label="$t('alarm.edit.label_decrease')" value="decrease"/>
              <el-option :label="$t('alarm.edit.label_increase_or_decrease')" value="both"/>
            </el-select>
            {{ $t('alarm.edit.text_greater_percent') }}
            <el-input v-model="form.ruleContract.settings.PERCENTAGE_THRESHOLD" style="width: 150px"/>
            {{ $t('alarm.edit.text_and_diff') }}
            <el-select v-model="form.ruleContract.settings.DIFF_COMPARE_TYPE">
              <el-option :label="$t('alarm.edit.label_absolute') + ' >='" value="ABS_GTE"/>
              <el-option :label="$t('alarm.edit.label_absolute') + ' <='" value="ABS_LTE"/>
              <el-option label=">=" value="GTE"/>
              <el-option label="<=" value="LTE"/>
            </el-select>
            <el-input v-model="form.ruleContract.settings.DIFF_VALUE_THRESHOLD" style="width: 100px"/>
          </el-form-item>
          <el-form-item v-if="form.metricContract.metricType === 'same_time'" :label="$t('alarm.edit.label_judge_rule') + ':'">
            <el-select v-model="form.ruleContract.settings.PERIOD_UNIT">
              <el-option :label="$t('alarm.edit.label_hour')" value="hour"/>
              <el-option :label="$t('alarm.edit.label_day')" value="day"/>
            </el-select>
            {{ $t('alarm.edit.label_same_time_compare') }}
            <el-select v-model="form.ruleContract.settings.REFERENCE_TYPE_LIST">
              <el-option :label="$t('alarm.edit.label_yesterday')" value="day"/>
              <el-option :label="$t('alarm.edit.label_last_week')" value="week"/>
              <el-option :label="$t('alarm.edit.label_last_month')" value="month"/>
              <el-option :label="$t('alarm.edit.label_yesterday_and_last_week')" value="day,week"/>
            </el-select>
            <el-select v-model="form.ruleContract.settings.COMPARE_TYPE">
              <el-option :label="$t('alarm.edit.label_increase')" value="increase"/>
              <el-option :label="$t('alarm.edit.label_decrease')" value="decrease"/>
              <el-option :label="$t('alarm.edit.label_increase_or_decrease')" value="both"/>
            </el-select>
            {{ $t('alarm.edit.text_greater_percent') }}
            <el-input v-model="form.ruleContract.settings.PERCENTAGE_THRESHOLD" style="width: 150px"/>
            {{ $t('alarm.edit.text_and_diff') }}
            <el-select v-model="form.ruleContract.settings.DIFF_COMPARE_TYPE">
              <el-option :label="$t('alarm.edit.label_absolute') + '>='" value="ABS_GTE"/>
              <el-option :label="$t('alarm.edit.label_absolute') + '<='" value="ABS_LTE"/>
              <el-option label=">=" value="GTE"/>
              <el-option label="<=" value="LTE"/>
            </el-select>
            <el-input v-model="form.ruleContract.settings.DIFF_VALUE_THRESHOLD" style="width: 100px"/>
          </el-form-item>
          <el-row>
            <el-form-item :label="$t('alarm.edit.label_alert_condition') + ':'">
              {{ $t('alarm.edit.text_continuous') }}
              <el-input-number v-model="form.ruleContract.settings.ALERT_CONDITION" size="small" :precision="0" :min="1"/>
              {{ $t('alarm.edit.text_times_meet_rule_begin_alert') }}
            </el-form-item>
          </el-row>
        </el-tab-pane>
        <el-tab-pane :label="$t('alarm.edit.label_message_template')">
          <el-form-item>
            <el-select v-model.number="alertTemplateId" filterable placeholder="请选择"
                       @change="changeAlertTemplateOptions()">
              <el-option v-for="item in alertTemplateOptions"
                         :key="item.id" :label="item.templateName" :value="item.id"/>
            </el-select>
            <el-button type="primary" :disabled="alertTemplateOption==null" @click="importAlertTemplate">{{ $t('alarm.edit.text_import_template') }}
            </el-button>
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_message_type') + ':'" prop="ruleContract.alertTemplateType">
            <el-select v-model="form.ruleContract.alertTemplateType" @change="alertTemplateTypeChangeHandler">
              <el-option label="text" value="TEXT"/>
              <el-option label="markdown" value="MARKDOWN"/>
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_template_content') + ':'" prop="ruleContract.alertTemplate">
            <el-input v-model="form.ruleContract.alertTemplate" type="textarea" rows="5"/>
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_custom_link') + ':'">
            <el-input v-model="form.metricContract.properties.dataLink" placeholder="optional"/>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-button type="primary" @click="onPreview">{{ $t('alarm.edit.text_preview_data') }}</el-button>
      <el-tabs>
        <el-tab-pane :label="$t('alarm.edit.label_message_deliver')">
          <el-row>
            <el-col :span="8">
              <el-form-item :label="$t('alarm.edit.label_message_way') + ':'" prop="alertContract.ways">
                <el-checkbox-group v-model="form.alertContract.ways" size="small">
                  <el-checkbox-button label="dingding">{{ $t('alarm.edit.text_dingding') }}</el-checkbox-button>
                  <el-checkbox-button label="feishu">{{ $t('alarm.edit.text_feishu') }}</el-checkbox-button>
                  <el-checkbox-button label="wechat">{{ $t('alarm.edit.text_wechat') }}</el-checkbox-button>
                  <el-checkbox-button label="email">Email</el-checkbox-button>
                  <el-checkbox-button label="sms">{{ $t('alarm.edit.text_sms') }}</el-checkbox-button>
                  <el-checkbox-button label="http_post">HTTP</el-checkbox-button>
                  <el-checkbox-button label="one_message">OneMessage</el-checkbox-button>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item :label="$t('alarm.edit.label_recover_notice') + ':'">
                <el-switch v-model="form.recoverNoticeStatus" active-value="OPEN" :active-text="$t('alarm.edit.label_status_open')"
                           inactive-value="CLOSE" :inactive-text="$t('alarm.edit.label_status_close')"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item v-if="form.alertContract.ways.includes('dingding')" :label="$t('alarm.edit.label_dingtalk_robot') + ':'">
            <el-input v-model="form.alertContract.dingRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('wechat')" :label="$t('alarm.edit.label_wechat_robot') + ':'">
            <el-input v-model="form.alertContract.wechatRobotHook" size="small" placeholder="optional"/>
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('http_post')" prop="alertContract.httpPostUrl"
                        :label="$t('alarm.edit.label_http_addr') + ':'">
            <el-input v-model="form.alertContract.httpPostUrl" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('feishu')" prop="alertContract.feishuRobotHook"
                        :label="$t('alarm.edit.label_feishu_robot') + ':'">
            <el-input v-model="form.alertContract.feishuRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('one_message')" prop="alertContract.oneMessageRobotHook"
                        label="OneMessage Robot:">
            <el-input v-model="form.alertContract.oneMessageRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-row>
            <el-col :span="8">
              <el-form-item :label="$t('alarm.edit.label_silence_time') + ':'">
                <el-input-number v-model="form.alertContract.silence" size="small" :min="0" :label="$t('alarm.edit.label_silence_time')"/>
                {{ $t('alarm.edit.label_minute') }}
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item :label="$t('alarm.edit.label_silence_rule') + ':'" prop="alertContract.silenceExpression">
                <span slot="label">
                  <el-tooltip class="item" effect="light" placement="right-start">
                    <div slot="content">
                      说明：字段取值参考JsonPath语法规则，多个字段判断支持使用逻辑运算符 '&&'，'||' 和 '()'<br/><br/>
                      举例：<br/>
                      &nbsp;&nbsp;&nbsp;&nbsp;1、日志链路和日志堆栈字段有一个值相同则静默：$.TraceId || $.StackTrace<br/>
                      &nbsp;&nbsp;&nbsp;&nbsp;2、数据字段A和数据字段B的值都相同则静默：$.A && $.B<br/>
                      &nbsp;&nbsp;&nbsp;&nbsp;3、如果字段A是个数组，取其下标2的字段：$.A[2]<br/><br/>
                      注：非必填，为空则默认静默时间内只会报警一次<br/>
                    </div>
                    <i class="el-icon-question"></i>
                  </el-tooltip>
                  {{ $t('alarm.edit.text_silence_rule') }}:
                </span>
                <el-input v-model="form.alertContract.silenceExpression"
                          placeholder="字段取值参考JsonPath语法规则，多个字段判断支持使用逻辑运算符 '&&'，'||' 和 '()'"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item :label="$t('alarm.edit.label_message_receiver') + ':'" prop="alertContract.recipients">
            <el-select v-model="form.alertContract.recipients" style="width:100%;" multiple filterable remote
                        :remote-method="findRecipient" :loading="loading">
              <el-option v-for="item in recipientList" :key="item.account" :label="item.account" :value="item.account"/>
            </el-select>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane :label="$t('alarm.edit.label_alarm_upgrade')">
          <el-form-item :label="$t('alarm.edit.label_alarm_upgrade_switch') + ':'">
            <el-switch v-model="form.alertUpgradeContract.status" active-value="OPEN" :active-text="$t('alarm.edit.label_status_open')"
                       inactive-value="CLOSE" :inactive-text="$t('alarm.edit.label_status_close')"/>
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_upgrade_rule') + ':'" prop="alertUpgradeContract.timesToUpgrade">
            {{ $t('alarm.edit.text_continuous_alert') }}
            <el-input-number v-model="form.alertUpgradeContract.timesToUpgrade" size="small" :precision="0" :min="2"
                             :label="$t('alarm.edit.label_upgrade_rule')"/>
            {{ $t('alarm.edit.text_times_then_upgrade') }}
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_message_way') + ':'" prop="alertUpgradeContract.ways">
            <el-checkbox-group v-model="form.alertUpgradeContract.ways" size="small">
              <el-checkbox-button label="dingding">{{ $t('alarm.edit.text_dingding') }}</el-checkbox-button>
              <el-checkbox-button label="feishu">{{ $t('alarm.edit.text_feishu') }}</el-checkbox-button>
              <el-checkbox-button label="wechat">{{ $t('alarm.edit.text_wechat') }}</el-checkbox-button>
              <el-checkbox-button label="email">Email</el-checkbox-button>
              <el-checkbox-button label="sms">{{ $t('alarm.edit.text_sms') }}</el-checkbox-button>
              <el-checkbox-button label="http_post">HTTP</el-checkbox-button>
              <el-checkbox-button label="one_message">OneMessage</el-checkbox-button>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item
              v-if="form.alertUpgradeContract.ways != null && form.alertUpgradeContract.ways.includes('dingding')"
              :label="$t('alarm.edit.label_dingtalk_robot') + ':'">
            <el-input v-model="form.alertUpgradeContract.dingRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item
              v-if="form.alertUpgradeContract.ways != null && form.alertUpgradeContract.ways.includes('wechat')"
              :label="$t('alarm.edit.label_wechat_robot') + ':'">
            <el-input v-model="form.alertUpgradeContract.wechatRobotHook" size="small" placeholder="optional"/>
          </el-form-item>
          <el-form-item
              v-if="form.alertUpgradeContract.ways != null && form.alertUpgradeContract.ways.includes('http_post')"
              prop="alertUpgradeContract.httpPostUrl" :label="$t('alarm.edit.label_http_addr') + ':'">
            <el-input v-model="form.alertUpgradeContract.httpPostUrl" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item
              v-if="form.alertUpgradeContract.ways != null && form.alertUpgradeContract.ways.includes('feishu')"
              prop="alertUpgradeContract.feishuRobotHook" :label="$t('alarm.edit.label_feishu_robot') + ':'">
            <el-input v-model="form.alertUpgradeContract.feishuRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item
              v-if="form.alertUpgradeContract.ways != null && form.alertUpgradeContract.ways.includes('one_message')"
              prop="alertUpgradeContract.oneMessageRobotHook" label="OneMessage Robot:">
            <el-input v-model="form.alertUpgradeContract.oneMessageRobotHook" size="small" placeholder="required"/>
          </el-form-item>
          <el-form-item :label="$t('alarm.edit.label_message_receiver') + ':'" prop="alertUpgradeContract.recipients">
            <el-select v-model="form.alertUpgradeContract.recipients" style="width:100%;" multiple filterable remote
                        :remote-method="findRecipient" :loading="loading">
              <el-option v-for="item in recipientList" :key="item.account" :label="item.account" :value="item.account"/>
            </el-select>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <el-tabs>
        <el-tab-pane :label="$t('alarm.edit.label_schedule_config')">
          <el-row>
            <el-col :span="8">
              <el-form-item :label="$t('alarm.edit.label_every') + ':'">
                <el-select v-model="intervalCron" @change="intervalChangeHandler">
                  <el-option :label="'1 ' + $t('alarm.edit.label_minute')" value="0/1 * * * ?"/>
                  <el-option :label="'2 ' + $t('alarm.edit.label_minute')" value="0/2 * * * ?"/>
                  <el-option :label="'3 ' + $t('alarm.edit.label_minute')" value="0/3 * * * ?"/>
                  <el-option :label="'5 ' + $t('alarm.edit.label_minute')" value="0/5 * * * ?"/>
                  <el-option :label="'10 ' + $t('alarm.edit.label_minute')" value="0/10 * * * ?"/>
                  <el-option :label="'15 ' + $t('alarm.edit.label_minute')" value="0/15 * * * ?"/>
                  <el-option :label="'30 ' + $t('alarm.edit.label_minute')" value="0/30 * * * ?"/>
                  <el-option :label="'1 ' + $t('alarm.edit.label_hour')" value="0 0/1 * * ?"/>
                  <el-option :label="'2 ' + $t('alarm.edit.label_hour')" value="0 0/2 * * ?"/>
                  <el-option :label="'6 ' + $t('alarm.edit.label_hour')" value="0 0/6 * * ?"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="3">{{ $t('alarm.edit.text_or') }}</el-col>
            <el-col :span="8">
              <el-form-item :label="$t('alarm.edit.label_each_day') + ':'">
                <el-select v-model="dayCron" @change="dayCronChangeHandler">
                  <el-option v-for="item in dayCronOptions" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item :label="$t('alarm.edit.label_cron_expression') + ':'" prop="cron">
            <el-input v-model="form.cron"/>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <el-form-item style="margin-top: 20px;">
        <el-button type="primary" :disabled="disableSave" @click="onSubmit">{{ $t('buttons.save') }}</el-button>
        <el-button type="primary" @click="onTest">{{ $t('buttons.test') }}</el-button>
        <el-button v-if="enableSaveAnother" type="primary" @click="onSaveAnother">{{ $t('buttons.save_another') }}</el-button>
        <el-button @click="onCancel">{{ $t('buttons.cancel') }}</el-button>
      </el-form-item>
    </el-form>

    <el-dialog :title="$t('alarm.edit.title_response_data')" :visible.sync="previewResponseDialogVisible" style="word-break: normal">
      <div>
        <vue-json-pretty :data="previewResponseData"/>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import alarmApi from '@/api/alarm.js'
import adminApi from '@/api/admin.js'
import {teams, search} from '@/api/user'
import dataApi from '@/api/data.js'
import alerttemplateApi from '@/api/alert-template.js'
import serviceinfoApi from '@/api/service-info.js'
import dataQueryApi from '@/api/data-query.js'
import validator from 'validator';

import VueJsonPretty from 'vue-json-pretty'
import 'vue-json-pretty/lib/styles.css'

export default {
  components: {
    VueJsonPretty
  },
  data() {
    const validatorSilenceExpression = (rule, value, callback) => {
      if (value !== null && value !== undefined && value !== '') {
        if (value.includes('（') || value.includes('）')) {
          callback(new Error('请使用英文括号'))
        }
        if (value.includes('｜')) {
          callback(new Error('或语法请使用英文双管道符号 \'||\''))
        }
        if (value.startsWith(' ') || value.endsWith(' ')) {
          callback(new Error('表达式不能以空格开头或结尾'))
        }
        const pattern = new RegExp('^[(]*[^&|]+[)]*(\\s{1}(&&|\\|\\|)\\s+[(]*[^&|]+[)]*)*$')
        if (!pattern.test(value)) {
          callback(new Error('逻辑表达式语法错误'))
        }
      }
      callback()
    }
    const validatorAlertUpgrade = (rule, value, callback) => {
      if (this.form.alertUpgradeContract.status === 'OPEN') {
        if (value === undefined) {
          callback(new Error('请设置规则'))
        }
      }
      callback()
    }
    const validatorAlertUpgradeWays = (rule, value, callback) => {
      if (this.form.alertUpgradeContract.status === 'OPEN') {
        if (value === undefined || value.length === 0) {
          callback(new Error('请至少选择一种报警方式'))
        }
      }
      callback()
    }
    const validatorAlertFeishuRobotHook = (rule, value, callback) => {
      if (this.form.alertContract.ways.includes('feishu') && (this.form.alertContract.feishuRobotHook === undefined || this.form.alertContract.feishuRobotHook.trim() === '')) {
        callback(new Error('飞书机器人地址不能为空'))
      }
      callback()
    }
    const validatorAlertHttpPostUrl = (rule, value, callback) => {
	const url = this.form.alertContract.httpPostUrl
      if (this.form.alertContract.ways.includes('http_post') 
	  && !validator.isURL(this.form.alertContract.httpPostUrl)) {
        callback(new Error('HTTP URL格式异常'))
      }
      callback()
    }
    const validatorAlertUpgradeFeishuRobotHook = (rule, value, callback) => {
      if (this.form.alertUpgradeContract.status === 'OPEN') {
        if (this.form.alertUpgradeContract.ways.includes('feishu') && (this.form.alertUpgradeContract.feishuRobotHook === undefined || this.form.alertContract.feishuRobotHook.trim() === '')) {
          callback(new Error('飞书机器人地址不能为空'))
        }
      }
      callback()
    }
    const validatorAlertUpgradeHttpPostUrl = (rule, value, callback) => {
      if (this.form.alertUpgradeContract.status === 'OPEN') {
        if (this.form.alertUpgradeContract.ways.includes('http_post') 
            && !validator.isURL(this.form.alertUpgradeContract.httpPostUrl)) {
          callback(new Error('HTTP URL格式异常'))
        }
      }
      callback()
    }
    const validatorAlertUpgradeRecipients = (rule, value, callback) => {
      if (this.form.alertUpgradeContract.status === 'OPEN') {
        if (this.form.alertUpgradeContract.recipients === undefined || this.form.alertUpgradeContract.recipients.length === 0) {
          callback(new Error('请配置报警接收人'))
        }
      }
      callback()
    }
    return {
      referer: null,
      intervalCron: '',
      dayCron: '',
      dayCronOptions: [],
      recipient: {
        value: '',
        visible: false
      },
      disableSave: false,
      activeName: 'datasource_tab',
      id: this.$route.query.id,
      previewResponseData: {},
      previewResponseDialogVisible: false,
      httpHeaders: [],
      loading: false,
      recipientList: [],
      form: {
        alarmName: '',
        ownerKey: '',
        status: 'OPEN',
        recoverNoticeStatus: 'CLOSE',
        teamName: '',
        description: '',
        cron: '',
        metricContract: {
          queryString: '',
          postData: null,
          aggregationType: '',
          aggregationField: '',
          metricType: '',
          dataSourceId: 0,
          dataNameId: 0,
          dataName: '',
          properties: {},
          bucketType: 'none',
          bucketField: ''
        },
        ruleContract: {
          ruleType: '',
          alertTemplateType: 'TEXT',
          alertTemplate: '${ALERT_SILENCE}分钟内连续报警将不重复发送\r\n' +
              '${Project}最近${TIME_WINDOW}分钟内有异常日志${NUMBER}条。最近一条异常信息:\r\n' +
              '服务器IP: ${ServerIP}\r\n' +
              '异常类型: ${ExceptionType}\r\n' +
              '自定义信息: ${CustomMessage}\r\n' +
              '异常信息: ${ExceptionMessage}',
          settings: {
            ALERT_CONDITION: 1
          }
        },
        alertContract: {
          ways: [],
          recipients: [],
          silence: 60,
          silenceExpression: ''
        },
        alertUpgradeContract: {
          status: 'CLOSE',
          timesToUpgrade: 5,
          ways: [],
          recipients: []
        },
        serviceInfo: {
          id: 0
        }
      },
      rules: {
        alarmName: [
          {required: true, message: '请输入监控名称', trigger: 'blur'}
        ],
        teamName: [
          {required: true, message: '请选择团队', trigger: 'change'}
        ],
        description: [
          {required: true, message: '请输入描述', trigger: 'blur'}
        ],
        'metricContract.dataName': [
          {required: true, message: '请选择数据', trigger: 'change'}
        ],
        'metricContract.queryString': [
          {required: true, message: '请输入查询语句', trigger: 'blur'}
        ],
        'metricContract.metricType': [
          {required: true, message: '请选择判断类型', trigger: 'change'}
        ],
        'ruleContract.alertTemplateType': [
          {required: true, message: '请选择消息类型', trigger: 'blur'}
        ],
        'ruleContract.alertTemplate': [
          {required: true, message: '请输入消息模板', trigger: 'blur'}
        ],
        'alertContract.ways': [
          {type: 'array', required: true, message: '请至少选择一种报警方式', trigger: 'change'}
        ],
        'alertContract.silenceExpression': [
          {validator: validatorSilenceExpression, trigger: 'blur'},
          {max: 500, message: '长度限制在500个字符以内', trigger: 'blur'}
        ],
        'alertContract.recipients': [
          {type: 'array', required: true, message: '请配置报警接收人', trigger: 'blur'}
        ],
        'alertUpgradeContract.timesToUpgrade': [
          {validator: validatorAlertUpgrade, trigger: 'blur'}
        ],
        'alertContract.feishuRobotHook': [
          {type: 'array', validator: validatorAlertFeishuRobotHook, trigger: 'blur'}
        ],
        'alertContract.httpPostUrl': [
          {type: 'array', validator: validatorAlertHttpPostUrl, trigger: 'blur'}
        ],
        'alertUpgradeContract.ways': [
          {type: 'array', validator: validatorAlertUpgradeWays, trigger: 'blur'}
        ],
        'alertUpgradeContract.feishuRobotHook': [
          {type: 'array', validator: validatorAlertUpgradeFeishuRobotHook, trigger: 'blur'}
        ],
        'alertUpgradeContract.httpPostUrl': [
          {type: 'array', validator: validatorAlertUpgradeHttpPostUrl, trigger: 'blur'}
        ],
        'alertUpgradeContract.recipients': [
          {type: 'array', validator: validatorAlertUpgradeRecipients, trigger: 'blur'}
        ],
        cron: [
          {required: true, message: '请输入cron表达式', trigger: 'blur'}
        ]
      },
      dataSourceType: '',
      dataValue: [],
      dataOptions: [],
      teamList: [],
      enableSaveAnother: true,
      alertTemplateOptions: [],
      alertTemplateOption: null,
      alertTemplateId: null,
      serviceOptionsLoading: false,
      serviceOptions: [],
      aggregationFields: []
    }
  },
  beforeRouteEnter(to, from, next) {
    next(
        vm => {
          vm.referer = from
        }
    )
  },
  mounted() {
    this.initDayCronOptions()
    if (this.id) {
      this.getDetail(this.initDataOptions)
      this.enableSaveAnother = true
    } else {
      this.enableSaveAnother = false
      this.initDataOptions()
    }

    teams().then(response => {
      this.teamList = response.result
    })

    search('').then(response => {
      this.recipientList = response.result
    })

    if (this.$route.query.dataName) {
      this.dataValue.push(this.$route.query.datasourceType)
      this.dataValue.push(this.$route.query.datasourceId)
      this.dataValue.push(this.$route.query.dataName)
      this.dataSourceType = this.$route.query.datasourceType
      this.form.metricContract.queryString = this.$route.query.queryString
      this.dataChange(this.dataValue)
    } else {
      this.initAlertTemplateOptions()
    }

    this.loadServiceOptions()
  },
  methods: {
    goBack() {
      if (this.referer) {
        this.$router.back()
      } else {
        this.$router.push({path: '/alarm/list.view'})
      }
    },
    success(message) {
      this.$message({
        type: 'success',
        message: message,
        duration: 500,
        onClose: () => this.goBack()
      })
    },
    onSubmit() {
      this.$refs['form'].validate((validate) => {
        if (validate) {
          this.disableSave = false
          this.copyToProperties()

          alarmApi.test(this.form)
              .then(response => {
                // 测试通过
                adminApi.save(this.form)
                    .then(response => this.success('保存成功！'))
                    .catch(error => {
                      this.$message({
                        type: 'success',
                        message: '保存失败: ' + error,
                        duration: 500
                      })
                    })
              })
              .catch(error => {
                console.log('测试运行失败: ' + error)
                this.$message({
                  type: 'success',
                  message: '测试运行失败',
                  duration: 1500
                })
              })
        } else {
          return false
        }
      })
    },
    onPreview() {
      if (this.form.metricContract.dataName == null) {
        this.$message({type: 'warning', message: '请先选择一个数据名', duration: 2000})
        return
      }
      if (this.form.metricContract.queryString == null || this.form.metricContract.queryString === '') {
        this.$message({type: 'warning', message: '查询语句不能为空', duration: 2000})
        return
      }
      if (this.form.dataSourceType !== 'http' && (this.form.metricContract.metricType == null || this.form.metricContract.metricType === '')) {
        this.$message({type: 'warning', message: '请先设置报警规则', duration: 2000})
        return
      }
      this.copyToProperties()
      alarmApi.previewData(this.form).then(response => {
        this.previewResponseData = response.result
        this.previewResponseDialogVisible = true
      })
    },
    onSaveAnother() {
      this.copyToProperties()
      this.form.alarmName = this.form.alarmName + '(copy)'
      adminApi.saveAnother(this.form)
          .then(response => this.success('另存成功！'))
          .catch(error => {
            console.log('另存失败:', error)
          })
    },
    removeHeader(index) {
      if (index > -1) {
        this.httpHeaders.splice(index, 1)
      }
      console.log('addHeader.headers -> ', this.httpHeaders)
    },
    addHeader() {
      this.httpHeaders.push({key: '', value: ''})
      console.log('addHeader.headers -> ' + this.httpHeaders.length, this.httpHeaders)
    },
    copyToProperties() {
      if (this.dataSourceType !== 'http') {
        return
      }
      this.form.metricContract.properties = {}
      this.httpHeaders.forEach(item => {
        if (item.key !== '' && item.value !== '') {
          this.form.metricContract.properties[item.key] = item.value
        }
      })
      console.log('properties -> ', this.form.metricContract.properties)
    },
    copyToHeaders(properties) {
      this.httpHeaders = []
      if (properties) {
        for (const key of Object.keys(properties)) {
          this.httpHeaders.push({key: key, value: properties[key]})
        }
      }
      console.log('copyToHeaders. -> ', this.httpHeaders)
    },
    onTest() {
      this.$refs['form'].validate((validate) => {
        if (validate) {
          this.copyToProperties()
          alarmApi.test(this.form)
              .then(response => {
                this.$alert('<pre style="overflow: auto">' + response.result + '</pre>', '测试运行成功', {
                  dangerouslyUseHTMLString: true
                })
              })
              .catch(error => {
                console.log('测试运行失败: ' + error)
                this.$message({
                  type: 'success',
                  message: '测试运行失败',
                  duration: 1500
                })
              })
        } else {
          return false
        }
      })
    },
    onCancel() {
      this.goBack()
    },
    getDetail(callback) {
      adminApi.findById(this.id)
          .then(response => {
            if (response.result.serviceInfo == null) {
              response.result.serviceInfo = {id: 0}
            }
            this.form = response.result
            this.copyToHeaders(this.form.metricContract.properties)

            if (response.result.metricContract.dataName === 'http') {
              this.dataValue.push('http')
              this.dataSourceType = 'http'
            } else if (response.result.metricContract.dataName === 'ping') {
              this.dataValue.push('ping')
              this.dataSourceType = 'ping'
            } else if (response.result.metricContract.dataName === 'telnet') {
              this.dataValue.push('telnet')
              this.dataSourceType = 'telnet'
            } else if (response.result.metricContract.dataSourceContract !== null) {
              this.dataValue.push(response.result.metricContract.dataSourceContract.datasourceType)
              this.dataValue.push(response.result.metricContract.dataSourceContract.id)
              this.dataValue.push(response.result.metricContract.dataName)
              this.dataSourceType = response.result.metricContract.dataSourceContract.datasourceType
            }
            if (callback) {
              callback()
            }
          })
    },
    dataChange(value) {
      this.form.ruleContract.settings = {}
      if (value.length === 0) {
        this.form.metricContract.dataSourceId = 0
        this.form.metricContract.dataName = ''
        this.initAlertTemplateOptions()
        return
      }
      this.aggregationFields = []
      this.dataSourceType = value[0]
      if (this.dataSourceType === 'http') {
        this.form.metricContract.dataSourceId = 0
        this.form.metricContract.dataName = 'http'
        this.form.metricContract.metricType = 'object'
        this.form.ruleContract.ruleType = 'expression'
        this.initAlertTemplateOptions()
        return
      } else if (this.dataSourceType === 'ping') {
        this.form.metricContract.dataSourceId = 0
        this.form.metricContract.dataName = 'ping'
        this.form.metricContract.metricType = 'ping'
        this.form.ruleContract.ruleType = 'ping'
        this.initAlertTemplateOptions()
        return
      } else if (this.dataSourceType === 'telnet') {
          this.form.metricContract.dataSourceId = 0
          this.form.metricContract.dataName = 'telnet'
          this.form.metricContract.metricType = 'telnet'
          this.form.ruleContract.ruleType = 'ping'
          this.initAlertTemplateOptions()
          return
      } else if (this.dataSourceType === 'elasticsearch') {
        dataQueryApi.elasticsearchFields({dataName: value[2]}).then(response => {
          if (response.returncode === 0 && response.result) {
            // 转换结构
            this.aggregationFields = []
            response.result.forEach(v => this.aggregationFields.push({value: v}))
          }
        })
      }
      this.form.metricContract.dataSourceId = value[1]
      this.form.metricContract.dataName = value[2]
      this.form.metricContract.metricType = ''
      this.initAlertTemplateOptions()
    },
    tabClick(tab, event) {
      // console.log(tab, event)
    },
    findRecipient(keyword) {
      this.loading = true
      search(keyword).then(response => {
        this.recipientList = response.result
        this.loading = false
      })
    },
    loadRecipient() {
      return this.form.alertContract.recipients
    },
    removeRecipient(recipient) {
      const start = this.form.alertContract.recipients.indexOf(recipient)
      this.form.alertContract.recipients.splice(start, 1)
    },
    showRecipient() {
      this.recipient.visible = true
      this.$nextTick(_ => this.$refs.addRecipient.$refs.input.focus())
    },
    inputRecipient() {
      const inputValue = this.recipient.value
      if (inputValue) {
        this.form.alertContract.recipients.push(inputValue)
      }
      this.recipient.visible = false
      this.recipient.value = ''
    },
    intervalChangeHandler(selectedValue) {
      var seconds = (new Date()).getSeconds()
      this.form.cron = seconds + ' ' + selectedValue
    },
    initDayCronOptions() {
      for (var i = 0; i < 24; i++) {
        this.dayCronOptions.push({label: i + ' ' + this.$t('alarm.edit.text_clock'), value: '0 0 ' + i + ' * * ?'})
      }
    },
    dayCronChangeHandler(selectedValue) {
      this.form.cron = selectedValue
    },
    initDataOptions() {
      dataApi.dataOptions().then(response => {
        var remoteOptions = response.result
        for (var i = 0; i < remoteOptions.length; i++) {
          var dataOption = {
            label: remoteOptions[i].datasourceType,
            value: remoteOptions[i].datasourceType,
            children: []
          }
          for (var j = 0; j < remoteOptions[i].dataSourceOptionList.length; j++) {
            var dataSource = {
              label: remoteOptions[i].dataSourceOptionList[j].dataSourceVO.datasourceName,
              value: remoteOptions[i].dataSourceOptionList[j].dataSourceVO.id,
              children: []
            }

            for (var k = 0; k < remoteOptions[i].dataSourceOptionList[j].dataNameContractList.length; k++) {
              dataSource.children.push({
                label: remoteOptions[i].dataSourceOptionList[j].dataNameContractList[k].displayName,
                value: remoteOptions[i].dataSourceOptionList[j].dataNameContractList[k].dataName
              })
            }
            dataOption.children.push(dataSource)
          }
          this.dataOptions.push(dataOption)
        }
        this.dataOptions.push({
          value: 'http', label: 'http'
        })
        this.dataOptions.push({
          value: 'ping', label: 'ping'
        })
      this.dataOptions.push({
          value: 'telnet', label: 'telnet'
      })
      })
    },
    handleHttpTest() {
      this.copyToProperties()
      alarmApi.httpTest(this.form.metricContract).then(response => {
        this.previewResponseData = response.result
        this.previewResponseDialogVisible = true
      })
    },
    metricTypeChangeHandler(newValue) {
      if (newValue === 'same_time') {
        this.form.ruleContract.alertTemplate = '自然${PERIOD_UNIT_DESCRIPTION}\r\n' +
            '<#list REFERENCE_LIST as item>\n' +
            '指标同比${item.description}变化${item.percentage}%,超过阈值${PERCENTAGE_THRESHOLD}%, 当前值: ${CURRENT}, 对比值：${item.value};\r\n' +
            '</#list>'
      }
    },
    initAlertTemplateOptions() {
      this.alertTemplateId = null
      this.alertTemplateOption = null
      var condition = {
        pageIndex: 1,
        pageSize: 500
      }
      var dataName = this.form.metricContract.dataName
      if (dataName != null && dataName !== '') {
        condition.templateTypeUnionCodes.push('DATA_NAME|' + dataName)
      }
      alerttemplateApi.findAlertTemplate(condition)
          .then(response => {
            this.alertTemplateOptions = response.result.list
          }).catch(() => {
        console.error('消息模板加载失败')
      })
    },
    changeAlertTemplateOptions() {
      this.alertTemplateOption = null
      if (this.alertTemplateId) {
        for (var i = 0; i < this.alertTemplateOptions.length; i++) {
          if (this.alertTemplateId === this.alertTemplateOptions[i].id) {
            this.alertTemplateOption = this.alertTemplateOptions[i]
            this.disableAlertTemplateOptions = false
            break
          }
        }
      }
    },
    importAlertTemplate() {
      if (this.alertTemplateOption) {
        this.$confirm('是否确定使用选择的模板覆盖当前消息模板?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.form.ruleContract.alertTemplate = this.alertTemplateOption.content
        }).catch(() => {
        })
      } else {
        this.$alert('请选择一个消息模板', '提示').catch(() => {
        })
      }
    },
    loadServiceOptions(query) {
      this.serviceOptionsLoading = true
      serviceinfoApi.findServiceInfo({
        serviceName: query,
        pageIndex: 1,
        pageSize: 1000,
        orderType: 'SERVICE_NAME'
      })
          .then(response => {
            this.serviceOptions = response.result.list || []
            this.serviceOptions.unshift({
              id: 0,
              serviceName: this.$t('alarm.edit.text_choose_service')
            })
            this.serviceOptionsLoading = false
          })
          .catch(e => {
          })
    },
    searchElasticsearchFields(queryString, cb) {
      var restaurants = this.aggregationFields
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    alertTemplateTypeChangeHandler(newValue) {
      if (newValue === 'TEXT' && !this.form.ruleContract.alertTemplate.replace(/&nbsp;/g, '').trim()) {
        this.form.ruleContract.alertTemplate = '消息类型: [${ALERT_TYPE}] ${ALERT_SILENCE}分钟内连续报警将不重复发送\r\n' +
            '${Project}最近${TIME_WINDOW}分钟内有异常日志${NUMBER}条。最近一条异常信息:\r\n' +
            '服务器IP: ${ServerIP}\r\n' +
            '异常类型: ${ExceptionType}\r\n' +
            '自定义信息: ${CustomMessage}\r\n' +
            '异常信息: ${ExceptionMessage}'
      } else if (newValue === 'MARKDOWN' && !this.form.ruleContract.alertTemplate.replace(/&nbsp;/g, '').trim()) {
        this.form.ruleContract.alertTemplate = '### [霜之哀伤监控平台][id:${ALARM_ID}]${ALARM_NAME}\r\n' +
            '${Project}最近 ${TIME_WINDOW} 分钟内有异常日志 ${NUMBER} 条。最近一条异常信息:\r\n' +
            '> 服务器IP: ${ServerIP}\r\n' +
            '> 异常类型: ${ExceptionType}\r\n' +
            '> 自定义信息: ${CustomMessage}\r\n' +
            '> 异常信息: ${ExceptionMessage}'
      }
    }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}

.vjs-key {
  word-break: normal;
}
</style>

