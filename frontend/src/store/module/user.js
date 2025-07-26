export default {
  namespaced: true,//开启命名空间
  state:{
    userinfo:{
      username:'',
      userrole:'',
      isLogin:false,
      token:''
    }
  },//数据
  mutations:{
    setUser(state, payload){
      state.userinfo = payload;
    },
    deleteUser(state){
      state.userinfo = {
        username:'',
        userrole: '',
        isLogin:false,
        token:''
      };
    }
  },//操作数据
  actions:{

  }
}
