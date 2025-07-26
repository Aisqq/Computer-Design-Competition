<template>
  <div style="min-height:90vh">
    <div class="logo-container" slot="top">
      <img src="../image/logo.png" alt="">
    </div>
    <span style="color:#E1FFFF;padding-left:1vw;font-size:16px;margin-bottom: 3vh">铜板车间调度系统平台</span>
     <el-menu default-active="activeIndex" class="el-menu-vertical-demo" width="500px"
            background-color="#363636" text-color="white" active-text-color="#ffd04b" router>
        <el-menu-item index="/back/map">
          <span style="text-decoration-color: white"><i class="el-icon-location" ></i>三维地图</span>
        </el-menu-item>
       <el-menu-item index="/back/mapList">
         <span style="text-decoration-color: white"><i class="el-icon-folder-opened" ></i>三维模型库</span>
       </el-menu-item>
       <el-menu-item index="/back/staff">
         <span style="text-decoration-color: white"><i class="el-icon-files"></i>人员管理</span>
       </el-menu-item>
        <el-menu-item index="/back/task">
         <span slot="title"><i class="el-icon-document"></i>任务发布</span>
        </el-menu-item>
        <el-menu-item index="/back/security">
          <span slot="title"><i class="el-icon-setting"></i>安全预警</span>
        </el-menu-item>
        <el-submenu index="5">
          <template slot="title">
          <span slot="title"><i class="el-icon-files"></i>行车设备管理</span>
          </template>
          <el-menu-item index="/back/car">行车管理</el-menu-item>
          <el-menu-item index="/back/device">设备管理</el-menu-item>
          <el-menu-item index="/back/material">物料管理</el-menu-item>
          <el-menu-item index="/back/staCalender">车间概况</el-menu-item>
        </el-submenu>
      </el-menu>
  </div>

</template>

<script>
export default {
  data(){
    return {
      activeIndex:"map",
    }
  }
}

</script>

<style>
.logo-container{
  display: flex;
  margin-bottom: 2vh;
  img{
    padding-top: 5vh;
    padding-left: 1vw;
    max-width: 3vw;
    object-fit: contain;
  }
}
</style>
