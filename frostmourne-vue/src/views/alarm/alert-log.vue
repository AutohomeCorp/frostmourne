<template>
  <div class="app-container">
    <div class="block">
      <el-form ref="form" :model="form" label-width="100px" label-position="center">
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item label="监控id:">
              <el-input v-model="form.alarmId" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="执行时间:">
              <el-date-picker v-model="datePickValue" type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right" :default-time="['00:00:00', '23:59:59']" @change="dateChangeHandler" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发送状态:">
              <el-select v-model="form.sendStatus" placeholder="发送状态">
                <el-option label="全部" value="" />
                <el-option label="成功" value="SUCCESS" />
                <el-option label="失败" value="FAIL" />
                <el-option label="未发送" value="NONE" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="执行id:">
              <el-input v-model="form.executeId" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报警方式:">
              <el-select v-model="form.way" placeholder="报警方式">
                <el-option label="全部" value="" />
                <el-option label="钉钉" value="DING_DING" />
                <el-option label="钉钉机器人" value="DING_ROBOT" />
                <el-option label="企业微信" value="WECHAT" />
                <el-option label="企业微信机器人" value="WECHAT_ROBOT" />
                <el-option label="飞书机器人" value="FEI_SHU_ROBOT" />
                <el-option label="短信" value="SMS" />
                <el-option label="邮件" value="EMAIL" />
                <el-option label="HTTP" value="http_post" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="消息类型:">
              <el-select v-model="form.alertType" placeholder="消息类型">
                <el-option label="全部" value="" />
                <el-option label="报警" value="PROBLEM" />
                <el-option label="报警恢复" value="RECOVER" />
                <el-option label="报警升级" value="UPGRADE" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-col :span="4">
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="alarmId" label="监控id" align="center" />
      <el-table-column prop="executeId" label="执行id" align="center" />
      <el-table-column prop="way" label="报警方式" align="center" :formatter="alertWayFormat" />
      <el-table-column prop="inSilence" label="静默状态" align="center" :formatter="silenceFormat" />
      <el-table-column prop="sendStatus" label="发送状态" align="center" :formatter="sendStatusFormat" />
      <el-table-column prop="alertType" label="消息类型" align="center" :formatter="alertTypeFormat" />
      <el-table-column prop="createAt" label="发送时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createAt | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="showMessage(scope.row)">详细>></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block mt-paginate">
      <el-row>
        <el-col :span="8" :offset="6">
          <div class="grid-content">
            <el-pagination background layout="total, prev, pager, next" :page-size="form.pageSize" :total="rowcount" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>
    <el-dialog title="消息内容" :visible.sync="dialogVisible" width="35%">
      <pre style="overflow: auto; word-wrap: break-word" v-text="currentMessage" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import logApi from '@/api/log.js'
import { formatJsonDate } from '@/utils/datetime.js'
import moment from 'moment'

export default {
  filters: {
    timeFormat (value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  data () {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick (picker) {
            const startMoment = moment().startOf('day')
            const endMoment = moment().endOf('day')
            picker.$emit('pick', [startMoment.toDate(), endMoment.toDate()])
          }
        },
        {
          text: '昨天',
          onClick (picker) {
            const end = moment().startOf('day').toDate()
            const start = moment().startOf('day').subtract(1, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: '前天',
          onClick (picker) {
            const end = moment().startOf('day').subtract(1, 'day').toDate()
            const start = moment().startOf('day').subtract(2, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: '最近三天',
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(3, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: '最近一周',
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(7, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: '最近一月',
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(30, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        }]
      },
      dialogVisible: false,
      datePickValue: [],
      list: null,
      rowcount: 0,
      listLoading: true,
      currentMessage: '',
      form: {
        alarmId: null,
        startTime: null,
        endTime: null,
        sendStatus: null,
        executeId: null,
        way: null,
        alertType: null,
        pageIndex: 1,
        pageSize: 10
      }
    }
  },
  created () {
    const startMoment = moment().startOf('day')
    const endMoment = moment().endOf('day')
    this.datePickValue[0] = startMoment.toDate()
    this.datePickValue[1] = endMoment.toDate()
    this.form.startTime = startMoment.toDate()
    this.form.endTime = endMoment.toDate()
    this.fetchData()
  },
  methods: {
    onSubmit () {
      this.fetchData()
    },
    onStatusChange () {
      this.form.pageIndex = 1
      this.fetchData()
    },
    onPrevClick () {
      this.form.pageIndex--
      this.fetchData()
    },
    onNextClick () {
      this.form.pageIndex++
      this.fetchData()
    },
    onCurrentChange (curr) {
      this.form.pageIndex = curr
      this.fetchData()
    },
    fetchData () {
      this.listLoading = true
      logApi.findAlertLog(this.form)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
    },
    dateChangeHandler (value) {
      this.form.startTime = value[0]
      this.form.endTime = value[1]
    },
    showMessage (row) {
      this.currentMessage = row.content
      this.dialogVisible = true
      /* this.$alert('<pre>' + row.content + '</pre>', '消息内容',{
          dangerouslyUseHTMLString: true
        })*/
    },
    alertWayFormat (date) {
      if (date.way === 'EMAIL') {
        return '邮件'
      }
      if (date.way === 'SMS') {
        return '短信'
      }
      if (date.way === 'WECHAT') {
        return '企业微信'
      }
      if (date.way === 'WECHAT_ROBOT') {
        return '企业微信机器人'
      }
      if (date.way === 'DING_DING') {
        return '钉钉'
      }
      if (date.way === 'DING_ROBOT') {
        return '钉钉机器人'
      }
      if (date.way === 'FEI_SHU_ROBOT') {
        return '飞书机器人'
      }
      if (date.way === 'HTTP_POST') {
        return 'HTTP_POST'
      }
    },
    silenceFormat (date) {
      if (date.inSilence === 'YES') {
        return '已静默'
      }
      if (date.inSilence === 'NO') {
        return '未静默'
      }
    },
    sendStatusFormat (date) {
      if (date.sendStatus === 'SUCCESS') {
        return '成功'
      }
      if (date.sendStatus === 'FAIL') {
        return '失败'
      }
      if (date.sendStatus === 'NONE') {
        return '未发送'
      }
    },
    alertTypeFormat (date) {
      if (date.alertType === 'PROBLEM') {
        return '报警'
      }
      if (date.alertType === 'UPGRADE') {
        return '报警升级'
      }
      if (date.alertType === 'RECOVER') {
        return '报警恢复'
      }
    }
  }
}
</script>
