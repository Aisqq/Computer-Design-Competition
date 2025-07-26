<template>
  <div class="box">
    <div class="box__header">车间物料概况</div>

  <div class="column">
  <dv-conical-column-chart :config="config" style="width:90%;height:200px;" class="columnchart" />
  </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      // 初始化 config.data 为空数组
      config: {
        data: []
      }
    };
  },
  created() {
    // 组件创建后获取数据
    this.fetchData();
  },
  methods: {
    fetchData() {
      fetch('http://localhost:9090/Material/countMaterial')
        .then(response => response.json())
        .then(data => {
          // 检查返回的数据格式是否正确
          if (data.code === 'success' && Array.isArray(data.data)) {
            // 更新 config.data 属性
            this.config.data = data.data.map(item => ({
              name: item.materialtype,
              value: item.count
            }));
            this.config={...this.config};
            console.log(this.config.data);
          } else {
            console.error('返回的数据格式不正确:', data);
          }
        })
        .catch(error => {
          console.error('请求数据失败:', error);
        });
    }
  }
};

</script>

<style>
.box{
  padding:10px;
  height:90%;
  .box__header{
    margin:10px;
    font-size:20px;
    color:white;
  }
}
.columnchart{
  margin-left:4vh;
  margin-top: 3vh;
}

</style>
