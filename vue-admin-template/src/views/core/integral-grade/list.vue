<template>
  <div class="app-container">
    <el-table :data="list" stripe border >
      <el-table-column type="index" label="ID"  width="50" />
      <el-table-column label="借款额度" prop="borrowAmount" width="180" />
      <el-table-column label="积分区间开始" prop="integralStart" width="180" />
      <el-table-column label="积分区间结束" prop="integralEnd" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <router-link
            :to="'/core/integral-grade/edit/' + scope.row.id"
            style="margin-right: 5px"
          >
            <el-button type="primary"
                       size="mini"
                       icon="el-icon-edit"
                       >编辑</el-button>
          </router-link>
          <el-button type="danger"
                     size="mini"
                     icon="el-icon-delete"
                     @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import integralGrade from '@/api/core/integral-grade'

  export default {
    name: 'List',
    data() {
      return {
        list: []
      }
    },
    created() {

    },
    mounted() {
      this.getList()
    },
    methods: {
      // 获取列表
      getList() {
        integralGrade.list().then(response => {
          this.list = response.data.list
        })
      },
      // 根据id删除某一个数据
      // 这里用了两个then回调 这个还好 ，可以使用另一种方式避免回调地狱 将then串联起来
      /*
      a().then(res => {
        return b()
      }).then(res => {

      }).catch(() => {

      })
      */
      removeDataById(id){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          integralGrade.removeById(id).then(response => {
            this.$message({
              showClose: true,
              message: response.message,
              type: 'success'
            })
            this.getList()
          })
        }).catch((error) => {
          if (error === 'cancel'){
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          }
        });
      },

    }
  }
</script>

<style scoped>

</style>
