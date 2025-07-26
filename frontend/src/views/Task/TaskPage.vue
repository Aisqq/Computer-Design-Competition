<template>
 <div :style="minHeight" class="websit">

    <el-container style="height:100vh;width:100vw;border:1px ;solid:#eee">
      <el-aside width="200px" style="background-color: #363636">
       <Aside/>
      </el-aside>
       <el-container class="header-and-main">
         <el-header>
            <Head 厂区生产流程图/>
         </el-header>

         <el-main  class="main" style="margin-top: 2vh">
         <el-row :gutter="20">
           <dv-border-box-9 :style="LCTSC" class="bigmap">
             <img src="../../image/TaskPageImage_2/bacground01.png" class="bigmapImage" alt="bigmapImage"/>
           </dv-border-box-9>

           <div :style="tasklight">
             <tasklight />
           </div>

           <dv-border-box-2 >
           <div :style="taskchart" class="taskchart" >
             <TaskPage2taskchart />
           </div>
           </dv-border-box-2>
         </el-row>

         <div class="otherbox">
           <taskchar2 />
         </div>

           </el-main>
    </el-container>
  </el-container>

 </div>
</template>

<script >
import Aside from "@/components/Aside.vue";
import Head from "@/components/Head.vue";
import request from "@/utils/request";
import TaskPage2taskchart from "@/views/Task/TaskPage2taskchart.vue";
import taskchar2 from "@/views/Task/taskchart2.vue";
import tasklight from "@/views/Task/tasklight.vue";
export default {

  components: {Head, Aside, request,TaskPage2taskchart,taskchar2,tasklight},
  data: function () {
    return {
      dialogVisible: false,
      mappage: {
        position: 'absolute',
        zIndex: 1,
      },
      taskchart: {
        position: 'absolute',
        zIndex: 4,
      },
      tasklight: {
        position: 'absolute',
        zIndex: 2,
      },
      LCTSC:{
        position: 'absolute',
        zIndex: 3,
      },
      minHeight: 0
    }
  },
}

</script>

<style scoped>
.bigmap{
    display: block; /* 确保它是一个块级元素 */
    width: 75vw; /* 设置一个明确的宽度 */
    height: 70vh; /* 设置一个明确的高度 */
    overflow: hidden; /* 如果图片被裁剪，隐藏超出部分 */
    position: relative; /* 如果需要内部元素绝对定位 */
    margin-top: 2vh;
    .bigmapImage{
     width:73vw;
      height:68vh;
      margin-top: 1.2vh;
      margin-left: 2.3vh;
    }
}
.taskchart{
  background: lightblue;
  margin-top: 2vh;
  margin-left:75vw;
  height: 70vh;
  width: 12vw;
}

.otherbox{
  display: block;
  margin-top: 75vh;
  height:17vh;
  weight:10vw;
}
.websit{
    overflow: visible;
}

</style>
