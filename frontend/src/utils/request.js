import axios from "axios";
import qs from "qs";
//工具类简化操作

const request = axios.create({//创建axios对象
  baseURL: "http://localhost:9090",//写上后台地址,页面写接口时不需要写‘\api’
  timeout: 5000
})

//request拦截器
//发送请求前预处理，如统一加token
request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=UTF-8';//可以在请求头加一些自定义参数
  // config.headers['token'] =user.token
  if(config.method === 'get') {
    config.paramsSerializer = function(params) {
      return JSON.stringify(params,{arrayFormat: 'repeat'});
    }
  }
  return config;
},error => {
  return Promise.reject(error);
});

//reponse拦截器，对返回结果预处理
request.interceptors.response.use(response => {
  let res = response.data;
  //兼容返回的字符串数据
  if(typeof res === 'string') {
    res = res ? JSON.parse(res) : res;
  }
  return res;
},error => {
  console.log('err' +error);
  return Promise.reject(error);
  }
  )

export default request;
