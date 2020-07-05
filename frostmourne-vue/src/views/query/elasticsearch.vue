<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.dataName" placeholder="选择数据名" clearable style="width: 150px" class="filter-item" @change="dataNameChangeHandler">
        <el-option v-for="item in dataNameList" :key="item.data_name" :label="item.display_name" :value="item.data_name" />
      </el-select>
      <el-select v-model="form.sortOrder" placeholder="选择排序" clearable style="width: 120px" class="filter-item">
        <el-option label="时间倒序" value="desc" />
        <el-option label="时间正序" value="asc" />
      </el-select>
      <el-select v-model="form.intervalInSeconds" placeholder="统计间隔" clearable style="width: 120px" class="filter-item">
        <el-option label="自动" value="0" />
        <el-option label="1小时" value="3600" />
        <el-option label="1天" value="86400" />
        <el-option label="5分钟" value="300" />
        <el-option label="15分钟" value="900" />
        <el-option label="30分钟" value="1800" />
      </el-select>
      <el-date-picker
        v-model="datePickValue"
        class="filter-item"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="right"
        :default-time="['00:00:00', '23:59:59']"
        @change="dateChangeHandler" />
      <el-input v-model="form.esQuery" clearable placeholder="输入查询语句。如: Team: dealer.arch" style="width: 700px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="filter-item el-icon-plus" type="primary" @click="addAlarm">添加监控</el-button>
      <el-button class="filter-item el-icon-share" type="primary" @click="share">分享</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="download">下载</el-button>
    </div>

    <figure>
      <v-chart :options="charOptions" theme="ovilia-green" autoresize @click="handleClick" />
    </figure>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item v-for="field in fields" :key="field" :label="field">
              <span>{{ props.row[field] }}</span>
            </el-form-item>
          </el-form>
          <!--<div>
            <vue-json-pretty :data="props.row"></vue-json-pretty>
          </div>-->
        </template>
      </el-table-column>
      <el-table-column type="index" width="50" />
      <el-table-column :prop="timestampField" label="时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row[timestampField] | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column v-for="head in headFields" :key="head" :label="head" :prop="head" />
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
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/title.js'
import 'echarts/theme/dark'
import theme from './_theme.json'
// registering custom theme
ECharts.registerTheme('ovilia-green', theme)

export default {
  filters: {
    timeFormat (value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  components: {
    'v-chart': ECharts
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
          text: '最近七天',
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
        esQuery: '*',
        sortOrder: 'desc',
        intervalInSeconds: '0'
      },
      datePickValue: [],
      dataNameList: [],
      selectedDataName: null,
      charOptions: {
        title: {
          left: 'center',
          text: '总数:0'
        },
        tooltip: { trigger: 'axis' },
        xAxis: { data: ['00:00:00', '01:00:00', '02:00:00', '03:00:00', '04:00:00', '05:00:00', '06:00:00', '07:00:00', '08:00:00', '09:00:00', '10:00:00', '11:00:00', '12:00:00', '13:00:00', '14:00:00', '15:00:00', '16:00:00', '17:00:00', '18:00:00', '19:00:00', '20:00:00', '21:00:00', '22:00:00', '23:00:00'] },
        yAxis: {},
        series: [{ name: '总数', type: 'bar', data: [] }]
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
    dataApi.findDataNameByType('elasticsearch').then(response => {
      this.dataNameList = response.result
    })

    if (this.$route.query.esQuery) {
      this.form.esQuery = this.$route.query.esQuery
      this.form.dataName = this.$route.query.dataName
      this.form.startTime = moment(this.$route.query.startTime).toDate()
      this.form.endTime = moment(this.$route.query.endTime).toDate()
      this.datePickValue[0] = this.form.startTime
      this.datePickValue[1] = this.form.endTime

      this.search()
    }
  },
  methods: {
    handleClick () {
      console.log('click from echars')
    },
    handleZrClick () {
      console.log('click from zrender')
    },
    dateChangeHandler (value) {
      this.form.startTime = value[0]
      this.form.endTime = value[1]
    },
    search () {
      if (this.form.dataName == null) {
        this.$message({ type: 'warning', message: '请先选择一个数据名', duration: 2000 })
        return
      }

      if (this.form.esQuery == null || this.form.esQuery == '') {
        this.$message({ type: 'warning', message: '查询语句不能为空', duration: 2000 })
        return
      }

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
        this.charData(response.result.statItem)
      })
    },
    share () {
      var sb = []
      sb.push('dataName=' + this.form.dataName)
      sb.push('startTime=' + this.form.startTime.toISOString())
      sb.push('endTime=' + this.form.endTime.toISOString())
      sb.push('esQuery=' + this.form.esQuery)
      sb.push('sortOrder=' + this.form.sortOrder)
      sb.push('intervalInSeconds=' + this.form.intervalInSeconds)
      const uri = window.location.href.indexOf('?') >= 0
        ? window.location.href.substr(0, window.location.href.indexOf('?'))
        : window.location.href
      const url = encodeURI(uri + '?' + sb.join('&'))
      dataQueryApi.shortenLink(url).then(response => {
        const shorten = (response.result === '' || response.result == null) ? url : response.result
        this.copyToClipboard(shorten)
      })
    },
    copyToClipboard (message) {
      var textArea = document.createElement('textarea')
      textArea.style.position = 'fixed'
      textArea.style.top = '0'
      textArea.style.left = '0'
      textArea.style.width = '2em'
      textArea.style.height = '2em'
      textArea.style.padding = '0'
      textArea.style.border = 'none'
      textArea.style.outline = 'none'
      textArea.style.boxShadow = 'none'
      textArea.style.background = 'transparent'
      textArea.value = message
      document.body.appendChild(textArea)
      textArea.select()
      if (document.execCommand('copy')) {
        this.$message({ message: '已复制: ' + message, type: 'success', duration: 4000, offset: 60 })
      }

      document.body.removeChild(textArea)
    },
    download () {
      dataQueryApi.downloadData(this.form).then(response => {
        this.forceFileDownload(response)
      }).catch(error => {
        console.log(error)
      })
    },
    forceFileDownload (response) {
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const link = document.createElement('a')
      link.href = url
      var fileName = response.headers['attachment-filename']
      link.setAttribute('download', fileName) // or any other extension
      document.body.appendChild(link)
      link.click()
      URL.revokeObjectURL(link.href) // 释放URL 对象
      document.body.removeChild(link)
    },
    charData (statItem) {
      const min = formatJsonDate(statItem.keys[0], 'yyyy-MM-dd hh:mm:ss')
      const max = formatJsonDate(statItem.keys[statItem.keys.length - 1], 'yyyy-MM-dd hh:mm:ss')
      let format = 'yyyy-MM-dd'
      if (min.substr(0, 10) === max.substr(0, 10)) {
        format = 'hh:mm'
      } else if (min.substr(0, 7) === max.substr(0, 7)) {
        format = 'MM-dd hh:mm'
      }
      this.charOptions.xAxis.data = statItem.keys.map(e => formatJsonDate(e, format))
      this.charOptions.title.text = `${formatJsonDate(this.form.startTime, 'yyyy-MM-dd hh:mm:ss')} 至 ${formatJsonDate(this.form.endTime, 'yyyy-MM-dd hh:mm:ss')}  总数:${this.total}`
      this.charOptions.series = [{ name: '次数', type: 'bar', data: statItem.values }]
    },
    dataNameChangeHandler (selectedName) {
      this.selectedDataName = this.dataNameList.filter(d => d.data_name == selectedName)[0]
    },
    loadMore () {
      this.listLoading = true
      dataQueryApi.elasticsearchData(this.form).then(response => {
        for (var i = 0; i < response.result.logs.length; i++) {
          this.list.push(response.result.logs[i])
        }
        this.form.scrollId = response.result.scrollId
        this.listLoading = false
      })
    },
    addAlarm () {
      if (this.form.dataName == null) {
        this.$message({ type: 'warning', message: '请先选择一个数据名', duration: 2000 })
        return
      }
      if (this.form.esQuery == null || this.form.esQuery == '') {
        this.$message({ type: 'warning', message: '查询语句不能为空', duration: 2000 })
        return
      }
      this.selectedDataName = this.dataNameList.filter(d => d.data_name == this.form.dataName)[0]
      this.$router.push({
        name: 'alarm-edit',
        query: {
          datasource_type: this.selectedDataName.datasource_type,
          datasource_id: this.selectedDataName.data_source_id,
          data_name: this.selectedDataName.data_name,
          query_string: this.form.esQuery
        }
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
  white-space: PRE-WRAP;
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
figure {
  display: inline-block;
  position: relative;
  margin: 2em auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  /* box-shadow: 0 0 45px rgba(0, 0, 0, 0.2); */
  padding: 1.5em 2em;
  min-width: calc(40vw + 4em);
  width: 100%;
}
.echarts {
  width: 100%;
  /* height: 100%; */
}
</style>
