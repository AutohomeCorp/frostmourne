<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.datasource_type" placeholder="选择数据类型" clearable style="width: 190px" class="filter-item">
        <el-option label="elasticsearch" value="elasticsearch" />
      </el-select>
      <!-- <el-input v-model="form.datasource_name" placeholder="名称" style="width: 300px;" class="filter-item" /> -->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">新增</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="datasource_name" label="名称" align="center" />
      <el-table-column prop="datasource_type" label="类型" align="center" />
      <el-table-column prop="service_address" label="服务地址" align="center" />
      <el-table-column prop="modify_at" label="最近修改时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modify_at | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="creator" label="创建人" align="center" />
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
          <el-button size="mini" icon="el-icon-edit" @click="remove(scope.row)">删除</el-button>
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

    <el-dialog title="保存数据源" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="editData">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="editData.datasource_name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="类型" :label-width="formLabelWidth">
          <el-select v-model="editData.datasource_type" :disabled="disableTypeSelect" placeholder="数据源类型">
            <el-option label="elasticsearch" value="elasticsearch" />
          </el-select>
        </el-form-item>
        <el-form-item label="服务地址" :label-width="formLabelWidth">
          <el-input v-model="editData.service_address" autocomplete="off" placeholder="例如：127.0.0.1:9200，多地址英文逗号分隔" />
          <el-tooltip content="地址更新后，下次重启后才生效" placement="bottom">
            <i class="el-icon-question" />
          </el-tooltip>
        </el-form-item>
        <el-form-item v-if="editData.datasource_type == 'elasticsearch'" label="认证用户" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.username" placeholder="无认证不需要填写" autocomplete="off" />
        </el-form-item>
        <el-form-item v-if="editData.datasource_type == 'elasticsearch'" label="密码" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.password" placeholder="无认证不需要填写" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import dataApi from '@/api/data.js'
import { formatJsonDate } from '@/utils/datetime.js'

export default {
  filters: {
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
        pageIndex: 1,
        pageSize: 20,
        datasource_name: '',
        datasource_type: ''
      },
      editData: {
        id: 0,
        datasource_name: '',
        datasource_type: '',
        service_address: '',
        settings: {}
      },
      formLabelWidth: '80px',
      dialogFormVisible: false,
      disableTypeSelect: true
    }
  },
  created () {
    this.fetchData()
  },
  methods: {
    fetchData () {
      this.listLoading = true
      dataApi
        .findDataSource(
          this.form.pageIndex,
          this.form.pageSize,
          this.form.datasource_type
        )
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
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
    edit (row) {
      console.log(row)
      if (row != null) {
        this.editData.id = row.id
        this.editData.datasource_name = row.datasource_name
        this.editData.datasource_type = row.datasource_type
        this.editData.service_address = row.service_address
        this.disableTypeSelect = true
        this.editData.settings = row.settings
      } else {
        this.editData = {
          settings: {}
        }
        this.disableTypeSelect = false
      }

      this.dialogFormVisible = true
    },
    search () {
      // console.log(this.form)
      this.form.pageIndex = 1
      this.fetchData()
    },
    save () {
      if (this.editData.datasource_type === 'elasticsearch') {
        if (this.editData.service_address.indexOf(':') < 0) {
          this.$message({ type: 'warning', message: 'elasticsearch地址必须指定端口', duration: 2000 })
          return
        }
      }
      dataApi.saveDataSource(this.editData).then(response => {
        this.dialogFormVisible = false
        this.fetchData()
      })
    },
    remove (row) {
      this.$confirm('此操作将删除数据源, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        dataApi.removeDataSource(row.id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功！',
            duration: 1500
          })
          this.fetchData()
        })
      })
    }
  }
}
</script>
