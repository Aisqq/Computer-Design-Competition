<template>
  <el-dialog title="人员轨迹查询" :visible.sync="detailVisible" width="50%">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="标签号码">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="部门类型">
        <el-select v-model="form.region" placeholder="请选择活动区域">
          <el-option label="车间员工" value="shanghai"></el-option>
          <el-option label="叉车驾驶员" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="查询轨迹时间">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
        </el-col>
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
  name:"map_path",
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
    init (data22) {
      this.detailVisible = true;
      console.log(data22);
    },
    onSubmit () {
      console.log('submit!');
    }
  }
}
</script>

<style scoped>

</style>
