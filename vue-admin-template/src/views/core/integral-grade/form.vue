<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form label-width="120px">
      <el-form-item label="借款额度">
        <el-input-number v-model="integralGrade.borrowAmount" :min="0" />
      </el-form-item>
      <el-form-item label="积分区间开始">
        <el-input-number v-model="integralGrade.integralStart" :min="0" />
      </el-form-item>
      <el-form-item label="积分区间结束">
        <el-input-number v-model="integralGrade.integralEnd" :min="0" />
      </el-form-item>
      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate()"
        >
          保存
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import integralGradeApi from '@/api/core/integral-grade'

  export default {
    name: 'form',
    data(){
      return {
        saveBtnDisabled: false, //是否禁用保存按钮，防止表单重复提交
        integralGrade: {}
      }
    },
    created() {
      //当路由中存在id属性的时候，就是回显表单，需要调用回显数据的接口
      if (this.$route.params.id) {
        this.fetchById(this.$route.params.id)
      }
    },
    mounted() {
    },
    methods: {
      fetchById(id) {
        integralGradeApi.getById(id).then(response => {
          this.integralGrade = response.data.integral
        })
      },
      //保存或更新
      saveOrUpdate() {
        //禁用保存按钮
        this.saveBtnDisabled = true
        if (!this.integralGrade.id) {
          //调用新增
          this.saveData()
        } else {
          this.updateData()
        }
        //调用更新
      },
      saveData() {
        integralGradeApi.addIntegralGrade(this.integralGrade).then(response => {
          // this.$message({
          //   type: 'success',
          //   message: response.message
          // })
          this.$message.success(response.message)
          this.$router.push('/core/integral-grade/list')
        })
      },
      updateData() {
        integralGradeApi.update(this.integralGrade).then(response => {
          this.$message.success(response.message)
          this.$router.push('/core/integral-grade/list')
        })
      }
    }
  }
</script>

<style scoped>

</style>
