<template xmlns:el-col="http://www.w3.org/1999/xhtml">
  <div>
    <el-container style="height:100vh;width:100vw;border:1px;solid:#eee">
      <el-aside width="200px" style="background-color: #363636">
        <Aside/>
      </el-aside>
      <el-container class="header-and-main">
        <el-header>
          <Head/>
        </el-header>
        <el-main class="main" style="margin-top: 4vh">
          <el-card style="margin-top:2vh;width:98%;height:98%;padding-right:2px;overflow:hidden;">
            <mapView/>
          </el-card>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import mapView from './mapView.vue';

// 引入公共组件顶部和侧边栏
import Head from "@/components/Head.vue";
import Aside from "@/components/Aside.vue";

export default {
  components: {
    Head,
    Aside,
    mapView
  },
}
</script>

<style>
.header-and-main {
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
