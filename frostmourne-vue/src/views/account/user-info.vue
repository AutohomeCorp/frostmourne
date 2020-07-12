<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="form.id" placeholder="id" clearable style="width: 200px;" class="filter-item" />
      <el-input v-model="form.account" placeholder="账号" clearable style="width: 200px;" class="filter-item" />
      <el-select v-model="form.teamId" placeholder="选择团队" clearable style="width: 200px" class="filter-item">
        <el-option v-for="item in teamList" :key="item.team_name" :label="item.full_name" :value="item.id" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">
        新增
      </el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="id" align="center" />
      <el-table-column prop="account" label="账号" align="center" />
      <el-table-column prop="full_name" label="姓名" align="center" />
      <el-table-column prop="team_id" label="团队ID" align="center" />
      <el-table-column prop="mobile" label="手机号码" align="center" />
      <el-table-column prop="email" label="邮件" align="center" />
      <el-table-column prop="wxid" label="企业微信id" align="center" />
      <!--<el-table-column prop="creator" label="创建人" align="center" />
      <el-table-column prop="create_at" label="创建时间" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.create_at | timeFormat }}</span>
        </template>
      </el-table-column>-->
      <el-table-column prop="modifier" label="修改人" align="center" />
      <el-table-column prop="modify_at" label="修改时间" align="center" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.modify_at | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="210" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" icon="el-icon-edit" @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block mt-paginate">
      <el-row>
        <el-col :span="8" :offset="6">
          <div class="grid-content">
            <el-pagination background layout="total, prev, pager, next" :total="rowcount" :page-size="form.pageSize" @prev-click="onPrevClick" @next-click="onNextClick" @current-change="onCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </div>

    <el-dialog title="保存账号" :visible.sync="dialogFormVisible" width="40%">
      <el-form :model="editData">
        <el-form-item label="团队" :label-width="formLabelWidth">
          <el-select v-model="editData.team_id" placeholder="选择团队" class="filter-item">
            <el-option v-for="item in teamList" :key="item.team_name" :label="item.full_name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="账号" :label-width="formLabelWidth">
          <el-input v-model="editData.account" autocomplete="off" />
        </el-form-item>
        <el-form-item label="名字" :label-width="formLabelWidth">
          <el-input v-model="editData.full_name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="手机号码" :label-width="formLabelWidth">
          <el-input v-model="editData.mobile" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="editData.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="企业微信id" :label-width="formLabelWidth">
          <el-input v-model="editData.wxid" autocomplete="off" />
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
import userInfoApi from '@/api/account/user-info.js'
import teamInfoApi from '@/api/account/team-info.js'
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
      form: {
        pageIndex: 1,
        pageSize: 10,
        id: null,
        account: null,
        teamId: null
      },
      editData: {
        account: null,
        full_name: null,
        team_id: null,
        mobile: null,
        email: null,
        wxid: null
      },
      formLabelWidth: '100px',
      dialogFormVisible: false,
      teamList: []
    }
  },
  created() {
    this.fetchData()

    teamInfoApi.find().then(response => {
      this.teamList = response.result
    })
  },
  methods: {
    fetchData() {
      this.listLoading = true
      userInfoApi.findPage(this.form).then(response => {
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
      console.log(row)
      if (row != null) {
        this.editData.id = row.id
        this.editData.account = row.account
        this.editData.full_name = row.full_name
        this.editData.team_id = row.team_id
        this.editData.mobile = row.mobile
        this.editData.email = row.email
        this.editData.wxid = row.wxid
      } else {
        this.editData = {}
      }

      this.dialogFormVisible = true
    },
    search() {
      // console.log(this.form)
      this.form.pageIndex = 1
      this.fetchData()
    },
    save() {
      if(this.editData.id) {
        userInfoApi.update(this.editData).then(response=> {
          this.dialogFormVisible = false
          this.fetchData()
        })
      } else {
        userInfoApi.create(this.editData).then(response=> {
          this.dialogFormVisible = false
          this.fetchData()
        })
      }
    },
    remove(row) {
      this.$confirm('此操作将删除账号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userInfoApi.delete(row.id).then(response => {
          this.$message({ type: 'success', message: '删除成功！', duration: 1500 })
          this.fetchData()
        })
      })
    }
  }
}
</script>

<style scoped>
.filter-container {
  padding-bottom: 10px;
}
</style>
