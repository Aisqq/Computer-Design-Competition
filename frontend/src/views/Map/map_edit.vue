<template>
  <el-dialog title="地图编辑" :visible.sync="detailVisible" width="50%">
  <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
    <el-form-item label="地图名称">
      <el-input v-model="sizeForm.name"></el-input>
    </el-form-item>
    <el-form-item label="可活动区域">
      <el-select v-model="sizeForm.region" placeholder="请选择活动区域">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="地图类型">
      <el-checkbox-group v-model="sizeForm.type">
        <el-checkbox-button label="禁止人员活动区域" name="type"></el-checkbox-button>
        <el-checkbox-button label="工作区域" name="type"></el-checkbox-button>
        <el-checkbox-button label="可进入区域" name="type"></el-checkbox-button>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="工作类型">
      <el-radio-group v-model="sizeForm.resource" size="medium">
        <el-radio border label="仓库存储运输"></el-radio>
        <el-radio border label="生产线操作"></el-radio>
        <el-radio border label="员工活动休息"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
  </el-dialog>
</template>

<script>
export default {
  name:"map_edit",
  data () {
    return {
      detailVisible: false,
      sizeForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    init (data11) {
      this.detailVisible = true;
      console.log(data11);
    },
    onSubmit () {
      console.log('submit!');
    }
  }
}
</script>

<style scoped>

</style>
