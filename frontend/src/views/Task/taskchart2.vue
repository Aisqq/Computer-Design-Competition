<script >
  import Head from "@/components/Head.vue";
  import Aside from "@/components/Aside.vue";
  import request from "@/utils/request";

  export default {
    components: {Head, Aside, request},
    data(){
      return{
        dialogVisible:false,
        tableData:[],
        taskname:null,
      }
    },

    methods: {
      handleshow(){
        this.scheduleTask();
        this.load();
      },
      scheduleTask(){
        request.get('/scheduler/task').then(res=>{
          this.taskname=res.data
        })
      },
      load(){   //查询数据的方法，fetch是查询后台数据的api，获取并格式转换，出现跨域错误
        request.get('/scheduler/task-priorities', {
          params: this.params
        }).then(res => {
          this.tableData = res.data;
          // 排序
          this.tableData.sort((a, b) => b.priority - a.priority);
          // 重新编号
          this.tableData.forEach((task, index) => {
            task.priority = index + 1;
          });
        });
      },

    },
    created(){
      this.load()//方法调用
    },

  }

</script>

<template>

  <div style="margin:3vh">
    <el-card>
      <el-row :gutter="12">
        <el-col :span="5" style="font-weight: bold;font-size:26px;color:#4F4F4F;margin:3vh">任务情况</el-col>
        <el-col :span="7">

          <el-card>
            <div style="font-weight: bold;font-size:26px;color:#4F4F4F;margin:3vh">
              任务情况：{{ taskname }}
            </div>
          </el-card>


        </el-col>
        <el-col :span="7">
          <el-button @click="handleshow()">任务调度</el-button>
        </el-col>
      </el-row>

      <el-table :data="tableData" stripe>
        <el-table-column prop="name" label="任务名称"></el-table-column>
        <el-table-column prop="formattedArrivalTime" label="任务等待时间"></el-table-column>
        <el-table-column prop="labelCount" label="物料数量"></el-table-column>
        <el-table-column prop="priority" label="任务优先级" sortable default></el-table-column>
        <el-table-column prop="runTime" label="任务时间"></el-table-column>
      </el-table>

      <div style="padding:1vh;text-align:center;">
        <el-pagination
          :page-size="10"
          background
          layout="prev, pager, next"
          :total="tableData.length">
        </el-pagination>
      </div>

    </el-card>
  </div>

</template>




<style scoped>

</style>
