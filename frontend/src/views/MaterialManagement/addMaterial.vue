<template>
  <div>
    <!-- 弹窗添加   -->
    <el-dialog title="添加物料" :visible.sync="dialogVisible" width="50%" >
      <span>
        <el-form :model="materialForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" :before-close="close">
          <el-form :model="materialForm"  :disabled="isdisabled" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="物料号" prop="materialId"  for="gonghao">
            <el-input v-model="materialForm.materialId" id="gonghao" ></el-input>
          </el-form-item>
          </el-form>
          <el-form-item label="初始类型" prop="materialType" for="leixing">
             <el-input v-model="materialForm.materialType" id="leixing"></el-input>
          </el-form-item>
          <el-form-item label="标签号" prop="labelId" for="lable">
             <el-input v-model="materialForm.labelId" id="lable"></el-input>
          </el-form-item>
          <el-form-item label="重量" prop="materialWeight" for="zhongliang">
             <el-input v-model="materialForm.materialWeight" id="zhongliang"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="materialRemark" for="beizhu">
             <el-input v-model="materialForm.materialRemark" id="beizhu"></el-input>
          </el-form-item>
          </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="addMaterial">添加</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  props:{
    title:{
      type:String,
      default:"添加物料"
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
      materialForm:{//大对象
        materialId:"",
        materialType:"",
        labelId:"",
        materialWeight:"",
        materialRemark:"",
      },
      params:{
        materialId:"",
        materialType:"",
        labelId:"",
        materialWeight:"",
        materialRemark:"",
      },
      rules:{
        materialId:[
          {required: true,message:"请输入物料编号",trigger:"blur"},
          {min:0,max:9999,message: "长度在5个字符",trigger:"blur"},
        ],
        materialType:[{required: true,message:"请输入物料类型",trigger:"blur"}],
        materialWeight:[{required: true,message:"请输入物料重量",trigger:"blur"}],
        labelId:[
          {required: true,message:"请输入物料标签号",trigger:"blur"},
          {min:0,max:9999,message: "长度在4个字符",trigger:"blur"},
        ],
      }
    }
  },
  computed:{
    isdisabled(){
      if(this.title === "添加物料"){
        return false;
      }else {
        return true;
      }
    }
  },
  //监听器
  watch:{
    rowData(val){
      this.materialForm=val;
    }
  },
  methods:{
    close(){
      this.materialForm={
        materialForm:{//大对象
          materialId:"",
          materialType:"",
          labelId:"",
          materialWeight:"",
          materialRemark:"",
        },
      }
      this.dialogVisible=false;
    },
    addMaterial(){
      this.dialogVisible = false;
      if(this.title==='添加物料'){
        //url +属性绑定
        request.post('/Material/add',this.materialForm).then(res=>{
          if(res.code === 'success') {
            this.$notify.success('新增成功');
          }else{
            this.$notify.error('新增失败');
          }
        })
      }else{
        console.log('编辑商品')
        request.post('/Material/update',this.materialForm).then(res=>{
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
