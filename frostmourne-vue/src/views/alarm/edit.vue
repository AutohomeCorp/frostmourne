<template>
  <div class="app-container">
    <el-form ref="form" size="medium" label-position="right" :model="form" :rules="rules" label-width="120px">
      <el-tabs>
        <el-tab-pane label="基础信息">
          <el-row>
            <el-col :span="12">
              <el-form-item label="监控名称:" prop="alarm_name">
                <el-input v-model="form.alarm_name" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label>
                <el-switch v-model="form.status" active-value="OPEN" active-text="开启" inactive-value="CLOSE" inactive-text="关闭" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="所属对象:">
                <el-input v-model="form.owner_key" placeholder="表示这个监控的归属对象关系" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="所属团队:" prop="team_name">
                <el-select v-model="form.team_name" placeholder="选择团队">
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
            <el-col :span="8">
              <el-form-item label="数据:" prop="metricContract.data_name">
                <el-cascader v-model="dataValue" width="100" size="medium" :show-all-levels="false" :options="dataOptions" @change="dataChange" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item v-if="dataSourceType === 'elasticsearch'" label="聚合类型:">
                <el-select v-model="form.metricContract.aggregation_type">
                  <el-option label="count" value="count" />
                  <el-option label="avg" value="avg" />
                  <el-option label="min" value="min" />
                  <el-option label="max" value="max" />
                  <el-option label="sum" value="sum" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item v-if="dataSourceType === 'elasticsearch' && form.metricContract.aggregation_type !== 'count'" label="聚合字段:">
                <el-input v-model="form.metricContract.aggregation_field" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="查询语句:" prop="metricContract.query_string">
            <el-input v-model="form.metricContract.query_string" />
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
            <el-input v-model="form.metricContract.post_data" type="textarea" />
            <el-button type="primary" @click="handleHttpTest">测试请求</el-button>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="报警规则">
          <el-form-item label="判断类型:" prop="metricContract.metric_type">
            <el-select v-model="form.metricContract.metric_type">
              <el-option v-if="dataSourceType != 'http'" label="数值" value="numeric" />
              <el-option v-if="dataSourceType === 'http'" label="Javascript表达式" value="object" />
              <!--<el-option label="环比" value="ring_than"/>-->
            </el-select>
          </el-form-item>
          <el-row>
            <el-form-item v-if="form.metricContract.metric_type == 'numeric'" label="判断规则:">
              最近
              <el-input-number v-model="form.ruleContract.settings.TIME_WINDOW" size="small" :min="1" label="间隔分钟" />分钟；指标数值
              <el-select v-model="form.ruleContract.settings.OPERATOR" size="small" style="width:100px" placeholder="比较类型">
                <el-option label=">=" value="GTE" />
                <el-option label="<=" value="LTE" />
                <el-option label="==" value="EQUAL" />
              </el-select>
              <el-input-number v-model="form.ruleContract.settings.THRESHOLD" size="small" label="阈值" />
            </el-form-item>
          </el-row>
          <el-form-item v-if="form.metricContract.metric_type == 'object'" label="判断表达式:">
            <el-input v-model="form.ruleContract.settings.EXPRESSION" type="textarea" />
          </el-form-item>
          <el-form-item v-if="form.metricContract.metric_type == 'ring_than'" label="判断规则:">
            <el-select v-model="form.ruleContract.PERIOD_UNIT">
              <el-option label="周" value="week" />
              <el-option label="日" value="day" />
              <el-option label="小时" value="hour" />
              <el-option label="分钟" value="minute" />
            </el-select>环比
            <el-select v-model="form.ruleContract.settings.OPERATOR">
              <el-option label="增加" value="increase" />
              <el-option label="减少" value="decrease" />
            </el-select>百分之
            <el-input v-model="form.ruleContract.settings.PERCENT_THRESHOLD" style="width: 200px" />
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="消息模板">
          <el-form-item label="消息模板:" prop="ruleContract.alert_template">
            <el-input v-model="form.ruleContract.alert_template" type="textarea" rows="5" />
          </el-form-item>
        </el-tab-pane>
      </el-tabs>

      <el-tabs>
        <el-tab-pane label="报警发送">
          <el-form-item label="报警方式:" prop="alertContract.ways">
            <el-checkbox-group v-model="form.alertContract.ways" size="small">
              <el-checkbox-button label="dingding">钉钉</el-checkbox-button>
              <el-checkbox-button label="email">Email</el-checkbox-button>
              <el-checkbox-button label="sms">短信</el-checkbox-button>
              <el-checkbox-button label="wechat">企业微信</el-checkbox-button>
              <el-checkbox-button label="http_post">HTTP</el-checkbox-button>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('dingding')" label="钉钉机器人:">
            <el-input v-model="form.alertContract.ding_robot_hook" size="small" placeholder="选填" />
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('wechat')" label="微信机器人:">
            <el-input v-model="form.alertContract.wechat_robot_hook" size="small" placeholder="选填" />
          </el-form-item>
          <el-form-item v-if="form.alertContract.ways.includes('http_post')" label="HTTP地址:">
            <el-input v-model="form.alertContract.http_post_url" size="small" placeholder="必填" />
          </el-form-item>
          <el-form-item label="静默时间:">
            <el-input-number v-model="form.alertContract.silence" size="small" :min="1" label="静默时间" />分钟
          </el-form-item>
          <el-form-item label="报警接收人:" prop="alertContract.recipients">
            <el-select v-model="form.alertContract.recipients" style="width:100%;" multiple filterable remote
                       placeholder="请输入关键词" :remote-method="findRecipient" :loading="loading">
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
            <el-col :span="8">或者</el-col>
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

    <el-dialog title="响应数据" :visible.sync="httpResponseDialogVisible">
      <div>
        <vue-json-pretty :data="httpResonseData" />
      </div>
    </el-dialog>
  </div>
</template>

<script>
import alarmApi from '@/api/alarm.js'
import adminApi from '@/api/admin.js'
import { teams, search } from '@/api/user'
import dataApi from '@/api/data.js'

import VueJsonPretty from 'vue-json-pretty'

export default {
  components: {
    VueJsonPretty
  },
  data () {
    return {
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
      httpResonseData: {},
      httpResponseDialogVisible: false,
      httpHeaders: [],
      loading: false,
      recipientList: [],
      form: {
        alarm_name: '',
        owner_key: '',
        status: 'OPEN',
        team_name: '',
        description: '',
        cron: '',
        metricContract: {
          query_string: '',
          post_data: null,
          aggregation_type: '',
          aggregation_field: '',
          metric_type: '',
          data_source_id: 0,
          data_name_id: 0,
          data_name: '',
          properties: {}
        },
        ruleContract: {
          rule_type: '',
          alert_template: '${Project}最近${TIME_WINDOW}分钟内有异常日志${NUMBER}条。最近一条异常信息:\r\n' +
            '服务器IP: ${ServerIP}\r\n' +
            '异常类型: ${ExceptionType}\r\n' +
            '自定义信息: ${CustomMessage}\r\n' +
            '异常信息: ${ExceptionMessage}',
          settings: {}
        },
        alertContract: {
          ways: [],
          recipients: [],
          silence: 60
        }
      },
      rules: {
        alarm_name: [
          { required: true, message: '请输入监控名称', trigger: 'blur' }
        ],
        team_name: [
          { required: true, message: '请选择团队', trigger: 'change' }
        ],
        description: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
        'metricContract.data_name': [
          { required: true, message: '请选择数据', trigger: 'change' }
        ],
        'metricContract.query_string': [
          { required: true, message: '请输入查询语句', trigger: 'blur' }
        ],
        'metricContract.metric_type': [
          { required: true, message: '请选择判断类型', trigger: 'change' }
        ],
        'ruleContract.alert_template': [
          { required: true, message: '请输入消息模板', trigger: 'blur' }
        ],
        'alertContract.ways': [
          { type: 'array', required: true, message: '请至少选择一种报警方式', trigger: 'change' }
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
      enableSaveAnother: true
    }
  },
  mounted () {
    this.initDayCronOptions()
    if (this.id) {
      this.getDetail()
      this.enableSaveAnother = true
    } else {
      this.enableSaveAnother = false
    }

    teams().then(response => {
      this.teamList = response.result
    })

    search('').then(response => {
      this.recipientList = response.result
    })

    if (this.$route.query.data_name) {
      this.dataValue.push(this.$route.query.datasource_type)
      this.dataValue.push(this.$route.query.datasource_id)
      this.dataValue.push(this.$route.query.data_name)
      this.dataSourceType = this.$route.query.datasource_type
      this.form.metricContract.query_string = this.$route.query.query_string
      this.dataChange(this.dataValue)
    }
    this.initDataOptions()
  },
  methods: {
    onSubmit () {
      this.$refs['form'].validate((validate) => {
        if (validate) {
          this.disableSave = false
          this.copyToProperties()
          adminApi.save(this.form)
            .then(response => {
              this.$message({
                type: 'success',
                message: '保存成功！',
                duration: 500,
                onClose: () => this.$router.push({ path: '/alarm/list.view' })
              })
            })
            .catch(error => {
              this.$message({
                type: 'success',
                message: '保存失败: ' + error,
                duration: 500
              })
            })
        } else {
          return false
        }
      })
    },
    onSaveAnother () {
      this.copyToProperties()
      adminApi.saveAnother(this.form)
        .then(response => {
          this.$message({
            type: 'success',
            message: '另存成功！',
            duration: 500,
            onClose: () => this.$router.push({ path: '/alarm/list.view' })
          })
        })
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
      this.$router.push({ path: '/alarm/list.view' })
    },
    getDetail () {
      adminApi.findById(this.id)
        .then(response => {
          this.form = response.result
          this.copyToHeaders(this.form.metricContract.properties)

          if (response.result.metricContract.data_name === 'http') {
            this.dataValue.push('http')
            this.dataSourceType = 'http'
          } else {
            this.dataValue.push(response.result.metricContract.dataSourceContract.datasource_type)
            this.dataValue.push(response.result.metricContract.dataSourceContract.id)
            this.dataValue.push(response.result.metricContract.data_name)
            this.dataSourceType = response.result.metricContract.dataSourceContract.datasource_type
          }
        })
    },
    dataChange (value) {
      if (value.length === 0) {
        this.form.metricContract.data_source_id = 0
        this.form.metricContract.data_name = ''
        return
      }
      this.dataSourceType = value[0]
      if (this.dataSourceType === 'http') {
        this.form.metricContract.data_source_id = 0
        this.form.metricContract.data_name = 'http'
        this.form.metricContract.metric_type = 'object'
        this.form.ruleContract.rule_type = 'expression'
        return
      }
      this.form.metricContract.data_source_id = value[1]
      this.form.metricContract.data_name = value[2]
      this.form.metricContract.metric_type = ''
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
              label: remoteOptions[i].dataSourceOptionList[j].dataSource.datasource_name,
              value: remoteOptions[i].dataSourceOptionList[j].dataSource.id,
              children: []
            }

            for (var k = 0; k < remoteOptions[i].dataSourceOptionList[j].dataNameContractList.length; k++) {
              dataSource.children.push({
                label: remoteOptions[i].dataSourceOptionList[j].dataNameContractList[k].display_name,
                value: remoteOptions[i].dataSourceOptionList[j].dataNameContractList[k].data_name
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
      alarmApi.httpTest(this.form.metricContract).then(response => {
        this.httpResonseData = response.result
        this.httpResponseDialogVisible = true
      })
    }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

