<template>
  <div class="box">
    <div class="box__header">车间设备概况</div>
    <div style="height:220px" id="pie"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import request from "@/utils/request";

const option = {
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b}: {c} ({d}%)'
  },
  legend: {
    data: []
  },
  series: [
    {
      name: '',
      type: 'pie',
      selectedMode: 'single',
      radius: [0, '30%'],
      label: {
        position: 'inner',
        fontSize: 10
      },
      labelLine: {
        show: false
      },
      data: []
    },
    {
      name: '',
      type: 'pie',
      radius: ['45%', '60%'],
      labelLine: {
        length: 15
      },
      label: {
        formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
        backgroundColor: '#F6F8FC',
        borderColor: '#8C8D8E',
        borderWidth: 1,
        borderRadius: 4,
        rich: {
          a: {
            color: '#6E7079',
            lineHeight: 20,
            align: 'center'
          },
          hr: {
            borderColor: '#8C8D8E',
            width: '100%',
            borderWidth: 1,
            height: 0
          },
          b: {
            color: '#4C5058',
            fontSize: 14,
            fontWeight: 'bold',
            lineHeight: 20
          },
          per: {
            color: '#fff',
            backgroundColor: '#4C5058',
            padding: [3, 4],
            borderRadius: 4
          }
        }
      },
      data: []
    }
  ]
};
export default {
  mounted() {
    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);
    pieChart.setOption(option);

    // 请求外层环形图数据
    request.get('/Device/Chartdataout')
      .then(response => {
        if (response && response.data && Array.isArray(response.data)) {
          const outerData = response.data.map(item => ({
            value: item.count,
            name: item.machinename
          }));
          // 更新外层环形图数据
          option.series[1].data = outerData;
        }
      })
      .catch(error => {
        console.error('请求外层数据失败:', error);
      });

    // 请求内层环形图数据
    request.get('/Device/Chartdatainside')
      .then(response => {
        if (response && response.data && Array.isArray(response.data)) {
          const innerData = response.data.map(item => ({
            value: item.count,
            name: item.machinetype
          }));
          // 更新内层环形图数据
          option.series[0].data = innerData;
          // 重新渲染图表
          pieChart.setOption(option);
        }
      })
      .catch(error => {
        console.error('请求内层数据失败:', error);
      });
  },
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
