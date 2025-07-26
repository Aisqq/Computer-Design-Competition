<template>
  <div class="box">
    <div class="box__header">车间员工概况</div>
    <!-- 将 config 作为数据属性绑定到 dv-active-ring-chart 组件 -->
    <dv-active-ring-chart :config="config" style="width:300px;height:250px" />
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
      fetch('http://localhost:9090/Staff/Ringchart')
        .then(response => response.json())
        .then(data => {
          // 检查返回的数据格式是否正确
          if (data.code === 'success' && Array.isArray(data.data)) {
            // 更新 config.data 属性
            this.config.data = data.data.map(item => ({
              name: item.employeeposition,
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
  margin-top:10px;
  padding:10px;
  height:90%;
}
.box__header{
  margin:10px;
  font-size:20px;
  color:white;
}
</style>
