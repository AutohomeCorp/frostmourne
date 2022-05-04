<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="form.datasourceType" placeholder="选择数据类型" clearable style="width: 190px" class="filter-item">
        <el-option label="elasticsearch" value="elasticsearch" />
        <el-option label="influxdb" value="influxdb" />
        <el-option label="mysql" value="mysql" />
        <el-option label="clickhouse" value="clickhouse" />
      </el-select>
      <!-- <el-input v-model="form.datasourceName" placeholder="名称" style="width: 300px;" class="filter-item" /> -->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">查询</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">新增</el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="datasourceName" label="名称" align="center" />
      <el-table-column prop="datasourceType" label="类型" align="center" />
      <el-table-column prop="serviceAddress" label="服务地址" align="center" />
      <el-table-column prop="modifyAt" label="最近修改时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modifyAt | timeFormat }}</span>
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
            <el-pagination background layout="total, prev, pager, next" :page-size="form.pageSize" :total="rowcount" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>

    <el-dialog title="保存数据源" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="editData">
        <el-form-item label="类型" :label-width="formLabelWidth">
          <el-select v-model="editData.datasourceType" :disabled="disableTypeSelect" placeholder="数据源类型">
            <el-option label="elasticsearch" value="elasticsearch" />
            <el-option label="influxdb" value="influxdb" />
            <el-option label="mysql" value="mysql" />
            <el-option label="clickhouse" value="clickhouse" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="editData.datasourceName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="服务地址" :label-width="formLabelWidth">
          <el-input v-model="editData.serviceAddress" autocomplete="off" />
          <!--<el-tooltip content="地址更新后，下次重启后才生效" placement="bottom">
            <i class="el-icon-question" />
          </el-tooltip>-->
        </el-form-item>
        <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="是否HTTPS:">
          <el-switch v-model="editData.settings.https" active-value="YES" active-text="是" inactive-value="NO" inactive-text="否" />
        </el-form-item>
        <template v-if="editData.settings.https === 'YES'">
          <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="SSL证书:" :label-width="formLabelWidth">
            <el-upload
              action="#"
              :on-change="handleCertChange"
              :on-remove="handleCertRemove"
              :file-list="fileList"
              :auto-upload="false">
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="证书格式:" :label-width="formLabelWidth">
            <el-select v-model="editData.settings.sslFormat">
              <el-option label="jks" value="jks" />
              <el-option label="pkcs12" value="pkcs12" />
            </el-select>
          </el-form-item>
          <el-form-item v-if="editData.datasourceType === 'elasticsearch'" label="证书密码:" :label-width="formLabelWidth">
            <el-input v-model="editData.settings.sslCertPassword" placeholder="填写证书密码" :type="passwordType" autocomplete="off" />
          </el-form-item>
        </template>
        <el-form-item v-if="editDataShowUsername()" label="认证用户" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.username" placeholder="无认证不需要填写" autocomplete="off" />
        </el-form-item>
        <el-form-item v-if="editDataShowPassword()" label="密码" :label-width="formLabelWidth">
          <el-input v-model="editData.settings.password" placeholder="无认证不需要填写" :type="passwordType" autocomplete="off" />
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
        pageSize: 10,
        datasourceName: '',
        datasourceType: ''
      },
      editData: {
        id: 0,
        datasourceName: '',
        datasourceType: '',
        serviceAddress: '',
        settings: {}
      },
      formLabelWidth: '80px',
      dialogFormVisible: false,
      disableTypeSelect: true,
      passwordType: 'password',
      fileList: []
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
          this.form.datasourceType
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
    handleCertChange (file, filelist) {
      // 变更事件：添加文件
      var tmpEditData = this.editData
      var reader = new FileReader()
      reader.onload = function (e) {
        var base64Result = this.result
        // console.log(base64Result)
        // 去掉头部
        var sslCert = base64Result.replace(/\r|\n/g, '').replace(/data:[^;]+;base64,/g, '')
        // console.log(cert)
        tmpEditData.settings.sslCert = sslCert
      }
      reader.readAsDataURL(file.raw)
    },
    handleCertRemove (file) {
      // 移除文件
      this.editData.settings.sslCert = null
    },
    edit (row) {
      if (row != null) {
        this.passwordType = 'password'
        this.editData.id = row.id
        this.editData.datasourceName = row.datasourceName
        this.editData.datasourceType = row.datasourceType
        this.editData.serviceAddress = row.serviceAddress
        this.disableTypeSelect = true
        // 深度拷贝
        this.editData.settings = row.settings ? JSON.parse(JSON.stringify(row.settings)) : {}
      } else {
        this.passwordType = ''
        this.editData = {
          settings: {}
        }
        this.disableTypeSelect = false
      }
      this.fileList = []

      this.dialogFormVisible = true
    },
    search () {
      // console.log(this.form)
      this.form.pageIndex = 1
      this.fetchData()
    },
    save () {
      if (this.editData.datasourceType === 'elasticsearch') {
        if (this.editData.serviceAddress.indexOf(':') < 0) {
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
    },
    showPwd () {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    editDataShowUsername () {
      return this.editData.datasourceType === 'elasticsearch' || this.editData.datasourceType === 'mysql' ||
       this.editData.datasourceType === 'clickhouse' || this.editData.datasourceType === 'influxdb'
    },
    editDataShowPassword () {
      return this.editDataShowUsername()
    }
  }
}
</script>
