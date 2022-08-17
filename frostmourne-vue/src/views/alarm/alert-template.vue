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
                <el-option v-for="option in templateTypeOptions" :key="option.code" :label="option.name" :value="option.code" />
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
      <el-table-column prop="templateTypeTreeLabels" label="模板类型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.templateTypeTreeLabels | formatTemplateTypeTreeLabels }}</span>
        </template>
      </el-table-column>
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

    <el-dialog title="报警模板" :visible.sync="dialogEditVisible" width="50%" @close="closeAlertTemplateForm">
      <el-form ref="alertTemplateForm" :model="itemData" :rules="rule" label-width="120px" label-suffix="：">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="itemData.templateName" :maxlength="100" :disabled="!dialogEdit" autocomplete="off" />
        </el-form-item>
        <el-form-item label="模板类型" prop="templateTypeTreeValue">
          <el-cascader ref="templateTypeTreeValue" v-model="templateTypeTreeValue" style="width: 100%" :options="templateTypeTreeOptions" :props="templateTypeTreeProps" :show-all-levels="false" :disabled="!dialogEdit" />
        </el-form-item>
        <el-form-item label="模板内容" prop="content">
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
import { formatJsonDate } from '@/utils/datetime.js'

export default {
  filters: {
    timeFormat (value) {
      return value ? formatJsonDate(value, 'yyyy-MM-dd hh:mm:ss') : null
    },
    formatTemplateTypeTreeLabels (value) {
      var label = ''
      if (value && value.length > 0) {
        for (var i = 0; i < value.length; i++) {
          label += ' / ' + value[i]
        }
      }
      return label.length > 0 ? label.substr(3) : label
    }
  },
  data () {
    var validateTemplateTypeTreeRule = (rule, value, callback) => {
      if (!this.templateTypeTreeValue || this.templateTypeTreeValue.length === 0) {
        callback(new Error('模板类型不能为空'))
      } else {
        callback()
      }
    }
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
      templateTypeOptions: [
        { code: 'COMMON', name: '通用' },
        { code: 'dataName', name: '数据' }
      ],
      templateTypeTreeProps: {
        value: 'code',
        label: 'name',
        children: 'children'
      },
      templateTypeTreeValue: [],
      templateTypeTreeOptions: [],
      itemData: {
        id: 0,
        templateName: '',
        templateType: '',
        templateUnionCode: '',
        content: ''
      },
      dialogEditVisible: false,
      dialogEdit: false,
      rule: {
        templateName: [
          { required: true, message: '请输入模板名称', target: 'blur' },
          { max: 100, message: '长度不能超过100', target: 'blur' }
        ],
        templateTypeTreeValue: [
          { required: true, validator: validateTemplateTypeTreeRule, target: 'change' }
        ],
        content: [
          { required: true, message: '模板内容不能为空', target: 'blur' },
          { max: 5000, message: '长度不能超过5000', target: 'blur' }
        ]
      }
    }
  },
  created () {
    this.fetchData()
    this.initTemplateTypeTreeOptions()
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
    closeAlertTemplateForm () {
      this.$refs.alertTemplateForm.resetFields()
      this.$refs.templateTypeTreeValue.focusFirstNode()
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
        this.templateTypeTreeValue = []
      } else {
        this.itemData.id = row.id
        this.itemData.templateName = row.templateName
        this.itemData.templateType = row.templateType
        this.itemData.templateUnionCode = row.templateUnionCode
        this.itemData.content = row.content
        this.templateTypeTreeValue = row.templateTypeTreeValues
      }
    },
    getFormTemplateType () {
      if (this.templateTypeTreeValue == null || this.templateTypeTreeValue.length <= 0) {
        return null
      }
      return this.templateTypeTreeValue[0]
    },
    getFormTemplateUnionCode () {
      if (this.templateTypeTreeValue == null || this.templateTypeTreeValue.length <= 1) {
        return null
      }
      return this.templateTypeTreeValue[this.templateTypeTreeValue.length - 1]
    },
    initTemplateTypeTreeOptions () {
      alerttemplateApi.listTemplateTypeOptions().then(response => {
        this.templateTypeTreeOptions = response.result
      })
    },
    submitSaveItem () {
      this.itemData.templateType = this.getFormTemplateType()
      this.itemData.templateUnionCode = this.getFormTemplateUnionCode()
      this.$refs.alertTemplateForm.validate((valid) => {
        if (valid) {
          alerttemplateApi.saveAlertTemplate(this.itemData).then(response => {
            this.dialogEditVisible = false
            this.fetchData()
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>
