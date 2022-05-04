<template>
  <div class="app-container">
    <el-form ref="form" size="medium" label-position="right" :model="form" :rules="rules" label-width="120px">
      <el-tabs>
        <el-tab-pane label="基础信息">
          <el-row>
            <el-col :span="12">
              <el-form-item label="监控名称:" prop="alarmName">
                <el-input v-model="form.alarmName" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item>
                <el-switch v-model="form.status" active-value="OPEN" active-text="开启" inactive-value="CLOSE" inactive-text="关闭" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="所属服务:">
                <el-select v-model="form.serviceInfo.id" reserve-keyword placeholder="请选择服务">
                  <el-option v-for="item in serviceOptions" :key="item.id" :label="item.serviceName" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="风险等级:">
                <el-select v-model="form.riskLevel" size="small" style="width:100px" placeholder="风险等级">
                  <el-option label="提示" value="info" />
                  <el-option label="重要" value="important" />
                  <el-option label="紧急" value="emergency" />
                  <el-option label="我崩了" value="crash" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="所属对象:">
                <el-input v-model="form.ownerKey" placeholder="表示这个监控的归属对象关系" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="所属团队:" prop="teamName">
                <el-select v-model="form.teamName" placeholder="选择团队">
                  <el-option v-for="item in teamList" :key="item.name" :label="item.fullName" :value="item.name" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="描述:" prop="description">
                <el-input v-model="form.description" type="textarea" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>

      <el-tabs>
        <el-tab-pane label="数据配置">
          <el-row>
            <el-col :span="6">
              <el-form-item label="数据:" prop="metricContract.dataName">
                <el-cascader v-model="dataValue" width="100" size="medium" :show-all-levels="false" :options="dataOptions" @change="dataChange" />
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch'" label="聚合类型:">
                <el-select v-model="form.metricContract.aggregationType">
                  <el-option label="count" value="count" />
                  <el-option label="avg" value="avg" />
                  <el-option label="min" value="min" />
                  <el-option label="max" value="max" />
                  <el-option label="sum" value="sum" />
                  <el-option label="unique count" value="cardinality" />
                  <el-option label="standard deviation" value="standard_deviation" />
                  <el-option label="percentiles" value="percentiles" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch' && form.metricContract.aggregationType === 'percentiles'" label="百分比:">
                <el-input v-model="form.metricContract.properties.percent" placeholder="例如: 90" />
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item v-if="dataSourceType === 'elasticsearch' && form.metricContract.aggregationType !== 'count'" label="聚合字段:">
                <el-autocomplete
                  v-model="form.metricContract.aggregationField"
                  class="inline-input"
                  :fetch-suggestions="searchElasticsearchFields" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="查询语句:" prop="metricContract.queryString">
            <el-input v-model="form.metricContract.queryString" />
          </el-form-item>
          <el-form-item v-if="dataSourceType === 'http'" label="HTTP头:">
            <template v-if="httpHeaders.length === 0">
              <el-button type="primary" @click="addHeader">+</el-button>
            </template>
            <template v-else>
              <el-row v-for="(item, index) in httpHeaders" :key="'header-'+index">
                <el-input v-model="item.key" clearable style="width:160px;" />
                =
                <el-input v-model="item.value" clearable style="width:300px;" />
                <el-link :underline="false" icon="el-icon-delete" @click="removeHeader(index)" />
                <el-link :underline="false" icon="el-icon-plus" @click="addHeader" />
              </el-row>
            </template>
          </el-form-item>
          <el-form-item v-if="dataSourceType === 'http'" label="POST数据:">
            <el-input v-model="form.metricContract.postData" type="textarea" />
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="报警规则">
          <el-form-item label="判断类型:" prop="metricContract.metricType">
            <el-select v-model="form.metricContract.metricType" @change="metricTypeChangeHandler">
              <el-option v-if="dataSourceType !== 'http'" label="数值比较" value="numeric" />
              <el-option v-if="dataSourceType === 'http'" label="Javascript表达式" value="object" />
              <!--<el-option label="环比" value="ring_than"/>-->
              <el-option v-if="dataSourceType !== 'http'" label="同比" value="same_time" />
            </el-select>
          </el-form-item>
          <el-row>
            <el-form-item v-if="form.metricContract.metricType === 'numeric'" label="判断规则:">
              最近
              <el-input-number v-model="form.ruleContract.settings.TIME_WINDOW" size="small" :min="1" label="间隔分钟" />分钟；指标数值
              <el-select v-model="form.ruleContract.settings.OPERATOR" size="small" style="width:100px" placeholder="比较类型">
                <el-option label=">=" value="GTE" />
                <el-option label="<=" value="LTE" />
                <el-option label="==" value="EQUAL" />
              </el-select>
              <el-input-number v-model="form.ruleContract.settings.THRESHOLD" :precision="2" size="small" label="阈值" />
            </el-form-item>
          </el-row>
          <el-form-item v-if="form.metricContract.metricType === 'object'" label="判断表达式:">
            <el-input v-model="form.ruleContract.settings.EXPRESSION" type="textarea" />
          </el-form-item>
          <el-form-item v-if="form.metricContract.metricType === 'ring_than'" label="判断规则:">
            <el-select v-model="form.ruleContract.settings.PERIOD_UNIT">
              <el-option label="周" value="week" />
              <el-option label="日" value="day" />
              <el-option label="小时" value="hour" />
              <el-option label="分钟" value="minute" />
            </el-select>环比
            <el-select v-model="form.ruleContract.settings.DIFF_COMPARE_TYPE">
              <el-option label="增加" value="increase" />
              <el-option label="减少" value="decrease" />
            </el-select>百分之
            <el-input v-model="form.ruleContract.settings.PERCENT_THRESHOLD" style="width: 150px" />
          </el-form-item>
          <el-form-item v-if="form.metricContract.metricType === 'same_time'" label="判断规则:">
            <el-select v-model="form.ruleContract.settings.PERIOD_UNIT">
              <el-option label="小时" value="hour" />
              <el-option label="天" value="day" />
            </el-select>同比
            <el-select v-model="form.ruleContract.settings.REFERENCE_TYPE_LIST">
              <el-option label="昨天" value="day" />
              <el-option label="上周" value="week" />
              <el-option label="上月" value="month" />
              <el-option label="昨天和上周" value="day,week" />
            </el-select>
            <el-select v-model="form.ruleContract.settings.COMPARE_TYPE">
              <el-option label="增加" value="increase" />
              <el-option label="减少" value="decrease" />
              <el-option label="增加或减少" value="both" />
            </el-select>超过百分之
            <el-input v-model="form.ruleContract.settings.PERCENTAGE_THRESHOLD" style="width: 150px" />并且差值(当前值 - 对比值)
            <el-select v-model="form.ruleContract.settings.DIFF_COMPARE_TYPE">
              <el-option label="绝对值>=" value="ABS_GTE" />
              <el-option label="绝对值<=" value="ABS_LTE" />
              <el-option label=">=" value="GTE" />
              <el-option label="<=" value="LTE" />
            </el-select>
            <el-input v-model="form.ruleContract.settings.DIFF_VALUE_THRESHOLD" style="width: 100px" />
          </el-form-item>

        </el-tab-pane>
        <el-tab-pane label="消息模板">
          <el-form-item>
            <el-select v-model.number="alertTemplateId" filterable placeholder="请选择" @change="changeAlertTemplateOptions()">
              <el-option v-for="item in alertTemplateOptions"
                         :key="item.id" :label="item.templateName" :value="item.id" />
            </el-select>
            <el-button type="primary" :disabled="alertTemplateOption==null" @click="importAlertTemplate">导入模板</el-button>
          </el-form-item>
          <el-form-item label="消息类型:" prop="ruleContract.alertTemplateType">
            <el-select v-model="form.ruleContract.alertTemplateType" @change="alertTemplateTypeChangeHandler">
              <el-option label="text" value="TEXT" />
              <el-option label="markdown" value="MARKDOWN" />
            </el-select>
          </el-form-item>
          <el-form-item label="消息模板:" prop="ruleContract.alertTemplate">
            <el-input v-model="form.ruleContract.alertTemplate" type="textarea" rows="5" />
          </el-form-item>
          <el-form-item label="自定义链接:">
            <el-input v-model="form.metricContract.properties.dataLink" placeholder="选填，自定义链接不使用短链接" />
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-button type="primary" @click="onPreview">预览数据</el-button>
      <el-tabs>
        <el-tab-pane label="报警发送">
          <el-row>
            <el-col :span="8">
              <el-form-item label="报警方式:" prop="alertContract.ways">
                <el-checkbox-group v-model="form.alertContract.ways" size="small">
                  <el-checkbox-button label="dingding">钉钉</el-checkbox-button>
                  <el-checkbox-button label="email">Email</el-checkbox-button>
                  <el-checkbox-button label="sms">短信</el-checkbox-button>
                  <el-checkbox-button label="wechat">企业微信</el-checkbox-button>
                  <el-checkbox-button label="http_post">HTTP</el-checkbox-button>
                  <el-checkbox-button label="feishu">飞书</el-checkbox-button>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label="恢复通知:">
                <el-switch v-model="form.recoverNoticeStatus" active-value="OPEN" active-text="开启"
                           inactive-value="CLOSE" inactive-text="关闭" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item v-if="form.alertContract.ways.includes('dingding')" label="钉钉机器人:">
            <el-input v-model="form.alertContract.dingRobotHook" size="small" placeholder="选填" />
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('wechat')" label="微信机器人:">
            <el-input v-model="form.alertContract.wechatRobotHook" size="small" placeholder="选填" />
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('http_post')" label="HTTP地址:">
            <el-input v-model="form.alertContract.httpPostUrl" size="small" placeholder="必填" />
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('feishu')" label="飞书机器人:">
            <el-input v-model="form.alertContract.feishuRobotHook" size="small" placeholder="必填" />
          </el-form-item>
          <el-row>
            <el-col :span="8">
              <el-form-item label="静默时间:">
                <el-input-number v-model="form.alertContract.silence" size="small" :min="0" label="静默时间" />
                分钟
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label="静默判断:" prop="alertContract.silenceExpression">
                <span slot="label">
                  <el-tooltip class="item" effect="light" placement="right-start">
                    <div slot="content">
                      说明：支持多个数据字段比对，可使用逻辑运算符 '&&'，'||' 和 '()'<br /><br />
                      举例：<br />
                      &nbsp;&nbsp;&nbsp;&nbsp;1、日志链路和日志堆栈字段有一个值相同则静默：TraceId || StackTrace<br />
                      &nbsp;&nbsp;&nbsp;&nbsp;2、数据字段A和数据字段B的值都相同则静默：A && B<br /><br />
                      注：非必填，为空则默认静默时间内只会报警一次<br />
                    </div>
                    <i class="el-icon-question"></i>
                  </el-tooltip>
                  静默判断:
                </span>
                <el-input v-model="form.alertContract.silenceExpression"
                          placeholder="指定字段进行值相同判断，多个字段支持 '&&'，'||' 和 '()' 逻辑语法" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="报警接收人:" prop="alertContract.recipients">
            <el-select v-model="form.alertContract.recipients" style="width:100%;" multiple filterable remote
                       placeholder="支持关键词模糊搜索" :remote-method="findRecipient" :loading="loading">
              <el-option v-for="item in recipientList" :key="item.account" :label="item.account" :value="item.account" />
            </el-select>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <el-tabs>
        <el-tab-pane label="调度配置">
          <el-row>
            <el-col :span="8">
              <el-form-item label="每隔:">
                <el-select v-model="intervalCron" @change="intervalChangeHandler">
                  <el-option label="1分钟" value="0/1 * * * ?" />
                  <el-option label="2分钟" value="0/2 * * * ?" />
                  <el-option label="3分钟" value="0/3 * * * ?" />
                  <el-option label="5分钟" value="0/5 * * * ?" />
                  <el-option label="10分钟" value="0/10 * * * ?" />
                  <el-option label="15分钟" value="0/15 * * * ?" />
                  <el-option label="30分钟" value="0/30 * * * ?" />
                  <el-option label="1小时" value="0 0/1 * * ?" />
                  <el-option label="2小时" value="0 0/2 * * ?" />
                  <el-option label="6小时" value="0 0/6 * * ?" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="3">或者</el-col>
            <el-col :span="8">
              <el-form-item label="每天:">
                <el-select v-model="dayCron" @change="dayCronChangeHandler">
                  <el-option v-for="item in dayCronOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="cron表达式:" prop="cron">
            <el-input v-model="form.cron" />
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <el-form-item style="margin-top: 20px;">
        <el-button type="primary" :disabled="disableSave" @click="onSubmit">保存</el-button>
        <el-button type="primary" @click="onTest">测试</el-button>
        <el-button v-if="enableSaveAnother" type="primary" @click="onSaveAnother">另存</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="响应数据" :visible.sync="previewResponseDialogVisible">
      <div>
        <vue-json-pretty :data="previewResponseData" />
      </div>
    </el-dialog>
  </div>
</template>

<script>
import alarmApi from '@/api/alarm.js'
import adminApi from '@/api/admin.js'
import { teams, search } from '@/api/user'
import dataApi from '@/api/data.js'
import alerttemplateApi from '@/api/alert-template.js'
import serviceinfoApi from '@/api/service-info.js'
import dataQueryApi from '@/api/data-query.js'

import VueJsonPretty from 'vue-json-pretty'
import 'vue-json-pretty/lib/styles.css'

export default {
  components: {
    VueJsonPretty
  },
  data () {
    const validatorSilenceExpression = (rule, value, callback) => {
      if (value !== '') {
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
          properties: {}
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
          settings: {}
        },
        alertContract: {
          ways: [],
          recipients: [],
          silence: 60,
          silenceExpression: ''
        },
        serviceInfo: {
          id: 0
        }
      },
      rules: {
        alarmName: [
          { required: true, message: '请输入监控名称', trigger: 'blur' }
        ],
        teamName: [
          { required: true, message: '请选择团队', trigger: 'change' }
        ],
        description: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
        'metricContract.dataName': [
          { required: true, message: '请选择数据', trigger: 'change' }
        ],
        'metricContract.queryString': [
          { required: true, message: '请输入查询语句', trigger: 'blur' }
        ],
        'metricContract.metricType': [
          { required: true, message: '请选择判断类型', trigger: 'change' }
        ],
        'ruleContract.alertTemplateType': [
          { required: true, message: '请选择消息类型', trigger: 'blur' }
        ],
        'ruleContract.alertTemplate': [
          { required: true, message: '请输入消息模板', trigger: 'blur' }
        ],
        'alertContract.ways': [
          { type: 'array', required: true, message: '请至少选择一种报警方式', trigger: 'change' }
        ],
        'alertContract.silenceExpression': [
          { validator: validatorSilenceExpression, trigger: 'blur' },
          { max: 500, message: '长度限制在500个字符以内', trigger: 'blur' }
        ],
        'alertContract.recipients': [
          { type: 'array', required: true, message: '请配置报警接收人', trigger: 'blur' }
        ],
        cron: [
          { required: true, message: '请输入cron表达式', trigger: 'blur' }
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
  beforeRouteEnter (to, from, next) {
    next(
      vm => { vm.referer = from }
    )
  },
  mounted () {
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
    goBack () {
      if (this.referer) {
        this.$router.back()
      } else {
        this.$router.push({ path: '/alarm/list.view' })
      }
    },
    success (message) {
      this.$message({
        type: 'success',
        message: message,
        duration: 500,
        onClose: () => this.goBack()
      })
    },
    validateInput () {
      if (this.form.alertContract.ways.includes('feishu') && (this.form.alertContract.feishuRobotHook === null || this.form.alertContract.feishuRobotHook === '')) {
        this.$message({ type: 'warn', message: '飞书机器人地址不能为空' })
        return false
      }
      return true
    },
    onSubmit () {
      this.$refs['form'].validate((validate) => {
        if (validate) {
          if (!this.validateInput()) {
            return false
          }
          this.disableSave = false
          this.copyToProperties()

          alarmApi.test(this.form)
            .then(() => {
              // 测试通过
              adminApi.save(this.form)
                .then(() => this.success('保存成功！'))
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
    onPreview () {
      if (this.form.metricContract.dataName == null) {
        this.$message({ type: 'warning', message: '请先选择一个数据名', duration: 2000 })
        return
      }
      if (this.form.metricContract.queryString == null || this.form.metricContract.queryString === '') {
        this.$message({ type: 'warning', message: '查询语句不能为空', duration: 2000 })
        return
      }
      this.copyToProperties()
      alarmApi.previewData(this.form).then(response => {
        this.previewResponseData = response.result
        this.previewResponseDialogVisible = true
      })
    },
    onSaveAnother () {
      this.copyToProperties()
      this.form.alarmName = this.form.alarmName + '(copy)'
      adminApi.saveAnother(this.form)
        .then(response => this.success('另存成功！'))
        .catch(error => {
          console.log('另存失败:', error)
        })
    },
    removeHeader (index) {
      if (index > -1) {
        this.httpHeaders.splice(index, 1)
      }
      console.log('addHeader.headers -> ', this.httpHeaders)
    },
    addHeader () {
      this.httpHeaders.push({ key: '', value: '' })
      console.log('addHeader.headers -> ' + this.httpHeaders.length, this.httpHeaders)
    },
    copyToProperties () {
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
    copyToHeaders (properties) {
      this.httpHeaders = []
      if (properties) {
        for (const key of Object.keys(properties)) {
          this.httpHeaders.push({ key: key, value: properties[key] })
        }
      }
      console.log('copyToHeaders. -> ', this.httpHeaders)
    },
    onTest () {
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
    onCancel () {
      this.goBack()
    },
    getDetail (callback) {
      adminApi.findById(this.id)
        .then(response => {
          if (response.result.serviceInfo == null) {
            response.result.serviceInfo = { id: 0 }
          }
          this.form = response.result
          this.copyToHeaders(this.form.metricContract.properties)

          if (response.result.metricContract.dataName === 'http') {
            this.dataValue.push('http')
            this.dataSourceType = 'http'
          } else {
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
    dataChange (value) {
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
      } else if (this.dataSourceType === 'elasticsearch') {
        dataQueryApi.elasticsearchFields({ dataName: value[2] }).then(response => {
          if (response.returncode === 0 && response.result) {
            // 转换结构
            this.aggregationFields = []
            response.result.forEach(v => this.aggregationFields.push({ value: v }))
          }
        })
      }
      this.form.metricContract.dataSourceId = value[1]
      this.form.metricContract.dataName = value[2]
      this.form.metricContract.metricType = ''
      this.initAlertTemplateOptions()
    },
    tabClick (tab, event) {
      // console.log(tab, event)
    },
    findRecipient (keyword) {
      this.loading = true
      search(keyword).then(response => {
        this.recipientList = response.result
        this.loading = false
      })
    },
    loadRecipient () {
      return this.form.alertContract.recipients
    },
    removeRecipient (recipient) {
      const start = this.form.alertContract.recipients.indexOf(recipient)
      this.form.alertContract.recipients.splice(start, 1)
    },
    showRecipient () {
      this.recipient.visible = true
      this.$nextTick(_ => this.$refs.addRecipient.$refs.input.focus())
    },
    inputRecipient () {
      const inputValue = this.recipient.value
      if (inputValue) {
        this.form.alertContract.recipients.push(inputValue)
      }
      this.recipient.visible = false
      this.recipient.value = ''
    },
    intervalChangeHandler (selectedValue) {
      var seconds = (new Date()).getSeconds()
      this.form.cron = seconds + ' ' + selectedValue
    },
    initDayCronOptions () {
      for (var i = 0; i < 24; i++) {
        this.dayCronOptions.push({ label: i + '点', value: '0 0 ' + i + ' * * ?' })
      }
    },
    dayCronChangeHandler (selectedValue) {
      this.form.cron = selectedValue
    },
    initDataOptions () {
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
              label: remoteOptions[i].dataSourceOptionList[j].dataSource.datasourceName,
              value: remoteOptions[i].dataSourceOptionList[j].dataSource.id,
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
      })
    },
    handleHttpTest () {
      this.copyToProperties()
      alarmApi.httpTest(this.form.metricContract).then(response => {
        this.previewResponseData = response.result
        this.previewResponseDialogVisible = true
      })
    },
    metricTypeChangeHandler (newValue) {
      if (newValue === 'same_time') {
        this.form.ruleContract.alertTemplate = '自然${PERIOD_UNIT_DESCRIPTION}\r\n' +
        '<#list REFERENCE_LIST as item>\n' +
        '指标同比${item.description}变化${item.percentage}%,超过阈值${PERCENTAGE_THRESHOLD}%, 当前值: ${CURRENT}, 对比值：${item.value};\r\n' +
        '</#list>'
      }
    },
    initAlertTemplateOptions () {
      this.alertTemplateId = null
      this.alertTemplateOption = null
      var condition = {
        templateTypeUnionCodes: ['COMMON'],
        pageIndex: 1,
        pageSize: 1000
      }
      var dataName = this.form.metricContract.dataName
      if (dataName != null && dataName !== '') {
        condition.templateTypeUnionCodes.push('dataName|' + dataName)
      }
      alerttemplateApi.findAlertTemplate(condition)
        .then(response => {
          this.alertTemplateOptions = response.result.list
        }).catch(() => {
          console.error('消息模板加载失败')
        })
    },
    changeAlertTemplateOptions () {
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
    importAlertTemplate () {
      if (this.alertTemplateOption) {
        this.$confirm('是否确定使用选择的模板覆盖当前消息模板?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.form.ruleContract.alertTemplate = this.alertTemplateOption.content
        }).catch(() => {})
      } else {
        this.$alert('请选择一个消息模板', '提示').catch(() => {})
      }
    },
    loadServiceOptions (query) {
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
            serviceName: '选择服务'
          })
          this.serviceOptionsLoading = false
        })
        .catch(e => {})
    },
    searchElasticsearchFields (queryString, cb) {
      var restaurants = this.aggregationFields
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      cb(results)
    },
    createFilter (queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    alertTemplateTypeChangeHandler (newValue) {
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
</style>

