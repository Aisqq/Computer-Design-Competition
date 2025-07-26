<template>
  <div>
    <!-- 弹窗添加   -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="50%":before-close="close">
      <span>
        <el-form :model="carForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form :model="carForm"  :disabled="isdisabled" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="行车号" prop="carId"  for="xingchehao">
            <el-input v-model="carForm.carId" id="xingchehao" ></el-input>
          </el-form-item>
          </el-form>
          <el-form-item label="标签号" prop="labelId" for="lable">
             <el-input v-model="carForm.labelId" id="lable"></el-input>
          </el-form-item>
          <el-form-item label="行车状态" prop="carStatus" for="status">
             <el-input v-model="carForm.carStatus" id="status"></el-input>
          </el-form-item>
          <el-form-item label="行车备注" prop="carRemark" for="remark">
             <el-input v-model="carForm.carRemark" id="remark"></el-input>
          </el-form-item>
          </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary"  @click="addCar">确 定</el-button>
     </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  props: {
    title:{
      type:String,
      default:"添加行车",
    },
    rowData:{
      type:Object,
      default:function(){
        return{}
      }
    }
  },
  data(){
    return{
      disable: false,
      dialogVisible:false,
      carForm:{//大对象
        carId:"",
        labelId:"",
        carRemark:"",
        carStatus:""
      },
      params:{
        carId:"",
        labelId:"",
        carRemark:"",
        carStatus:""
      },
      rules:{
        carId:[
          {required: true,message:"请输入行车号",trigger:"blur"},
          {min:0,max:99999,message: "长度在5个字符",trigger:"blur"},
        ],
        labelId:[
          {required: true,message:"请输入员工标签号",trigger:"blur"},
          {min:0,max:9999,message: "长度在4个字符",trigger:"blur"},
        ],
      }
    }
  },
  computed:{
    isdisabled(){
      if(this.title === "添加行车"){
        return false;
      }else {
        return true;
      }
    }
  },
  //监听器
  watch:{
    rowData(val){
      this.carForm=val;
    }
  },
  methods:{
    close(){
      this.carForm={
        carForm:{//大对象
          carId:"",
          labelId:"",
          carRemark:"",
          carStatus:""
        },
      }
      this.dialogVisible=false;
    },
    addCar() {
      this.dialogVisible = false;
      if (this.title === '添加行车') {
        //url +属性绑定
        request.post('/Car/add', this.carForm).then(res => {
          if (res.code === 'success') {
            this.$notify.success('新增成功');
          } else {
            this.$notify.error('新增失败');
          }
        })
      } else {
        console.log('编辑商品')
        request.post('/Car/update', this.carForm).then(res => {
          if (res.code === 'success') {
            this.$notify.success('修改成功');
          } else {
            this.$notify.error('修改失败');
          }
        })
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }
}

</script>

<style scoped>

</style>
