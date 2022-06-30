<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.datasourceType" placeholder="选择数据类型" clearable style="width: 190px" class="filter-item" @change="formSourceTypeChangeHandler">
        <el-option label="elasticsearch" value="elasticsearch" />
        <el-option label="prometheus" value="prometheus" />
        <el-option label="skywalking" value="skywalking" />
        <el-option label="influxdb" value="influxdb" />
        <el-option label="mysql" value="mysql" />
        <el-option label="clickhouse" value="clickhouse" />
        <el-option label="iotdb" value="iotdb" />
        <el-option label="sqlserver" value="sqlserver" />
      </el-select>
      <el-select v-model="form.dataSourceId" placeholder="选择数据源" clearable class="filter-item">
        <el-option v-for="item in formDatasourceList" :key="item.datasourceName" :label="item.datasourceName" :value="item.id" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">新增</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="dataName" label="名称" align="center" />
      <el-table-column prop="displayName" label="说明" align="center" />
      <el-table-column prop="datasourceType" label="类型" align="center" />
      <el-table-column prop="timestampField" label="时间字段" align="center" />
      <el-table-column prop="modifyAt" label="最近修改" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modifyAt | timeFormat }}</span>
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
            <el-pagination background layout="total, prev, pager, next" :page-size="form.pageSize" :total="rowcount" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>

    <el-dialog title="保存数据名" :visible.sync="dialogFormVisible" width="30%">
      <el-form ref="form" :model="editData" :rules="rules">
        <el-form-item label="类型" :label-width="formLabelWidth" prop="datasourceType">
          <el-select
            v-model="editData.datasourceType"
            :disabled="disableEdit"
            placeholder="类型"
            clearable
            style="width: 190px"
            class="filter-item"
            @change="dialogSourceTypeChangeHandler">
            <el-option label="elasticsearch" value="elasticsearch" />
            <el-option label="prometheus" value="prometheus" />
            <el-option label="skywalking" value="skywalking" />
            <el-option label="influxdb" value="influxdb" />
            <el-option label="mysql" value="mysql" />
            <el-option label="clickhouse" value="clickhouse" />
            <el-option label="iotdb" value="iotdb" />
            <el-option label="sqlserver" value="sqlserver" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth" prop="dataName">
          <el-input v-model="editData.dataName" :disabled="disableEdit" autocomplete="off" />
        </el-form-item>
        <el-form-item label="数据源" :label-width="formLabelWidth" prop="dataSourceId">
          <el-select v-model="editData.dataSourceId" :disabled="disableEdit" placeholder="选择数据源" class="filter-item">
            <el-option v-for="item in dialogDatasourceList" :key="item.datasourceName" :label="item.datasourceName" :value="item.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="说明" :label-width="formLabelWidth" prop="displayName">
          <el-input v-model="editData.displayName" autocomplete="off" />
        </el-form-item>

        <el-form-item v-if="showEditDataTimestampField()" label="时间字段" :label-width="formLabelWidth" prop="timestampField">
          <el-input v-model="editData.timestampField" autocomplete="off" />
        </el-form-item>
        <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="索引前缀" :label-width="formLabelWidth" prop="indexPrefix">
          <el-input v-model="editData.settings.indexPrefix" placeholder="applog-" autocomplete="off" />
        </el-form-item>

        <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="时间后缀" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.timePattern" placeholder="可空。举例: yyyyMMdd。最小单位到天, 小于天请用*表示" autocomplete="off" />
        </el-form-item>

        <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="显示字段" :label-width="formLabelWidth">
          <el-tag
            v-for="tag in esFieldTags.dynamicTags"
            :key="tag"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)">
            {{ tag }}
          </el-tag>
          <el-input
            v-if="esFieldTags.inputVisible"
            ref="saveTagInput"
            v-model="esFieldTags.inputValue"
            class="input-new-tag"
            size="mini"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm" />
          <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 添加</el-button>
        </el-form-item>

        <el-form-item v-if="editData.datasourceType === 'influxdb'" label="Database" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.database" autocomplete="off" />
        </el-form-item>
        <el-form-item v-if="editData.datasourceType === 'skywalking'" label="模块" :label-width="formLabelWidth">
          <el-select v-model="editData.settings.skywalkingDataCategory">
            <el-option label="Logging" value="logging" />
            <el-option label="Alarms" value="alarms" />
          </el-select>
        </el-form-item>
        <el-form-item v-if="editData.datasourceType === 'prometheus'" label="Endpoint" :label-width="formLabelWidth">
          <el-select v-model="editData.settings.prometheusEndpoint">
            <el-option label="/api/v1/query" value="/api/v1/query" />
          </el-select>
        </el-form-item>
        <el-form-item v-if="editData.datasourceType === 'iotdb'" label="Endpoint" :label-width="formLabelWidth">
          <el-select v-model="editData.settings.iotdbEndpoint">
            <el-option label="/rest/v1/query" value="/rest/v1/query" />
          </el-select>
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
      formDatasourceList: [],
      form: {
        pageIndex: 1,
        pageSize: 10,
        dataName: '',
        datasourceType: '',
        dataSourceId: null
      },
      editData: {
        id: 0,
        dataName: null,
        dataSourceId: null,
        datasourceType: null,
        displayName: null,
        timestampField: null,
        settings: {}
      },
      dialogDatasourceList: [],
      formLabelWidth: '80px',
      dialogFormVisible: false,
      disableEdit: true,
      rules: {
        dataName: [
          { required: true, message: '请输入数据名称', trigger: 'blur' }
        ],
        displayName: [
          { required: true, message: '请输入数据说明', trigger: 'blur' }
        ],
        dataSourceId: [
          { required: true, message: '请选择数据源', trigger: 'change' }
        ],
        datasourceType: [
          { required: true, message: '请选择数据类型', trigger: 'change' }
        ]
      },
      esFieldTags: {
        dynamicTags: [],
        inputVisible: false,
        inputValue: ''
      }
    }
  },
  created () {
    this.fetchData()
  },
  methods: {
    fetchData () {
      this.listLoading = true
      dataApi.findDataName(this.form.pageIndex, this.form.pageSize, this.form.datasourceType, this.form.dataSourceId)
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
      if (row != null) {
        this.disableEdit = true
        dataApi.findDataSourceByType(row.datasourceType).then(response => {
          this.dialogDatasourceList = response.result
        })
        this.editData.id = row.id
        this.editData.dataName = row.dataName
        this.editData.datasourceType = row.datasourceType
        this.editData.timestampField = row.timestampField
        this.editData.displayName = row.displayName
        this.editData.dataSourceId = row.dataSourceId
        this.editData.settings = row.settings
        this.disableTypeSelect = true
        if (row.settings.headFields) {
          this.esFieldTags.dynamicTags = row.settings.headFields.split(',')
        } else {
          this.esFieldTags.dynamicTags = []
        }
      } else {
        this.disableEdit = false
        this.editData = {
          id: 0,
          dataName: null,
          dataSourceId: null,
          datasourceType: '',
          displayName: '',
          timestampField: '',
          settings: {}
        }
        this.disableTypeSelect = false
        this.esFieldTags.dynamicTags = []
        this.esFieldTags.inputVisible = false
        this.esFieldTags.inputValue = ''
      }

      this.dialogFormVisible = true
    },
    search () {
      this.form.pageIndex = 1
      this.fetchData()
    },
    save () {
      this.$refs['form'].validate((validate) => {
        if (!validate) {
          return false
        }
        this.editData.settings.headFields = this.esFieldTags.dynamicTags.join(',')
        dataApi.saveDataName(this.editData).then(response => {
          this.dialogFormVisible = false
          this.fetchData()
        })
      })
    },
    formSourceTypeChangeHandler (selectedValue) {
      this.form.dataSourceId = null
      if (selectedValue) {
        dataApi.findDataSourceByType(selectedValue).then(response => {
          this.formDatasourceList = response.result
        })
      } else {
        this.formDatasourceList = []
      }
    },
    dialogSourceTypeChangeHandler (selectedValue) {
      this.editData.dataSourceId = null
      if (selectedValue) {
        dataApi.findDataSourceByType(selectedValue).then(response => {
          this.dialogDatasourceList = response.result
        })
      } else {
        this.dialogDatasourceList = []
      }
    },
    remove (row) {
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
    },
    showEditDataTimestampField () {
      return this.editData.datasourceType === 'elasticsearch' || this.editData.datasourceType === 'mysql' || 
      this.editData.datasourceType === 'clickhouse' || this.editData.datasourceType === 'sqlserver'
    },
    handleClose (tag) {
      this.esFieldTags.dynamicTags.splice(this.esFieldTags.dynamicTags.indexOf(tag), 1)
    },

    showInput () {
      this.esFieldTags.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm () {
      const inputValue = this.esFieldTags.inputValue
      if (inputValue) {
        this.esFieldTags.dynamicTags.push(inputValue)
      }
      this.esFieldTags.inputVisible = false
      this.esFieldTags.inputValue = ''
    }
  }
}
</script>

<style>
.filter-container {
  padding-bottom: 10px;
}
</style>
