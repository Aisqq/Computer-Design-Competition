<template>
<div class="header">
  <div class="right">
    <el-button style="margin-right:2vw;background-color: #4F4F4F;color: white" @click="backing">返回数据大屏</el-button>
    <div class="user" style="padding-right:1vw">
       <el-dropdown style="margin-top:1vh;">
          <span class="el-dropdown-link">
             <img src="../image/user.png" alt="" style="height:5vh;">
<!--               <img src="../image/user.gif" alt="Animated Image" style="height:8vh">-->
          </span>
       <el-dropdown-menu slot="dropdown">
         <el-dropdown-item>
           <span>{{ userinfo.username }}</span>
         </el-dropdown-item>
         <el-dropdown-item>
           <span>{{ userinfo.userrole}}</span>
         </el-dropdown-item>
         <el-dropdown-item divided>
           <span @click="logout">退出登录</span>
         </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
<!--      <span @click="drawer = true" type="primary" style="margin-left: 16px;">-->
<!--        <i class="el-icon-bell" style="font-size:30px;color:gray;font-weight:bold"></i>-->
<!--      </span>-->
  </div>
</div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  computed: {
    ...mapState('user', ['userinfo']),
  },
  methods: {
    ...mapMutations('user', ['setUser']),
    backing(){
      this.$router.push("/back/homePage");
    },
    logout() {
      localStorage.removeItem("user");
      this.setUser({
        username: '',
        isLogin: false,
        token: '',
      });
      this.$router.push('/login');
    },
  },

  created() {
    console.log(this.userinfo)
    const user = JSON.parse(localStorage.getItem("user"));
    if (user) {
      this.setUser(user);
    }
  },
}
</script>

<style  scoped>
.header{
  display: flex;
  height:12vh;
  border-bottom: 1px solid lightgrey;
  .right{
    display: flex;
    align-items: center;
    position: absolute;
    right: 80px;
    margin-top:2vh;
  }
}

</style>
