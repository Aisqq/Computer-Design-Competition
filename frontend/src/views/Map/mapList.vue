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
        <el-main class="main" style="margin-top: 4vh">
          <div style="background-color:#2c3e6c;overflow:auto">
            <div class="row">
              <div class="col">
                <dv-border-box-11 title="冷轧机" style="">
                  <maplist1 />
                </dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="步进炉"> <maplist2/></dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="精剪机"><maplist3/></dv-border-box-11>
              </div>
            </div>
            <div class="row">
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <dv-border-box-11 title="热轧机"> <maplist4 /></dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="熔铸台"><maplist5/></dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="清洗机"><maplist6/></dv-border-box-11>
              </div>
            </div>
            <div class="row">
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <dv-border-box-11 title="退火炉"><maplist7 /></dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="双面铣洗机"><maplist8 /></dv-border-box-11>
              </div>
              <div class="col">
                <dv-border-box-11 title="叉车"><maplist9/></dv-border-box-11>
              </div>
            </div>
            <div class="row">
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
              <div class="col1">
                <dv-decoration-11 style="width:200px;height:60px;color:#fff;margin-left:8vw">引用</dv-decoration-11>
              </div>
            </div>
            <div class="row" style="margin-top: 4vh">
              <dv-decoration-10 style="width:90%;height:5px;" />
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Head from "@/components/Head.vue";
import Aside from "@/components/Aside.vue";
import maplist1 from "@/components/maplist/maplist1.vue";
import maplist2 from "@/components/maplist/maplist2.vue";
import maplist3 from "@/components/maplist/maplist3.vue";
import maplist4 from "@/components/maplist/maplist4.vue";
import maplist5 from "@/components/maplist/maplist5.vue";
import maplist6 from "@/components/maplist/maplist6.vue";
import maplist7 from "@/components/maplist/maplist7.vue";
import maplist8 from "@/components/maplist/maplist8.vue";
import maplist9 from "@/components/maplist/maplist9.vue";
export default {
  components: {
    Head,
    Aside,
    maplist1,
    maplist2,
    maplist3,
    maplist4,
    maplist5,
    maplist6,
    maplist7,
    maplist8,
    maplist9
  }
}
</script>

<style>
.header-and-main {
  flex-direction: column;
}

.main {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  height: 100%;
}

.row {
  display: flex;
  flex-direction: row;
  width: 100%;
}

.col {
  flex: 1;
  margin: 10px;
  box-sizing: border-box;
  height: 40vh; /* 可以根据需要调整高度 */
}
.col1{
  flex: 1;
  margin: 10px;
  box-sizing: border-box;
  height:4vh;
  padding-right:2vh;

}

</style>
