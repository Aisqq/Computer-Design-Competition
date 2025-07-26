<template>
  <!--    入口-->
  <div class="back-entrance">
    <div :style="style1" class="Entrancelogo">
      <img  style="width:150px;margin-top:14vh;margin-left:-2vh" src="../../image/logo.png" alt="">
    </div>

    <div :style="style2" class="Entrancebigbox" >
      <h1 class="logoName">铜板车间调度系统平台</h1>
      <h1 class="welcome">欢迎使用铜板车间调度系统平台</h1>
      <h2 class="target">致力于打造车间流程全可视化、人员调度更便捷的铜板车间调度系统</h2>
      <el-button id="button" @click="goToLogin"> 进入 </el-button>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default{
  methods:{
    goToLogin(){
      this.$router.push({name:'login'})
    },
    style1:{
      position: 'absolute',
      zIndex: 2,
    },
    style2:{
      position: 'absolute',
      zIndex: 1,
    }
  }
}

</script>

<style>


.back-entrance{
  background-image: url("../../image/backpage.jpg");
  background-size: cover;
  background-position: center;
  height: 100vh;
  width: 100vw;

  .Entrancelogo{
    width: 100px;
    height: 100px;
    margin-top:0vh;
    margin-left:47vw;

  };

  .Entrancebigbox{
    margin-top:20vh;
    margin-left:42vw;
    background: rgba(0,0, 0, 0);
    border: 3px solid rgba(0, 0, 0, 0);

    .logoName{
      width: 378px;
      height: 106px;
      font-size: 37px;
      font-weight: bold;
      line-height: 53px;
      margin-left:-2vw;
      color: #0F4264;
      margin-top:0vh;
    };
    .welcome{
      width: 672px;
      height: 67px;
      font-size: 48px;
      font-weight: bold;
      line-height: 67px;
      letter-spacing: 0;
      color: #000000;
      margin-left:-10vw;
      margin-top:-8vh;
    };
    .target{
      width: 720px;
      height: 34px;
      font-size: 24px;
      font-weight: bold;
      line-height: 34px;
      display: flex;
      align-items: center;
      letter-spacing: 0;
      color: #BB4430;
      margin-left:-11vw;
      margin-top:-2vh;
    }

    #button{
      width: 300px;
      height: 49px;
      background: #4F4F4F;
      font-size: 25px;
      color: white;
    }
  }
}
</style>
