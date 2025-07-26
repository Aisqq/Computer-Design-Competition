<template>
  <div>
    <!-- 弹窗添加   -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="50%":before-close="close" >
      <span>
        <el-form :model="deviceForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form :model="deviceForm"  :disabled="isdisabled" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="设备号" prop="deviceId"  for="shebeihao">
            <el-input v-model="deviceForm.machineId" id="shebeihao" ></el-input>
          </el-form-item>
          </el-form>
          <el-form-item label="设备名称" prop="deviceName" for="mingcheng">
             <el-input v-model="deviceForm.machineName" id="mingcheng"></el-input>
          </el-form-item>
          <el-form-item label="标签号" prop="labelId" for="lable">
             <el-input v-model="deviceForm.labelId" id="lable"></el-input>
          </el-form-item>
          <el-form-item label="设备类型" prop="devicetype" for="remark">
            <el-select  v-model="deviceForm.machineType" id="remark" placeholder="请选择设备类型">
              <el-option label="大型设备" value="大型设备"></el-option>
              <el-option label="中型设备" value="中型设备"></el-option>
              <el-option label="小型设备" value="小型设备"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备状态" prop="deviceStatus" for="status">
             <el-input v-model="deviceForm.machineStatus" id="status"></el-input>
          </el-form-item>
          <el-form-item label="加工物料ID" prop="MlabelId" for="Mlable">
             <el-input v-model="deviceForm.materialId" id="Mlable"></el-input>
          </el-form-item>
          </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary"  @click="addDevice">确 定</el-button>
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
      default:"添加设备"
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
      deviceForm:{//大对象
        machineId:"",
        machineName:"",
        labelId:"",
        materialId:"",
        machineType:"",
        machineStatus:"",
      },
      params:{
        machineId:"",
        machineName:"",
        labelId:"",
        materialId:"",
        machineType:"",
        machineStatus:"",
      },
      rules:{
        deviceId:[
          {required: true,message:"请输入设备号",trigger:"blur"},
          {min:0,max:99999,message: "长度在5个字符",trigger:"blur"},
        ],
        deviceName:[{required: true,message:"请输入设备名",trigger:"blur"}],
        labelId:[
          {required: true,message:"请输入设备标签号",trigger:"blur"},
          {min:0,max:9999,message: "长度在4个字符",trigger:"blur"},
        ],
        materialId:[{required: true,message:"请输入加工物料id",trigger:"blur"}],
      }
    }
  },
  computed:{
    isdisabled(){
      if(this.title === "添加设备"){
        return false;
      }else {
        return true;
      }
    }
  },
  //监听器
  watch:{
    rowData(val){
      this.deviceForm=val;
    }
  },
  methods:{
    close(){
      this.resetForm('ruleForm');
      this.deviceForm={
        deviceForm:{//大对象
          machineId:"",
          machineName:"",
          labelId:"",
          materialId:"",
          machineType:"",
          machineStatus:"",
        },
      }
      this.dialogVisible=false;
    },
    addDevice(){
      this.dialogVisible = false;
      if(this.title==='添加设备'){
        //url +属性绑定
        request.post('/Device/add',this.deviceForm).then(res=>{
          if(res.code === 'success') {
            this.$notify.success('新增成功');
          }else{
            this.$notify.error('新增失败');
          }
        })
      }else{
        console.log('编辑信息')
        request.post('/Device/update',this.deviceForm).then(res=>{
          if(res.code === 'success') {
            this.$notify.success('修改成功');
          }else{
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
