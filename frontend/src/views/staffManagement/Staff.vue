<template>
  <div>
    <el-container style="height:100vh;width:100vw;border:1px;solid:#eee">
      <el-aside width="200px" style="background-color: #363636">
        <Aside/>
      </el-aside>
      <el-container class="header-and-main">
        <el-header>
          <Head/>
        </el-header>
        <el-main  class="main" style="margin-top: 4vh">
          <div>
            <!--搜索框-->
            <div>
              <div style="margin-bottom: 4vh;margin-top:4vh;">
                <el-input style="width:260px;" placeholder="请输入员工姓名" v-model="params.employeeName"></el-input>
                <el-button title="" style="margin-left:5px;background-color: #4F4F4F;color: white" @click="load"><i class="el-icon-search"></i>  搜索</el-button>
                <el-button title="" style="margin-left:5px;background-color: #4F4F4F;color: white;" @click="reset" ><i class="el-icon-refresh" ></i>  重置</el-button>
                <!--添加按钮  -->
                <el-dropdown @command="handleCommand">
                  <el-button  style="margin-left:400px;background-color: #4F4F4F;color: white;">
                    <i class="el-icon-plus"/> 添加
                  </el-button>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="single">单个添加</el-dropdown-item>
                    <el-upload action="http://localhost:9090/Staff/import" :on-success="handleImport" :show-file-list="false">
                      <el-dropdown-item command="batch">批量添加</el-dropdown-item>
                    </el-upload>
                  </el-dropdown-menu>
                </el-dropdown>

                <el-button title="" style="margin-left:10px;background-color: #4F4F4F;color: white;" @click="exportData" ><i class="el-icon-plus" ></i>  导出</el-button>

              </div>
            </div>
            <el-card >
            <el-table :data="tableData"
                      :default-sort="{prop:'employeeId',order:'ascending'}"
                      stripe>
              <el-table-column prop="employeeId" label="工号" sortable default></el-table-column>
              <el-table-column prop="employeeName" label="姓名"></el-table-column>
              <el-table-column prop="labelId" label="标签号"></el-table-column>
              <el-table-column prop="employeePhone" label="电话"></el-table-column>
              <el-table-column prop="employeeStatus" label="工作状态"></el-table-column>
              <el-table-column prop="employeePosition" label="岗位"></el-table-column>
              <el-table-column prop="employeeWorkdate" label="入职日期"></el-table-column>
              <el-table-column  header-align="center" label="操作">
                <template slot-scope="scope">
                  <el-button
                    style="background-color:#4F4F4F;color: white"
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)"><i class="el-icon-edit"/></el-button>
                  <el-button
                    style="background-color:#4F4F4F;color: white"
                    size="mini"
                    @click="deleteStaff(scope.row.employeeId)"
                   ><i class="el-icon-delete"/></el-button>
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
<!--            <addStaff :dialogVisible='dialogVisible' @changeDialog='changeDialog'/>         -->
            <addStaff ref='dialog' :title="title" :rowData="rowData"/>
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
import addStaff from "@/views/staffManagement/addStaff.vue";
import request from "@/utils/request";//引入该用法
export default {
  name:'StaffView',
  data(){
    return{
      title:'添加员工',
      rowData:{},
      dialogVisible:false,
      tableData:[],
      total:0,
      params:{
        pageNum:1,
        pageSize:7,//向后台发送数据
        employeeName:""
      }

    }
  },
  created(){
      this.load()//方法调用
  },
  methods:{
    // 处理下拉菜单的命令
    handleCommand(command) {
      if (command === 'single') {
        // 执行单个添加逻辑
        console.log('执行单个添加');
        this.addStaff();
      } else if (command === 'batch') {
        // 执行批量添加逻辑
        console.log('执行批量添加');
      }
    },
    handleImport(res,file,fileList){
      if(res.code === 'success') {
        this.$message.success("操作成功");
        this.load();
      }else{
        this.$message.error(res.msg);
      }
    },
    exportData(){
      window.open('http://localhost:9090/Staff/export?employeeId='+this.params.employeeName);
    },
    handleCurrentChange(pageNum){
      //点击分页按钮触发分页
      this.params.pageNum = pageNum;
      this.load()
    },
    reset(){
      this.params={
        pageNum:1,
        pageSize:8,//向后台发送数据
        employeeName:""
      };
      this.load();
    },
    //弹窗修改
    handleEdit(index,row) {
      //1.显示弹窗 2.回显数据
      this.$refs.dialog.dialogVisible = true;
      this.title='编辑员工信息';
      this.rowData= {...row};//不加这里的话重复点击会报错
    },
    deleteStaff(id){
        this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          request.delete("/Staff/delete/"+id).then(res=>{
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
        this.load()
    },
    // 弹窗添加
    addStaff(){
        // this.dialogVisible=true
        //   修改子组件
      this.$refs.dialog.dialogVisible = true;
      this.title="添加员工";
    },
    load(){   //查询数据的方法，fetch是查询后台数据的api，获取并格式转换，出现跨域错误
      // fetch("http://localhost:9090/Staff/List").then(res => res.json()).then((res)=>{
      //   console.log(res)
      //   this.tableData=res;//查询结果赋值
      // })
      request.get('/Staff/Page',{
        params:this.params
      }).then(res=>{
        this.tableData=res.data.list//打开网页page/list查看数据，由于被多层封装必须要精准定位
        this.total=res.data.total//也在封装对象里可以看到
      })
    }
  },

  components: {
    Head,
    Aside,
    addStaff
  }
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
