
<template>
  <FullScreenContainer>
    <dv-border-box-7>
  <div class="box">
    <div class="box__header">生产进度</div>
    <div class="TaskPage2taskchart">
      <dv-capsule-chart :config="config" style="width:200px;height:500px" />
    </div>
  </div>
    </dv-border-box-7>
  </FullScreenContainer>
</template>

<script>
export default {
  data() {
    return {
      // 初始化 config.data 为空数组
      config: {
        data: [
          {
            name: '覆铜板',
            value: 46
          },
          {
            name: '轧制废料',
            value: 32
          },
          {
            name: '铜精矿',
            value: 11
          },
          {
            name: '半固化片',
            value: 21
          },
          {
            name: '电解铜',
            value: 29
          },
          {
            name: '阳极铜',
            value: 13
          },
        ],
        colors: ['#e062ae', '#fb7293', '#e690d1', '#32c5e9', '#96bfff'],

        showValue: true
      }
    };

  },
}


</script>

<style scoped>

.box{
  padding:7px;
  height:90%;
  .box__header{
    margin:10px;
    font-size:20px;
    color:white;
  }
}
.TaskPage2taskchart{
  margin-left:0vh;
  margin-top: 3vh;
}

</style>
