<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="form.id" placeholder="id" clearable style="width: 200px;" class="filter-item" />
      <el-input v-model="form.teamName" placeholder="名称" clearable style="width: 200px;" class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="search">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="edit(null)">
        新增
      </el-button>
    </div>

    <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column prop="id" label="id" align="center" />   
      <el-table-column prop="team_name" label="团队" align="center" />   
      <el-table-column prop="full_name" label="全名" align="center" />
      <el-table-column prop="department_id" label="部门ID" align="center" />   
      <el-table-column prop="creator" label="创建人" align="center" />   
      <el-table-column prop="modifier" label="修改人" align="center" />   
      <el-table-column prop="create_at" label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.create_at | timeFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="modify_at" label="修改时间" align="center">
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

    <el-dialog title="保存团队" :visible.sync="dialogFormVisible" width="40%">
      <el-form :model="editData">
        <el-form-item label="部门" :label-width="formLabelWidth">
          <el-select v-model="editData.department_id" placeholder="选择部门" class="filter-item">
            <el-option v-for="item in departmentList" :key="item.department_name" :label="item.full_name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="名字" :label-width="formLabelWidth">
          <el-input v-model="editData.team_name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="全称" :label-width="formLabelWidth">
          <el-input v-model="editData.full_name" autocomplete="off" />
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
import teamInfoApi from '@/api/account/team-info.js'
import departmentInfoApi from '@/api/account/department-info.js'
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
        teamName: null
      },
      editData: {
        team_name: null,
        full_name: null,
        department_id: null
      },
      formLabelWidth: '100px',
      dialogFormVisible: false,
      departmentList: []
    }
  },
  created() {
    this.fetchData()
    departmentInfoApi.find().then(response => {
      this.departmentList = response.result
    })
  },
  methods: {
    fetchData() {
      this.listLoading = true
      teamInfoApi.findPage(this.form).then(response => {
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
        this.editData.team_name = row.team_name
        this.editData.full_name = row.full_name
        this.editData.department_id = row.department_id
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
        teamInfoApi.update(this.editData).then(response=> {
          this.dialogFormVisible = false
          this.fetchData()
        })
      } else {
        teamInfoApi.create(this.editData).then(response=> {
          this.dialogFormVisible = false
          this.fetchData()
        })
      }
    },
    remove(row) {
      this.$confirm('此操作将删除团队和团队下所有账号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teamInfoApi.delete(row.id).then(response => {
          this.$message({ type: 'success',message: '删除成功！',duration: 1500})
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