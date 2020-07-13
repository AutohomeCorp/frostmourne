<template>
  <div class="dashboard-editor-container">
    <panel-group :panel-data="panelData" @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="chartData" />
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
// import RaddarChart from './components/RaddarChart'
// import PieChart from './components/PieChart'
// import BarChart from './components/BarChart'
import moment from 'moment'
import { formatJsonDate } from '@/utils/datetime.js'
import statisticsApi from '@/api/statistics.js'

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart
  },
  data () {
    return {
      startMoment: moment().startOf('day').subtract(30, 'day').toDate(),
      endMoment: moment().endOf('day').toDate(),
      panelData: {
        taskCount: 0,
        executeCount: 0,
        alarmCount: 0,
        alertCount: 0
      },
      chartData: {
        title: '最近七天报警次数',
        xAxisData: [],
        lineData: []
      }
    }
  },
  created () {
    this.handlePanelData()
    this.handleSetLineChartData('alarm')
  },
  methods: {
    handlePanelData () {
      statisticsApi.panelData({ startTime: this.startMoment, endTime: this.endMoment })
        .then(response => {
          this.panelData = response.result
        })
    },
    handleSetLineChartData (type) {
      const condition = { startTime: this.startMoment, endTime: this.endMoment }
      if (type === 'alarm') {
        statisticsApi.alarmAggregation(condition)
          .then(response => this.setChartData(type, response.result))
      } else {
        statisticsApi.alertAggregation(condition)
          .then(response => this.setChartData(type, response.result))
      }
    },
    setChartData (type, result) {
      if (type === 'alarm') {
        this.chartData.title = '最近30天报警次数'
      } else {
        this.chartData.title = '最近30天消息次数'
      }

      this.fillDate(result)
      this.chartData.xAxisData = result.map(e => formatJsonDate(e.date, 'MM-dd'))
      this.chartData.lineData = result.map(e => e.count)
      console.log('xAxisData', this.chartData.xAxisData, 'lineData', this.chartData.lineData)
    },
    fillDate (result) {
      const xAxisData = []
      const lineData = []
      for (let day = this.startMoment; day <= this.endMoment; day = moment(day).startOf('day').add(1, 'day').toDate()) {
        xAxisData.push(formatJsonDate(day, 'MM-dd'))
        let count = result.find(e => e.date === day)
        if (!count) {
          count = 0
        }
        lineData.push(count)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
