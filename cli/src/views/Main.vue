<template>
  <el-container class="main_container">
    <el-main>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in imags" :key="item">
          <div>
            <el-image style :src="item.idView" fit="fill"></el-image>
            <h3 class="h3_title">{{item.title}}</h3>
          </div>
        </el-carousel-item>
      </el-carousel>
    </el-main>

  </el-container>
</template>

<script>
export default {
  name: "Main",
  data(){
    return{
      imags: [{id:0, idView:require('../assets/images/Home1.jpg')},
        {id:1, idView:require('../assets/images/Homeimg2.jpg')},
        {id:2, idView:require('../assets/images/Homeimg3.jpg')},
        {id:3, idView:require('../assets/images/Homeimg4.jpg')}]
    }
  }
}
</script>

<style lang="less" scoped>
//.main_container{
//  height: 100%;
//  width: 100%;
//}
//.header_top{
//  background-color: rgb(249,249,249);
//  display: flex;
//  justify-content: space-between;
//  padding-left: 160px;
//  padding-right: 160px;
//  align-items: center;
//  width: 100%;
//  height: 20px;
//
//  .left_box{
//    display: flex;
//    align-items: center;
//
//    img{
//      width: 48px;
//      margin-right: 10px;
//    }
//
//    span{
//      font-style: oblique ;
//    }
//  }
//  .right_box{
//    display: flex;
//    align-items: center;
//  }
//}
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

</style>
