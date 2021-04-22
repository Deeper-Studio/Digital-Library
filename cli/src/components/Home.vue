<template>

  <div >
    <el-row :gutter="0">
     <el-col :span="20">
       <el-header class="nav_header">
         <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
           <el-menu-item index="/main">Home</el-menu-item>
           <el-menu-item index="/search">Search</el-menu-item>
           <el-menu-item index="upload">Upload</el-menu-item>
           <el-menu-item index="mana">Manage</el-menu-item>
         </el-menu>
       </el-header>
     </el-col>
      <el-col :span="4">
        <div class="right-menu">
          <el-dropdown class="avatar-container" trigger="click">
            <div class="avatar-wrapper">
              <img src="../assets/images/avatar.jpg" alt="" class="user-avatar">
              <i class="el-icon-caret-bottom" />
            </div>
            <el-dropdown-menu slot="dropdown" class="user-dropdown">
              <router-link to="/main">
                <el-dropdown-item>
                  Home
                </el-dropdown-item>
              </router-link>
              <router-link to="/info">
                <el-dropdown-item>
                  Info
                </el-dropdown-item>
              </router-link>
              <el-dropdown-item divided @click.native="logout">
                <span style="display:block;">Log Out</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
    <el-main>
      <!--路由视图-->
      <br>
      <router-view></router-view>
    </el-main>
  </div>

</template>

<script>
export default {
  name: 'home',
  data(){
    return{
      activeIndex: 1,
      imags: [{id:0, idView:require('../assets/images/Home1.jpg')},
              {id:1, idView:require('../assets/images/Homeimg2.jpg')},
              {id:2, idView:require('../assets/images/Homeimg3.jpg')},
              {id:3, idView:require('../assets/images/Homeimg4.jpg')}]
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key).catch(err => {});
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
  }
}
</script>

<style lang="less" scoped>

.nav_header{
  float: left;
  width: 1450px;
}
.el-carousel__item h3 {
  color: #fff;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin:10px;
}

.el-carousel__item:nth-child(2n) {
  background-color: #fff;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #fff;
}

.h3_title{
  position: absolute;
  left: 10px;
  bottom: 20px;
}

.right-menu {
  float: right;
  height: 100%;
  line-height: 50px;

    &:focus {
       outline: none;
     }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
         cursor: pointer;
         transition: background .3s;

        &:hover {
           background: rgba(0, 0, 0, .025)
         }
      }
    }

    .avatar-container {
      margin-right: 30px;

    .avatar-wrapper {
      margin-top: 5px;
      position: relative;

    .user-avatar {
      cursor: pointer;
      width: 40px;
      height: 40px;
      border-radius: 10px;
    }

      .el-icon-caret-bottom {
        cursor: pointer;
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}
</style>
