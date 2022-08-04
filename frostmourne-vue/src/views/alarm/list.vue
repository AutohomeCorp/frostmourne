<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="form.alarmId" :placeholder="$t('alarm.list.input_id')" clearable style="width: 150px;" class="filter-item" />
      <el-input v-model="form.name" clearable :placeholder="$t('alarm.list.input_name')" style="width: 300px;" class="filter-item" />
      <el-select v-model="form.status" :placeholder="$t('alarm.list.input_status')" clearable class="filter-item" @change="onStatusChange">
        <el-option v-for="item in alarmStatus" :key="item.value" :label="item.text" :value="item.value" />
      </el-select>
      <el-select v-model="form.teamName" placeholder="choose team" style="width: 200px" class="filter-item" @change="teamChangeHanlder">
        <el-option v-for="item in teamList" :key="item.name" :label="item.fullName" :value="item.name" />
      </el-select>
      <el-select v-model="form.serviceId" filterable remote reserve-keyword clearable :placeholder="$t('alarm.list.input_service')" class="filter-item"
                 :remote-method="loadServiceOptions" :loading="serviceOptionsLoading" @change="onServiceInfoChange">
        <el-option v-for="item in ServiceOptions" :key="item.id" :label="item.serviceName" :value="item.id" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="onSubmit">{{ $t('buttons.search') }}</el-button>
      <el-button class="filter-item" icon="el-icon-edit" @click="goEdit(null)">{{ $t('alarm.list.add') }}</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" :header-cell-style="{'text-align':'center'}" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="alarmName" :label="$t('alarm.list.header_alarm_name')" align="left" />
      <el-table-column prop="alarmType" :label="$t('alarm.list.header_alarm_type')" width="160" align="center" />
      <el-table-column prop="cron" label="Cron" width="120" align="center" />
      <el-table-column prop="status" :label="$t('alarm.list.header_is_open')" width="100" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" active-value="OPEN" inactive-value="CLOSE" @change="changeStatus(scope.row)" />
        </template>
      </el-table-column>
      <el-table-column :label="$t('alarm.list.header_last_execute_result')" width="110" class-name="status-col" align="center">
        <template slot-scope="scope">
          <el-tag size="medium" :type="scope.row.executeResult|executeResultFilter">{{ scope.row.executeResult }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="executeAt" :label="$t('alarm.list.header_last_execute_time')" width="160" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.executeAt|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('alarm.list.header_next_trigger_time')" width="160" align="center">
        <template slot-scope="scope">
          <el-popover
            placement="bottom"
            width="300"
            @show="nextTriggerTime(scope.row)"
          >
            <h5 v-html="triggerNextTimes" />
            <el-button slot="reference" size="small">{{ $t('buttons.view') }}</el-button>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column prop="ownerKey" :label="$t('alarm.list.header_owner_object')" width="160" align="center" />
      <el-table-column prop="modifier" :label="$t('alarm.list.header_modifier')" width="160" align="center" />
      <el-table-column prop="modifyAt" :label="$t('alarm.list.header_last_modify_time')" width="160" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modifyAt|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('alarm.list.header_action')" width="300" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="goEdit(scope.row.id)">{{ $t('buttons.edit') }}</el-button>
          <el-button size="mini" icon="el-icon-edit" @click="run(scope.row.id)">{{ $t('buttons.onceRun') }}</el-button>
          <el-button size="mini" icon="el-icon-edit" @click="remove(scope.row.id)">{{ $t('buttons.delete') }}</el-button>
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
import alarmApi from '@/api/alarm.js'
import adminApi from '@/api/admin.js'
import serviceinfoApi from '@/api/service-info.js'
import { teams, getInfo } from '@/api/user'
import { formatJsonDate } from '@/utils/datetime.js'

export default {
  // eslint-disable-next-line vue/name-property-casing
  name: 'alarm-list',
  filters: {
    statusFilter (status) {
      return status !== 'OPEN' ? 'info' : ''
    },
    executeResultFilter (result) {
      if (!result) {
        return ''
      }
      const resultMap = {
        WAITING: 'info',
        SUCCESS: 'success',
        ERROR: 'danger'
      }
      return resultMap[result]
    },
    timeFormat (value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  data () {
    return {
      list: null,
      rowcount: 0,
      listLoading: true,
      form: {
        alarmId: null,
        name: null,
        teamName: null,
        status: null,
        pageIndex: 1,
        pageSize: 10
      },
      alarmStatus: [
        { value: '', text: this.$t('alarm.list.input_status') },
        { value: 'OPEN', text: this.$t('alarm.list.input_status_open') },
        { value: 'CLOSE', text: this.$t('alarm.list.input_status_close') }
      ],
      triggerNextTimes: '',
      teamList: [],
      serviceOptionsLoading: false,
      ServiceOptions: []
    }
  },
  created () {
    getInfo().then(response => {
      this.form.teamName = response.result.teamName
      this.fetchData()
    })

    teams().then(response => {
      this.teamList = response.result
    })

    this.loadServiceOptions()
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
    onServiceInfoChange () {
      this.form.pageIndex = 1
      this.fetchData()
    },
    nextTriggerTime(alarm) {
      alarmApi.nextTriggerTime(alarm.cron).then(response => {
        const content = response.result
        this.triggerNextTimes = content.join('<br>')
      })
    },
    changeStatus (alarm) {
      const message = `id=${alarm.id} Success ${alarm.status}！`
      if (alarm.status === 'OPEN') {
        adminApi.open(alarm.id).then(response => this.$message({ type: 'success', message: message, duration: 2000 }))
      } else {
        adminApi.close(alarm.id).then(response => this.$message({ type: 'success', message: message, duration: 2000 }))
      }
    },
    fetchData () {
      this.listLoading = true
      adminApi.getList(this.form.alarmId, this.form.name, this.form.teamName, this.form.status, this.form.serviceId, this.form.pageIndex, this.form.pageSize)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
    },
    goEdit (id) {
      this.$router.push({ name: 'alarm-edit', query: { id: id } })
    },
    run (id) {
      alarmApi.run(id).then(response => {
        this.$alert('<pre style="overflow: auto">' + response.result + '</pre>', '执行成功', {
          dangerouslyUseHTMLString: true
        })
        this.fetchData()
      })
    },
    remove (id) {
      this.$confirm('此操作将删除监控, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        adminApi.delete(id).then(response => {
          this.$message({ type: 'success', message: '删除成功', duration: 2000 })
          this.fetchData()
        })
      })
    },
    teamChangeHanlder () {
      this.form.pageIndex = 1
      this.fetchData()
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
          this.ServiceOptions = response.result.list || []
          this.serviceOptionsLoading = false
        })
        .catch(e => {})
    }
  }
}
</script>
