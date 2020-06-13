<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.datasource_type" placeholder="选择数据类型" clearable style="width: 190px" class="filter-item" @change="formSourceTypeChangeHandler">
        <el-option label="elasticsearch" value="elasticsearch" />
      </el-select>
      <el-select v-model="form.data_source_id" placeholder="选择数据源" class="filter-item">
        <el-option v-for="item in formDatasourceList" :key="item.datasource_name" :label="item.datasource_name" :value="item.id" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">新增</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="data_name" label="名称" align="center" />
      <el-table-column prop="display_name" label="说明" align="center" />
      <el-table-column prop="datasource_type" label="类型" align="center" />
      <el-table-column prop="timestamp_field" label="时间字段" align="center" />
      <el-table-column prop="modify_at" label="最近修改" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modify_at | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="modifier" label="修改人" align="center" />
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

    <el-dialog title="保存数据名" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="editData">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="editData.data_name" :disabled="disableEdit" autocomplete="off" />
        </el-form-item>

        <el-form-item label="类型" :label-width="formLabelWidth">
          <el-select
            v-model="editData.datasource_type"
            :disabled="disableEdit"
            placeholder="类型"
            clearable
            style="width: 190px"
            class="filter-item"
            @change="dialogSourceTypeChangeHandler"
          >
            <el-option label="elasticsearch" value="elasticsearch" />
          </el-select>
        </el-form-item>

        <el-form-item label="数据源" :label-width="formLabelWidth">
          <el-select v-model="editData.data_source_id" :disabled="disableEdit" placeholder="选择数据源" class="filter-item">
            <el-option v-for="item in dialogDatasourceList" :key="item.datasource_name" :label="item.datasource_name" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="说明" :label-width="formLabelWidth">
          <el-input v-model="editData.display_name" autocomplete="off" />
        </el-form-item>

        <el-form-item label="时间字段" :label-width="formLabelWidth">
          <el-input v-model="editData.timestamp_field" autocomplete="off" />
        </el-form-item>
        <el-form-item v-if="editData.datasource_type == 'elasticsearch'" label="索引前缀" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.indexPrefix" placeholder="applog-" autocomplete="off" />
        </el-form-item>

        <el-form-item v-if="editData.datasource_type == 'elasticsearch'" label="时间后缀" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.timePattern" placeholder="可空。举例: yyyyMMdd。最小单位到天, 小于天请用*表示" autocomplete="off" />
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
    timeFormat(value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    }
  },
  data() {
    return {
      list: null,
      rowcount: 0,
      listLoading: true,
      formDatasourceList: [],
      form: {
        pageIndex: 1,
        pageSize: 20,
        data_name: '',
        datasource_type: '',
        data_source_id: null
      },
      editData: {
        id: 0,
        data_name: '',
        data_source_id: null,
        datasource_type: '',
        display_name: '',
        timestamp_field: '',
        settings: {}
      },
      dialogDatasourceList: [],
      formLabelWidth: '80px',
      dialogFormVisible: false,
      disableEdit: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      dataApi.findDataName(this.form.pageIndex, this.form.pageSize, this.form.datasource_type, this.form.data_source_id)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
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
    edit(row) {
      if (row != null) {
        console.log(row)
        this.disableEdit = true
        dataApi.findDataSourceByType(row.datasource_type).then(response => {
          this.dialogDatasourceList = response.result
        })
        this.editData.id = row.id
        this.editData.data_name = row.data_name
        this.editData.datasource_type = row.datasource_type
        this.editData.timestamp_field = row.timestamp_field
        this.editData.display_name = row.display_name
        this.editData.data_source_id = row.data_source_id
        this.editData.settings = row.settings
        this.disableTypeSelect = true
      } else {
        this.disableEdit = false
        this.editData = {
          id: 0,
          data_name: null,
          data_source_id: null,
          datasource_type: '',
          display_name: '',
          timestamp_field: '',
          settings: {}
        }
        this.disableTypeSelect = false
      }

      this.dialogFormVisible = true
    },
    search() {
      // console.log(this.form)
      this.form.pageIndex = 1
      this.fetchData()
    },
    save() {
      dataApi.saveDataName(this.editData).then(response => {
        this.dialogFormVisible = false
        this.fetchData()
      })
    },
    formSourceTypeChangeHandler(selectedValue) {
      this.form.data_source_id = null
      if (selectedValue) {
        dataApi.findDataSourceByType(selectedValue).then(response => {
          this.formDatasourceList = response.result
        })
      } else {
        this.formDatasourceList = []
      }
    },
    dialogSourceTypeChangeHandler(selectedValue) {
      this.editData.data_source_id = null
      if (selectedValue) {
        dataApi.findDataSourceByType(selectedValue).then(response => {
          this.dialogDatasourceList = response.result
        })
      } else {
        this.dialogDatasourceList = []
      }
    },
    remove(row) {
      this.$confirm('此操作将删除数据名, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        dataApi.removeDataName(row.id).then(response => {
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

<style>
.filter-container {
  padding-bottom: 10px;
}
</style>
