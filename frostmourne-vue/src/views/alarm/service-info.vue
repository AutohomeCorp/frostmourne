<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="searchForm.serviceName" clearable placeholder="输入名称,支持模糊查询" style="width: 300px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="onSubmit">查询</el-button>
      <el-button class="filter-item" icon="el-icon-edit" @click="editItem(null)">新增</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="serviceName" label="服务名称" align="center" />
      <el-table-column prop="remark" label="备注" align="center" />
      <el-table-column prop="createAt" label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createAt | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="modifyAt" label="操作时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modifyAt | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="editItem(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block mt-paginate">
      <el-row>
        <el-col :span="8" :offset="6">
          <div class="grid-content">
            <el-pagination background layout="total, prev, pager, next" :page-size="searchForm.pageSize" :total="rowcount" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>

    <el-dialog title="服务信息" :visible.sync="dialogEditVisible" width="50%" @close="closeEditForm">
      <el-form ref="editForm" :model="itemData" :rules="rule" label-width="120px" label-suffix="：">
        <el-form-item label="服务名称" prop="serviceName">
          <el-input v-model="itemData.serviceName" :maxlength="100" :disabled="!dialogEdit" autocomplete="off" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="itemData.remark" type="textarea" :rows="10" :maxlength="5000" :disabled="!dialogEdit" autocomplete="off" placeholder="" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-show="dialogEdit" type="primary" @click="submitSaveItem">保 存</el-button>
        <el-button @click="dialogEditVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import serviceinfoApi from '@/api/service-info.js'
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
      searchForm: {
        serviceName: null,
        pageIndex: 1,
        pageSize: 10
      },
      itemData: {
        id: 0,
        serviceName: '',
        remark: ''
      },
      dialogEditVisible: false,
      dialogEdit: false,
      rule: {
        serviceName: [
          { required: true, message: '请输入服务名称', target: 'blur' },
          { max: 50, message: '长度不能超过50', target: 'blur' }
        ],
        remark: [
          { max: 200, message: '长度不能超过200', target: 'blur' }
        ]
      }
    }
  },
  created () {
    this.fetchData()
  },
  methods: {
    onSubmit () {
      this.fetchData()
    },
    onStatusChange () {
      this.searchForm.pageIndex = 1
      this.fetchData()
    },
    onPrevClick () {
      this.searchForm.pageIndex--
      this.fetchData()
    },
    onNextClick () {
      this.searchForm.pageIndex++
      this.fetchData()
    },
    onCurrentChange (curr) {
      this.searchForm.pageIndex = curr
      this.fetchData()
    },
    fetchData () {
      this.listLoading = true
      serviceinfoApi.findServiceInfo(this.searchForm)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
    },
    closeEditForm () {
      this.$refs.editForm.resetFields()
    },
    editItem (row) {
      this.readRowData(row)
      this.dialogEditVisible = true
      this.dialogEdit = true
    },
    readRowData (row) {
      if (row == null) {
        this.itemData.id = 0
        this.itemData.serviceName = ''
        this.itemData.remark = ''
      } else {
        this.itemData.id = row.id
        this.itemData.serviceName = row.serviceName
        this.itemData.remark = row.remark
      }
    },
    submitSaveItem () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm('是否确定保存?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'info'
          }).then(() => {
            serviceinfoApi.saveServiceInfo(this.itemData).then(response => {
              this.dialogEditVisible = false
              this.fetchData()
            })
          }).catch(e => e)
        } else {
          return false
        }
      })
    }
  }
}
</script>
