<template>
  <div class="app-container">
    <!--<div class="block">
      <el-form ref="form" :model="form" label-width="100px" label-position="center">
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item label="报警名称">
              <el-input v-model="form.name" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="报警状态">
              <el-select v-model="form.status" placeholder="报警状态" @change="onStatusChange">
                <el-option v-for="item in alarmStatus" :key="item.id" :label="item.text" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-col :span="10">
            <el-form-item>
              <el-row>
                <el-col :span="12">
                  <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-col>
                <el-col :span="12">
                  <el-button icon="el-icon-edit" @click="goEdit(null)">添加报警</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    -->

    <div class="filter-container">
      <el-input v-model="form.alarmId" placeholder="输入id" clearable style="width: 150px;" class="filter-item" />
      <el-input v-model="form.name" clearable placeholder="输入名称,支持模糊查询" style="width: 300px;" class="filter-item" />
      <el-select v-model="form.status" placeholder="监控状态" clearable class="filter-item" @change="onStatusChange">
        <el-option v-for="item in alarmStatus" :key="item.value" :label="item.text" :value="item.value" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
      <el-button class="filter-item" icon="el-icon-edit" @click="goEdit(null)">添加报警</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="alarm_name" label="监控名称" />
      <el-table-column prop="description" label="监控描述" />
      <el-table-column prop="alarm_type" label="监控类型" width="150" align="center" />
      <el-table-column prop="cron" label="cron" width="110" align="center" />
      <el-table-column prop="status" label="是否开启" width="100" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" active-value="OPEN" inactive-value="CLOSE" @change="changeStatus(scope.row)" />
        </template>
      </el-table-column>
      <el-table-column label="最后执行结果" width="110" class-name="status-col" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.execute_result|executeResultFilter">{{ scope.row.execute_result }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="execute_at" label="最后执行时间" width="160" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.execute_at|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="owner_key" label="owner_key" width="100" align="center" />
      <el-table-column prop="modifier" label="最后修改人" width="100" align="center" />
      <el-table-column prop="modify_at" label="最后修改时间" width="160" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modify_at|timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="goEdit(scope.row.id)">编辑</el-button>
          <el-button size="mini" icon="el-icon-edit" @click="run(scope.row.id)">运行</el-button>
          <el-button size="mini" icon="el-icon-edit" @click="remove(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block mt-paginate">
      <el-row>
        <el-col :span="8" :offset="6">
          <div class="grid-content">
            <el-pagination background layout="total, prev, pager, next" :total="rowcount" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import alarmApi from '@/api/alarm.js'
import adminApi from '@/api/admin.js'
import { formatJsonDate } from '@/utils/datetime.js'

export default {
  filters: {
    statusFilter(status) {
      return status !== 'OPEN' ? 'info' : ''
    },
    executeResultFilter(result) {
      if (!result) {
        return ''
      }
      const resultMap = {
        WAITING: 'success',
        ERROR: 'danger'
      }
      return resultMap[result]
    },
    timeFormat(value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  data() {
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
        pageSize: 20
      },
      alarmStatus: [
        { value: '', text: '监控状态' },
        { value: 'OPEN', text: '开启' },
        { value: 'CLOSE', text: '关闭' }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    onSubmit() {
      this.fetchData()
    },
    onStatusChange() {
      this.form.pageIndex = 1
      this.fetchData()
    },
    onPrevClick() {
      this.form.pageIndex--
      this.fetchData()
    },
    onNextClick() {
      this.form.pageIndex++
      this.fetchData()
    },
    onCurrentChange(curr) {
      this.form.pageIndex = curr
      this.fetchData()
    },
    changeStatus(alarm) {
      const message = `id=${alarm.id} 监控报警${alarm.status}成功！`
      if (alarm.status === 'OPEN') {
        adminApi.open(alarm.id).then(response => this.$message({ type: 'success', message: message, duration: 2000 }))
      } else {
        adminApi.close(alarm.id).then(response => this.$message({ type: 'success', message: message, duration: 2000 }))
      }
    },
    fetchData() {
      this.listLoading = true
      adminApi.getList(this.form.alarmId, this.form.name, this.form.groupId, this.form.status, this.form.pageIndex, this.form.pageSize)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
    },
    goEdit(id) {
      this.$router.push({ name: 'alarm-edit', query: { id: id } })
    },
    run(id) {
      alarmApi.run(id).then(response => {
        this.$alert('<pre style="overflow: auto">' + response.result + '</pre>', '执行成功', {
          dangerouslyUseHTMLString: true
        })
      })
    },
    remove(id) {
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
    }
  }
}
</script>
