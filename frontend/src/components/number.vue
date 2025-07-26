<template>
  <div class="box">
  <div class="box__header">生产线加工进度</div>
  <dv-capsule-chart :config="config" style="width:300px;height:300px" />
  </div>
</template>

<script>
export default {
  data(){
    return{
      config:{
        data: [
          {
            name: '生产线一',
            value: 167
          },
          {
            name: '生产线二',
            value: 67
          },
          {
            name: '生产线三',
            value: 123
          },
          {
            name: '生产线四',
            value: 55
          },
        ]
      }
    }
  }
}

</script>
<style>
.box{
  padding:10px;
  height:90%;
  .box__header{
    margin-bottom:20px;
    font-size:20px;
    color:white;
  }
}

</style>
