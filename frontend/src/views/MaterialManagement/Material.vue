<template>
  <div>
    <el-container style="height:100vh;width:100vw;border:1px;solid:#eee">
      <el-aside width="200px" style="background-color: #363636">
        <Aside/>
      </el-aside>
      <el-container class="header-and-main">
        <el-header >
          <Head/>
        </el-header>
        <el-main  class="main" style="margin-top: 4vh">
          <div >
            <!--搜索框-->
            <div style="margin-bottom: 4vh;margin-top:4vh;">
              <el-input style="width:260px;" placeholder="请输入物料类型查询" v-model="params.employeeName"></el-input>
              <el-button title="" style="margin-left:5px;background-color: #4F4F4F;color: white" ><i class="el-icon-search" @click="load"></i>  搜索</el-button>
              <el-button title="" style="margin-left:5px;background-color: #4F4F4F;color: white;" @click="reset" ><i class="el-icon-refresh" ></i>  重置</el-button>
              <!--添加按钮  -->
              <el-button title="" style="margin-left:400px;background-color: #4F4F4F;color: white;" @click="addMaterial" ><i class="el-icon-plus" ></i>  添加</el-button>
            </div>
            <el-card>
            <el-table :data="tableData"
                      :default-sort="{prop:'materialId',order:'ascending'}"
                      stripe>
              <el-table-column prop="materialId" label="物料号" sortable default></el-table-column>
              <el-table-column prop="materialType" label="初始类型"></el-table-column>
              <el-table-column prop="labelId" label="标签号"></el-table-column>
              <el-table-column prop="materialWeight" label="物料重量"></el-table-column>
              <el-table-column prop="materialRemark" label="物料备注"></el-table-column>
              <el-table-column  header-align="center" label="操作">
                <template slot-scope="scope">
                  <div style="display: flex; justify-content: center; align-items: center;">
                  <el-button
                    style="background-color:#4F4F4F;color: white"
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)"><i class="el-icon-edit"/></el-button>
                  <el-button
                    style="background-color:#4F4F4F;color: white"
                    size="mini"
                    @click="deleteStaff(scope.row.materialId)"
                  ><i class="el-icon-delete"/></el-button>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <!--分页-->
              <div style="padding:30px;text-align:center;">
                <el-pagination
                  :current-page="params.pageNum"
                  :page-size="params.pageSize"
                  background
                  @current-change="handleCurrentChange"
                  layout="prev, pager, next"
                  :total="total">
                </el-pagination>
              </div>
            </el-card>
            <!-- 弹窗-->
            <addMaterial ref='dialog' :title="title" :rowData="rowData"/>
          </div>
        </el-main>
      </el-container>
    </el-container>


  </div>
</template>

<script>
// 引入公共组件顶部和侧边栏
import Head from "@/components/Head.vue";
import Aside from "@/components/Aside.vue";
import addMaterial from "@/views/MaterialManagement/addMaterial.vue";
import request from "@/utils/request";
export default {
  name:'MaterialView',
  data(){
    return{
      title: '添加物料',
      rowData: {},
      dialogVisible:false,
      tableData:[],
      total:0,
      params:{
      pageNum:1,
      pageSize: 8,
      materialType: ""
      }
    }
  },
  components: {
    Head,
    Aside,
    addMaterial,
    request
  },
  created(){
    this.load()//方法调用
  },
  methods:{
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum = pageNum;
      this.load()
    },
    reset(){
      this.params={
        pageNum:1,
        pageSize:8,//向后台发送数据
        materialType:""
      };
      this.load();
    },
    //弹窗修改
    handleEdit(index,row) {
      //1.显示弹窗 2.回显数据
      this.$refs.dialog.dialogVisible = true;
      this.title='更新标签绑定物料';
      this.rowData= {...row};//不加这里的话重复点击会报错
    },
    deleteStaff(id){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete("/Material/delete/"+id).then(res=>{
          if(res.code==='success'){
            this.$notify.success("删除成功");
            this.load();
          }else{
            this.$notify.error(res.msg);
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 弹窗添加
    addMaterial(){
      this.$refs.dialog.dialogVisible = true;
      this.title="添加物料";
    },
    changeDialog(){
      this.dialogVisible=false;
    },
    load(){
      request.get('/Material/Page',{
        params:this.params
      }).then(res=>{
        this.tableData=res.data.list//打开网页page/list查看数据，由于被多层封装必须要精准定位
        this.total=res.data.total//也在封装对象里可以看到
      })
    },
  },
}
</script>

<style>
.header-and-main{
  flex-direction: column;
}
.main{
  display: flex;
  flex-direction:column;
  align-items: flex-start;
  justify-content: space-between;
  height: 100%;
}

</style>
