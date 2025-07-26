<template>
  <dv-full-screen-container>
    <div style="height:100%;width:100%;background-color:#2c3e6c;overflow:auto">
    <div class="decoration-container">
      <dv-decoration-8 style="width:300px;height:50px;" />
      <dv-decoration-5 style="width:300px;height:40px;"></dv-decoration-5>
      <dv-decoration-8 :reverse="true" style="width:300px;height:50px;" />
    </div>
    <div class="row" style="display: flex;justify-content: flex-end;width: 100%;"><el-button style="margin:10px;background-color: #4F4F4F;color: white;" @click="coming">进入后台</el-button></div>
    <div class="row1">
      <div class="cell">
        <dv-border-box-13>
          <RingChart />
        </dv-border-box-13>
      </div>
      <div class="cell">
        <div class="parent-container" ref="borderBox">
          <dv-border-box-8>
            <mapView ref="mapView" />
          </dv-border-box-8>
        </div>
      </div>
      <div class="cell">
        <dv-border-box-13><ScrollBoard /></dv-border-box-13>
      </div>
    </div>
    <div class="row2">
      <div class="cell">
        <dv-border-box-12><pieChart /></dv-border-box-12>
      </div>
      <div class="cell">
        <dv-border-box-12><column /></dv-border-box-12>
      </div>
      <div class="cell">
        <dv-border-box-12><number /></dv-border-box-12>
      </div>
    </div>
      <div class="row3">

      </div>
    </div>
  </dv-full-screen-container>
</template>

<script>
import RingChart from "@/components/RingChart.vue";
import ScrollBoard from "@/components/ScrollBoard.vue";
import pieChart from "@/components/pieChart.vue";
import column from "@/components/column.vue"
import number from "@/components/number.vue"
import mapView from "@/components/mapchart.vue";


export default {
  components: {
    RingChart,
    ScrollBoard,
    pieChart,
    column,
    number,
    mapView
  },
  mounted() {
    this.$nextTick(() => {
      // 等待DOM更新完成后再调用 adjustMapSize
      this.adjustMapSize();
      // 监听窗口大小变化事件
      window.addEventListener('resize', this.adjustMapSize);
    });
  },
  methods: {
    adjustMapSize() {
      // 确保 mapView 组件已经挂载并且borderBox ref是存在的
      const borderBox = this.$refs.borderBox;
      if (borderBox && this.$refs.mapView) {
        const width = borderBox.offsetWidth;
        const height = borderBox.offsetHeight;
        this.$refs.mapView.adjustSize(width, height);
      }
    },
    coming() {
      this.$router.push("/back/map");
    }
  },
  beforeDestroy() {
    // 移除窗口大小变化事件监听器
    window.removeEventListener('resize', this.adjustMapSize);
  },
};

</script>


<style>
.decoration-container {
  display: flex;
  justify-content: space-between; /* 左右对齐，中间平均分布 */
  align-items: flex-start; /* 垂直顶部对齐 */
  width: 100%;
  height: 40px; /* 可以根据需要调整高度 */
  margin-top: 0; /* 确保装饰容器在顶部 */
}
.parent-container {
  width:100%;
  height:100%;
}
.row1{
  width: 100%;
  height: 50%;
  display: flex;
  margin: 5px;
  .cell {
    padding:5px;
    /* 旁边两列各占1份，中间列占2份 */
    flex: 1;
  };
  .cell:nth-child(2) {
    flex: 2;
  }
}
.row2{
  width: 100%;
  height:45%;
  display:flex;
  margin:5px;
  .cell{
    padding:5px;
    flex: 1;
  }
}
.row3{
  width: 100%;
  height:10%;
}
</style>
