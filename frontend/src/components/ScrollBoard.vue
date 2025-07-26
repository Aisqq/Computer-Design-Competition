<template>
  <div class="box">
    <div class="box__header">任务调度信息</div>
    <dv-scroll-board :config="config" style="width:96%;height:86%" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      config: {
        header: ['任务名', '任务时间', '到达时间'],
        data: [], // 初始化为空数组，将从后端填充
        index: true,
        columnWidth: [60, 60, 40], // 假设您想要为每列设置相同的宽度，或根据需要调整
        align: ['center', 'center', 'center'] // 假设您想要所有列都居中
      },
      loading: false, // 加载状态，用于显示加载指示器
      error: null // 存储错误信息
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.loading = true; // 显示加载指示器
      this.error = null; // 重置错误信息

      fetch('http://localhost:9090/scheduler/task-priorities')
        .then(response => {
          if (!response.ok) {
            throw new Error('网络响应不是 ok');
          }
          return response.json();
        })
        .then(data => {
          if (data.code === 'success' && Array.isArray(data.data)) {
            // 假设后端返回的数据结构是 { code: 'success', data: [{ name: '...', runTime: '...', formattedArrivalTime: '...' }] }
            this.config.data = data.data.map(item => [item.name, item.runTime, item.formattedArrivalTime]);
            this.config={...this.config};
            console.log(this.config.data);
          } else {
            this.error = '返回的数据格式不正确或数据数组不存在';
            console.error(this.error, data);
          }
        })
        .catch(error => {
          this.error = '请求数据失败或处理数据时出错';
          console.error(this.error, error);
        })
        .finally(() => {
          this.loading = false; // 隐藏加载指示器
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

</style>
