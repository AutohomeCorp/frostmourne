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
            <el-form-item label="是否报警:">
              <el-select v-model="form.verifyResult" placeholder="是否报警">
                <el-option label="全部" value="" />
                <el-option label="TRUE" value="TRUE" />
                <el-option label="FALSE" value="FALSE" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item label="执行结果:">
              <el-select v-model="form.executeResult" placeholder="执行结果">
                <el-option label="全部" value="" />
                <el-option label="SUCCESS" value="SUCCESS" />
                <el-option label="FAIL" value="FAIL" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="alarm_id" label="监控id" align="center" />
      <el-table-column prop="cost" label="耗时(毫秒)" align="center" />
      <el-table-column prop="exe_start" label="开始时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.exe_start|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="exe_end" label="结束时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.exe_end|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="execute_result" label="执行结果" align="center" />
      <el-table-column prop="verify_result" label="是否报警" align="center" />
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
      datePickValue: [],
      list: null,
      rowcount: 0,
      listLoading: true,
      form: {
        alarmId: null,
        startTime: null,
        endTime: null,
        verifyResult: null,
        executeResult: null,
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
