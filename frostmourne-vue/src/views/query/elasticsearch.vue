<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.dataName" placeholder="选择数据名" @change="dataNameChangeHandler" clearable style="width: 150px" class="filter-item">
        <el-option v-for="item in dataNameList" :key="item.data_name" :label="item.display_name" :value="item.data_name" />
      </el-select>
      <el-select v-model="form.sortOrder" placeholder="选择排序" clearable style="width: 120px" class="filter-item">
        <el-option label="时间倒序" value="desc"></el-option>
        <el-option label="时间正序" value="asc"></el-option>
      </el-select>
      <el-select v-model="form.intervalInSeconds" placeholder="统计间隔" clearable style="width: 120px" class="filter-item">
        <el-option label="自动" value="0"></el-option>
        <el-option label="1小时" value="3600"></el-option>
        <el-option label="1天" value="86400"></el-option>
      </el-select>
      <el-date-picker v-model="datePickValue" type="datetimerange" :picker-options="pickerOptions" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right" @change="dateChangeHandler" :default-time="['00:00:00', '23:59:59']"></el-date-picker>
      <el-input v-model="form.esQuery" clearable placeholder="输入查询语句。如: Team: dealer.arch" style="width: 700px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">
        查询
      </el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">
        分享
      </el-button>
    </div>

    <el-row style="text-align: center; height: 30px">
      <span>总数: <strong>{{ total }}</strong></span>
    </el-row>
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item v-for="field in fields" :key="field" :label="field">
              <span>
                {{ props.row[field] }}
              </span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column type="index" width="50">
      </el-table-column>
      <el-table-column :prop="timestampField" label="时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[timestampField]|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column v-for="head in headFields" :key="head" :label="head" :prop="head"></el-table-column>
    </el-table>
    <el-row style="text-align: center">
      <el-button type="primary" @click="loadMore">加载更多>></el-button>
    </el-row>
  </div>

</template>

<script>
import dataQueryApi from '@/api/data-query.js'
import dataApi from '@/api/data.js'
import { formatJsonDate } from '@/utils/datetime.js'
import moment from 'moment'

export default {
  filters: {
    timeFormat(value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            const startMoment = moment().startOf('day')
            const endMoment = moment().endOf('day')
            picker.$emit('pick', [startMoment.toDate(), endMoment.toDate()]);
          }
        },
        {
          text: '昨天',
          onClick(picker) {
            const end = moment().startOf('day').toDate();
            const start = moment().startOf('day').subtract(1, 'day').toDate();
            picker.$emit('pick', [start, end]);
          }
        },
        {
          text: '前天',
          onClick(picker) {
            const end = moment().startOf('day').subtract(1, 'day').toDate();
            const start = moment().startOf('day').subtract(2, 'day').toDate();
            picker.$emit('pick', [start, end]);
          }
        },
        {
          text: '最近三天',
          onClick(picker) {
            const end = moment().endOf('day').toDate();
            const start = moment().startOf('day').subtract(3, 'day').toDate();
            picker.$emit('pick', [start, end]);
          }
        },
        {
          text: '最近七天',
          onClick(picker) {
            const end = moment().endOf('day').toDate();
            const start = moment().startOf('day').subtract(7, 'day').toDate();
            picker.$emit('pick', [start, end]);
          }
        },
        {
          text: '最近一月',
          onClick(picker) {
            const end = moment().endOf('day').toDate();
            const start = moment().startOf('day').subtract(30, 'day').toDate();
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      list: [],
      total: 0,
      listLoading: false,
      fields: [],
      headFields: [],
      timestampField: null,
      form: {
        scrollId: null,
        dataName: null,
        startTime: null,
        endTime: null,
        esQuery: null,
        sortOrder: 'desc',
        intervalInSeconds: "0"
      },
      datePickValue: [],
      dataNameList: []
    }
  },
  created() {
    const startMoment = moment().startOf('day')
    const endMoment = moment().endOf('day')
    this.datePickValue[0] = startMoment.toDate()
    this.datePickValue[1] = endMoment.toDate()
    this.form.startTime = startMoment.toDate()
    this.form.endTime = endMoment.toDate()
    dataApi.findDataNameByType('elasticsearch').then(response => {
      this.dataNameList = response.result
    })
  },
  methods: {
    dateChangeHandler(value) {
      this.form.startTime = value[0]
      this.form.endTime = value[1]
    },
    search() {
      this.listLoading = true
      this.form.scrollId = null
      this.list = []
      dataQueryApi.elasticsearchData(this.form).then(response => {
        this.list = response.result.logs
        this.timestampField = response.result.timestampField
        this.headFields = response.result.headFields
        this.fields = response.result.fields
        this.form.scrollId = response.result.scrollId
        this.listLoading = false
        this.total = response.result.total
      })
    },
    dataNameChangeHandler() {

    },
    loadMore() {
      this.listLoading = true
      dataQueryApi.elasticsearchData(this.form).then(response => {
        for (var i = 0; i < response.result.logs.length; i++) {
          this.list.push(response.result.logs[i])
        }
        this.form.scrollId = response.result.scrollId
        this.listLoading = false
      })
    }
  }
}
</script>

<style>
.filter-container {
  padding-bottom: 20px;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 150px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
  color: green;
}
</style>