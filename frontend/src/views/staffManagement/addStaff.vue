<template>
  <div>
<!-- 弹窗添加   -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="50%":before-close="close">
      <span>
        <el-form :model="staffForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form :model="staffForm"  :disabled="isdisabled" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="工号" prop="employeeId"  for="gonghao">
            <el-input v-model="staffForm.employeeId" id="gonghao" ></el-input>
          </el-form-item>
          </el-form>
          <el-form-item label="姓名" prop="employeeName" for="xingming">
             <el-input v-model="staffForm.employeeName" id="xingming"></el-input>
          </el-form-item>
          <el-form-item label="标签号" prop="labelId" for="lable">
             <el-input v-model="staffForm.labelId" id="lable"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="employeePhone" for="dianhua">
             <el-input v-model="staffForm.employeePhone" id="dianhua"></el-input>
          </el-form-item>
          <el-form-item label="工作状态" prop="employeeStatus" for="status">
            <el-select  id="status" v-model="staffForm.employeeStatus" placeholder="工作状态">
              <el-option label="空闲中" value="空闲中"></el-option>
              <el-option label="工作中" value="工作中"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="岗位" prop="employeePosition" for="gangwei">
            <el-select  id="gangwei" v-model="staffForm.employeePosition" placeholder="请选择员工岗位">
              <el-option label="仓库管理员" value="仓库管理员"></el-option>
              <el-option label="熔铸工" value="熔铸工"></el-option>
              <el-option label="运输员" value="运输员"></el-option>
              <el-option label="轧制工" value="轧制工"></el-option>
              <el-option label="精整工" value="精整工"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="入职日期" required>
            <el-col :span="11">
              <el-form-item prop="employeeWorkdate">
                <el-date-picker type="date" placeholder="选择日期" v-model="staffForm.employeeWorkdate" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
          </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="primary"  @click="addEmployee">确 定</el-button>
     </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import {Notification} from "element-ui";
import Vue from "vue";


export default {
  // props: ['dialogVisible'],
  props:{
    title:{
      type:String,
      default:"添加员工"
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
      staffForm:{//大对象
        employeeId:"",
        employeeName:"",
        labelId:"",
        employeePhone:"",
        employeePosition:"",
        employeeStatus:"空闲中",
        employeeWorkdate:"",
      },
      params:{
        employeeId:"",
        employeeName:"",
        labelId:"",
        employeePhone:"",
        employeePosition:"",
        employeeStatus: "",
        employeeWorkdate:"",
      },
      rules:{
        employeeId:[
          {required: true,message:"请输入员工工号",trigger:"blur"},
          {min:0,max:99999,message: "长度在5个字符",trigger:"blur"},
        ],
        employeeName:[{required: true,message:"请输入员工姓名",trigger:"blur"}],
        lableId:[
          {required: true,message:"请输入员工标签号",trigger:"blur"},
          {min:0,max:9999,message: "长度在4个字符",trigger:"blur"},
        ],
        employeePhone:[
          {required: true,message:"请输入员工联系电话",trigger:"blur"},
          {min:0,max:99999999999,message:"长度为11个字符",trigger:"blur"},
        ],

        employeeWorkdate:[{required: true,message:"请输入员工岗位",trigger:"blur"}],
        date1: [{ type: 'date', required: true, message: '请选择日期', trigger: 'change' }],
      }
    }
  },
  computed:{
    isdisabled(){
      if(this.title === "添加员工"){
        return false;
      }else {
        return true;
      }
    }
  },
  //监听器
  watch:{
    rowData(val){
      this.staffForm=val;
    }
  },
  methods:{
    close(){
      this.resetForm('ruleForm');
      this.staffForm={
        staffForm:{//大对象
          employeeId:"",
          employeeName:"",
          labelId:"",
          employeePosition:"",
          employeeStatus:"空闲中",
          employeeWorkdate:"",
        },
      }
     this.dialogVisible=false;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    addEmployee(){
      this.dialogVisible = false;
      if(this.title==='添加员工'){
        //url +属性绑定
        request.post('/Staff/add',this.staffForm).then(res=>{
          if(res.code === 'success') {
            this.$notify.success('新增成功');
          }else{
            this.$notify.error('新增失败');
          }
        })
      }else{
        console.log('编辑商品')
        request.post('/Staff/update',this.staffForm).then(res=>{
          if(res.code === 'success') {
            this.$notify.success('修改成功');
          }else{
            this.$notify.error('修改失败');
          }
        })
      }
    }
  }
}

</script>

<style scoped>

</style>
