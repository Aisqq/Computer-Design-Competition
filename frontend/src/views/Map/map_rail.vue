<template>
  <el-dialog title="电子围栏添加" :visible.sync="detailVisible" width="50%">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="围栏名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="活动区域">
        <el-select v-model="form.region" placeholder="请选择活动区域">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="围栏区域">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="禁止人员活动区域" name="type"></el-checkbox>
          <el-checkbox label="工作区域" name="type"></el-checkbox>
          <el-checkbox label="可进入区域" name="type"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="围栏类型">
        <el-radio-group v-model="form.resource">
          <el-radio label="禁止围栏"></el-radio>
          <el-radio label="可进入围栏"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name:"map_rail",
  data () {
    return {
      detailVisible: false,
      form: {
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
    init (data33) {
      this.detailVisible = true;
      console.log(data33);
    },
    onSubmit () {
      console.log('submit!');
    }
  }
}
</script>

<style scoped>

</style>
