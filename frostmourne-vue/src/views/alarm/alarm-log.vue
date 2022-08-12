<template>
  <div class="app-container">
    <div class="block">
      <el-form ref="form" :model="form" label-width="100px" label-position="center">
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item :label="$t('alarm.alarmLog.label_alarm_id') + ':'">
              <el-input v-model="form.alarmId" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('alarm.alarmLog.label_execute_time') + ':'">
              <el-date-picker v-model="datePickValue" type="datetimerange" :picker-options="pickerOptions" range-separator="To" :start-placeholder="$t('alarm.alarmLog.label_start_date')" 
              :end-placeholder="$t('alarm.alarmLog.label_end_date')" align="right" :default-time="['00:00:00', '23:59:59']" @change="dateChangeHandler" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('alarm.alarmLog.label_is_alert') + ':'">
              <el-select v-model="form.verifyResult" :placeholder="$t('alarm.alarmLog.label_is_alert')">
                <el-option :label="$t('alarm.alarmLog.label_all')" value="" />
                <el-option :label="$t('alarm.alarmLog.label_yes')" value="ture" />
                <el-option :label="$t('alarm.alarmLog.label_no')" value="false" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item :label="$t('alarm.alarmLog.label_execute_result') + ':'">
              <el-select v-model="form.executeResult" :placeholder="$t('alarm.alarmLog.label_execute_result')">
                <el-option :label="$t('alarm.alarmLog.label_all')" value="" />
                <el-option :label="$t('alarm.alarmLog.label_success')" value="SUCCESS" />
                <el-option :label="$t('alarm.alarmLog.label_exception')" value="ERROR" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item :label="$t('alarm.alarmLog.label_alert_condition') + ':'">
              <el-select v-model="form.alert" :placeholder="$t('alarm.alarmLog.label_alert_condition')">
                <el-option :label="$t('alarm.alarmLog.label_all')" value="" />
                <el-option :label="$t('alarm.alarmLog.label_meet')" value="TRUE" />
                <el-option :label="$t('alarm.alarmLog.label_not_meet')" value="FALSE" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-button type="primary" @click="onSubmit">{{ $t('buttons.search') }}</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="alarmId" :label="$t('alarm.alarmLog.label_alarm_id')" width="80" align="center" />
      <el-table-column prop="cost" :label="$t('alarm.alarmLog.label_cost_millisecond')" align="center" />
      <el-table-column prop="exeStart" :label="$t('alarm.alarmLog.label_start_date')" width="180" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.exeStart|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="exeEnd" :label="$t('alarm.alarmLog.label_end_date')" width="180" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.exeEnd|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="executeResult" :label="$t('alarm.alarmLog.label_execute_result')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.executeResult === 'SUCCESS' ? $t('alarm.alarmLog.label_success') : $t('alarm.alarmLog.label_exception') }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="verifyResult" :label="$t('alarm.alarmLog.label_alert_condition')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.verifyResult === 'TRUE' ? $t('alarm.alarmLog.label_meet') : $t('alarm.alarmLog.label_not_meet') }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="alert" :label="$t('alarm.alarmLog.label_is_alert')" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.alert ? $t('alarm.alarmLog.label_yes') : $t('alarm.alarmLog.label_no') }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('alarm.alarmLog.label_action')" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="showMessage(scope.row)">{{ $t('alarm.alarmLog.text_detail_arrow') }}</el-button>
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
          text: this.$t('alarm.alarmLog.text_today'),
          onClick (picker) {
            const startMoment = moment().startOf('day')
            const endMoment = moment().endOf('day')
            picker.$emit('pick', [startMoment.toDate(), endMoment.toDate()])
          }
        },
        {
          text: this.$t('alarm.alarmLog.text_yesterday'),
          onClick (picker) {
            const end = moment().startOf('day').toDate()
            const start = moment().startOf('day').subtract(1, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: this.$t('alarm.alarmLog.text_the_day_before_yesterday'),
          onClick (picker) {
            const end = moment().startOf('day').subtract(1, 'day').toDate()
            const start = moment().startOf('day').subtract(2, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: this.$t('alarm.alarmLog.text_latest_three_days'),
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(3, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: this.$t('alarm.alarmLog.text_latest_seven_days'),
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(7, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        },
        {
          text: this.$t('alarm.alarmLog.text_latest_30_days'),
          onClick (picker) {
            const end = moment().endOf('day').toDate()
            const start = moment().startOf('day').subtract(30, 'day').toDate()
            picker.$emit('pick', [start, end])
          }
        }]
      },
      id: this.$route.query.id,
      datePickValue: [],
      list: null,
      rowcount: 0,
      listLoading: true,
      form: {
        alarmId: this.$route.query.id,
        startTime: null,
        endTime: null,
        verifyResult: null,
        executeResult: null,
        alert: null,
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
      logApi.findAlarmLog(this.form)
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
      this.$alert('<pre style="overflow: auto">' + row.message + '</pre>', '消息内容', {
        dangerouslyUseHTMLString: true
      })
    }
  }
}
</script>
