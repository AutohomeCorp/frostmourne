<template>
  <div class="app-container">
    <div class="block">
      <el-form ref="form" :model="form" label-width="100px" label-position="center">
        <el-row :gutter="5">
          <el-col :span="8">
            <el-form-item label="模板名称:">
              <el-input v-model="form.templateName" placeholder="模板名称" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="模板类型:">
              <el-select v-model="form.templateType" placeholder="模板类型">
                <el-option label="全部" value="" />
                <el-option label="COMMON" value="COMMON" />
                <el-option label="DATA_NAME" value="DATA_NAME" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="addItem">新增</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="templateName" label="模板名称" align="center" />
      <el-table-column prop="templateType" label="模板类型" align="center" />
      <el-table-column prop="templateUnionName" label="关联名称" align="center" />
      <el-table-column prop="createAt" label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createAt | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="editItem(scope.row)">修改</el-button>
          <el-button size="mini" @click="showItem(scope.row)">详细</el-button>
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

    <el-dialog title="报警模板" :visible.sync="dialogEditVisible" width="50%">
      <el-form :model="itemData" label-width="120px" label-suffix="：">
        <el-form-item label="模板名称">
          <el-input v-model="itemData.templateName" :maxlength="100" :disabled="!dialogEdit" autocomplete="off" />
        </el-form-item>
        <el-form-item label="模板类型">
          <el-select v-model="itemData.templateType" :disabled="!dialogEdit" placeholder="模板类型" @change="templateTypeChangeHandler">
            <el-option label="==请选择==" value="" />
            <el-option label="COMMON" value="COMMON" />
            <el-option label="DATA_NAME" value="DATA_NAME" />
          </el-select>
        </el-form-item>
        <el-form-item v-show="showTemplateUnionCode" label="模板关联内容">
          <el-select v-model="itemData.templateUnionCode" :disabled="!dialogEdit" placeholder="模板关联内容">
            <el-option label="==请选择==" value="" />
            <el-option v-for="option in templateUnionCodeOptions" :key="option.data_name" :lebel="option.display_name" :value="option.data_name" />
          </el-select>
        </el-form-item>
        <el-form-item label="模板内容">
          <el-input v-model="itemData.content" type="textarea" :rows="10" :maxlength="5000" :disabled="!dialogEdit" autocomplete="off" placeholder="" />
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
import alerttemplateApi from '@/api/alert-template.js'
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
        templateName: null,
        templateType: null,
        pageIndex: 1,
        pageSize: 10
      },
      itemData: {
        id: 0,
        templateName: '',
        templateType: '',
        templateUnionCode: '',
        content: ''
      },
      dialogEditVisible: false,
      dialogEdit: false,
      showTemplateUnionCode: false,
      templateUnionCodeOptions: []
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
      alerttemplateApi.findAlertTemplate(this.form)
        .then(response => {
          this.list = response.result.list || []
          this.rowcount = response.result.rowcount
          this.listLoading = false
        })
    },
    addItem () {
      this.readRowData(null)
      this.dialogEditVisible = true
      this.dialogEdit = true
    },
    editItem (row) {
      this.readRowData(row)
      this.dialogEditVisible = true
      this.dialogEdit = true
    },
    showItem (row) {
      this.readRowData(row)
      this.dialogEditVisible = true
      this.dialogEdit = false
    },
    readRowData (row) {
      if (row == null) {
        this.itemData.id = 0
        this.itemData.templateName = ''
        this.itemData.templateType = ''
        this.itemData.templateUnionCode = ''
        this.itemData.content = ''
      } else {
        this.itemData.id = row.id
        this.itemData.templateName = row.templateName
        this.itemData.templateType = row.templateType
        this.itemData.templateUnionCode = row.templateUnionCode
        this.itemData.content = row.content
      }
    },
    templateTypeChangeHandler (newValue) {
      if (newValue === 'DATA_NAME') {
        this.showTemplateUnionCode = true
        // 加载数据名选项
        dataApi.findDataNameByType('')
          .then(response => {
            this.templateUnionCodeOptions = response.result
          })
      } else {
        this.templateUnionCodeOptions = []
        this.showTemplateUnionCode = false
      }
    },
    submitSaveItem () {
      if (this.itemData.templateName == null || this.itemData.templateName === '') {
        this.$message({ type: 'warning', message: '模板名称不能为空', duration: 2000 })
        return
      }
      if (this.itemData.templateType == null || this.itemData.templateType === '') {
        this.$message({ type: 'warning', message: '模板类型不能为空', duration: 2000 })
        return
      }
      if (this.itemData.templateType === 'DATA_NAME' && (this.itemData.templateUnionCode == null || this.itemData.templateUnionCode === '')) {
        this.$message({ type: 'warning', message: '模板关联内容不能为空', duration: 2000 })
        return
      }
      if (this.itemData.content == null || this.itemData.content === '') {
        this.$message({ type: 'warning', message: '模板内容不能为空', duration: 2000 })
        return
      }

      this.$confirm('是否确定保存?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        alerttemplateApi.saveAlertTemplate(this.itemData).then(response => {
          this.dialogEditVisible = false
          this.fetchData()
        })
      }).catch(e => e)
    }
  }
}
</script>
