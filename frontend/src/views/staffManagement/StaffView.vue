<template>
  <div>
    <el-container style="height:100vh;width:100vw;border:1px;solid:#eee">
      <el-aside width="200px" style="background-color: #363636">
        <Aside/>
      </el-aside>
      <el-container class="header-and-main">
        <el-header >
          <Head/>
        </el-header>
        <el-main  class="main" style="margin-top: 4vh">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-card style="height: 350px; margin: 10px; padding: 10px;">
                <div style="height:300px" id="pie1"></div>
              </el-card>
            </el-col>
              <el-col :span="12">
              <el-card style="height: 350px; margin: 10px; padding: 10px;">
                <div style="height:350px" ref="chart"></div>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-card>
              <div style="height:350px" ref="linepie"></div>
            </el-card>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import Head from "@/components/Head.vue";
import Aside from "@/components/Aside.vue";
import * as echarts from 'echarts';
import request from "@/utils/request";

const option = {
  title:{
    text:"车间员工分类图"
  },
  legend: {
    top: 'bottom'
  },
  toolbox: {
    show: true,
    feature: {
      mark: { show: true },
      dataView: { show: true, readOnly: false },
      restore: { show: true },
      saveAsImage: { show: true }
    }
  },
  series: [
    {
      name: 'Nightingale Chart',
      type: 'pie',
      radius: ['20%', '50%'],
      center: ['50%', '50%'],
      roseType: 'area',
      itemStyle: {
        borderRadius: 8
      },
      data: [

      ]
    }
  ]
};
export default {
  components: {
    Head,
    Aside,
  },

  mounted() {
    //饼状图
    let pieDom1 = document.getElementById('pie1'); // 使用 getElementById 并传递 id 值
    let pieChart1 = echarts.init(pieDom1);
    pieChart1.setOption(option);
    request.get('/Staff/Ringchart')
      .then(response => {
        if (response && response.data && Array.isArray(response.data)) {
          const dynamicData = response.data.map(item => ({
            value: item.count, // 确保这里的属性与 echarts 所需的属性一致
            name: item.employeeposition
          }));
          option.series[0].data = dynamicData; // 更新图表数据
          pieChart1.setOption(option); // 重新渲染图表
        }
      })
      .catch(error => {
        console.error('请求数据失败:', error);
      });
    //柱状图
    this.renderChart();
    //综合图
    this.initLinePieChart();
  },

  methods:{
    initLinePieChart() {
      this.$nextTick(()=>{
      const myChart = echarts.init(this.$refs.linepie);
      const linePieOption = {
        title:{
          text:"生产线产能图"
        },
        legend: {},
        tooltip: {
          trigger: 'axis',
          showContent: false
        },
        color: ['#ee6666', '#fac858','#5470c6',],
        dataset: {
          source: [
            ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
            ['生产线一', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
            ['生产线二', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
            ['生产线三', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
          ]
        },
        xAxis: { type: 'category' },
        yAxis: { gridIndex: 0 },
        grid: { top: '55%' },
        series: [
          // 折线图系列和饼状图系列配置
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'pie',
            id: 'pie',
            radius: '30%',
            center: ['50%', '25%'],
            emphasis: {
              focus: 'self'
            },
            label: {
              formatter: '{b}: {@2012} ({d}%)'
            },
            encode: {
              itemName: 'product',
              value: '2012',
              tooltip: '2012'
            }
          }
        ]
      };
      myChart.setOption(linePieOption);});
    },
    renderChart() {
      const myChart = echarts.init(this.$refs.chart);
      const option = {
        title:{
          text: "车间物料储备图"
        },
        xAxis: {
          data: ['原料', '中间产品', '铜板']
        },
        yAxis: {},
        dataGroupId: '',
        animationDurationUpdate: 500,
        series: {
          type: 'bar',
          id: 'sales',
          data: [
            {
              value: 5,
              groupId: '原料'
            },
            {
              value: 7,
              groupId: '中间产品'
            },
            {
              value: 4,
              groupId: '铜板'
            }
          ],
          universalTransition: {
            enabled: true,
            divideShape: 'clone'
          }
        }
      };
      myChart.setOption(option);
      const drilldownData = [
        {
          dataGroupId: '原料',
          data: [
            ['精铜', 4],
            ['废铜', 2],
          ]
        },
        {
          dataGroupId: '中间产品',
          data: [
            ['铜杆', 4],
            ['铜丝', 2],
            ['覆铜板', 1],
          ]
        },
        {
          dataGroupId: '产品',
          data: [

          ]
        }
      ];

      myChart.on('click', function (event) {
        if (event.data) {
          var subData = drilldownData.find(function (data) {
            return data.dataGroupId === event.data.groupId;
          });
          if (!subData) {
            return;
          }
          myChart.setOption({
            xAxis: {
              data: subData.data.map(function (item) {
                return item;
              })
            },
            series: {
              type: 'bar',
              id: 'sales',
              dataGroupId: subData.dataGroupId,
              data: subData.data.map(function (item) {
                return item[1];
              }),
              universalTransition: {
                enabled: true,
                divideShape: 'clone'
              }
            },
            graphic: [
              {
                type: 'text',
                left: 50,
                top: 20,
                style: {
                  text: 'Back',
                  fontSize: 18
                },
                onclick: function () {
                  myChart.setOption(option);
                }
              }
            ]
          });
        }
      });
    }
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
</style>
