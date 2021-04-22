<template>
  <div class="search-box">
    <input class="search-txt" type="text" placeholder="Search">
    <a class="search-btn">
      <svg class="icon" viewBox="0 0 1024 1024"><path d="M424.024 766.098c-91.619 0-177.754-35.679-242.538-100.464-133.735-133.737-133.735-351.344 0-485.078 64.784-64.784 150.919-100.462 242.538-100.462s177.754 35.677 242.539 100.462c133.733 133.735 133.735 351.34 0 485.078-64.785 64.783-150.922 100.464-242.539 100.464zM424.024 196.085c-60.637 0-117.643 23.613-160.523 66.489-88.512 88.51-88.51 232.53 0 321.040 42.876 42.876 99.885 66.491 160.523 66.491s117.643-23.614 160.523-66.491c88.51-88.51 88.51-232.53 0-321.040-42.876-42.876-99.885-66.489-160.523-66.489z" fill="#e94118" p-id="1176"></path><path d="M886.838 943.904c-14.842 0-29.684-5.663-41.010-16.986l-261.286-261.285c-22.647-22.649-22.647-59.369 0-82.018 22.647-22.647 59.37-22.647 82.018 0l261.286 261.286c22.647 22.649 22.647 59.369 0 82.018-11.325 11.322-26.167 16.985-41.010 16.985z" fill="#e94118" p-id="1177"></path></svg>
    </a>
  </div>
</template>
<script>
export default {
  name: "Search"
}
</script>
<style lang="less" scoped>
//.body{
//  margin: 0;
//  padding: 0;
//  background: #303133;
//}
.search-box{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  background: #2f3640;
  height: 40px;
  border-radius: 40px;
  padding: 10px;
}
.search-box:hover>.search-txt{
  width: 200px;
  padding: 0 6px;
}
.search-box:hover>.search-btn{
  background: white;
}
.search-btn{
  color: #e84118;
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #2f3640;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}
.search-txt{
  border: none;
  background: none;
  outline: none;
  float: left;
  padding: 0;
  color: white;
  font-size: 16px;
  transition: 0.4s;
  line-height: 40px;
  width: 0px;
}
</style>

<!--<template>-->
<!--  <div class="container">-->
<!--      <div class="search_box">-->
<!--        <el-input placeholder="Keywords" v-model="input3" class="input-with-select">-->
<!--          <el-select v-model="select" slot="prepend" placeholder="Theme">-->
<!--            <el-option label="Theme" value="1"></el-option>-->
<!--            <el-option label="Keywords" value="2"></el-option>-->
<!--            <el-option label="Author" value="3"></el-option>-->
<!--          </el-select>-->
<!--          <el-button slot="append" icon="el-icon-search"></el-button>-->

<!--        </el-input>-->
<!--        <a class="search-btn">-->
<!--          <svg class="icon" viewBox="0 0 1024 1024"><path d="M424.024 766.098c-91.619 0-177.754-35.679-242.538-100.464-133.735-133.737-133.735-351.344 0-485.078 64.784-64.784 150.919-100.462 242.538-100.462s177.754 35.677 242.539 100.462c133.733 133.735 133.735 351.34 0 485.078-64.785 64.783-150.922 100.464-242.539 100.464zM424.024 196.085c-60.637 0-117.643 23.613-160.523 66.489-88.512 88.51-88.51 232.53 0 321.040 42.876 42.876 99.885 66.491 160.523 66.491s117.643-23.614 160.523-66.491c88.51-88.51 88.51-232.53 0-321.040-42.876-42.876-99.885-66.489-160.523-66.489z" fill="#e94118" p-id="1176"></path><path d="M886.838 943.904c-14.842 0-29.684-5.663-41.010-16.986l-261.286-261.285c-22.647-22.649-22.647-59.369 0-82.018 22.647-22.647 59.37-22.647 82.018 0l261.286 261.286c22.647 22.649 22.647 59.369 0 82.018-11.325 11.322-26.167 16.985-41.010 16.985z" fill="#e94118" p-id="1177"></path></svg>-->
<!--        </a>-->
<!--      </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "Search",-->
<!--  data(){-->
<!--    return{-->
<!--      input3: '',-->
<!--      select: '',-->
<!--    }-->
<!--  },-->
<!--  method: {-->

<!--  }-->
<!--}-->

<!--</script>-->

<!--<style lang="less" scoped>-->
<!--* {-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--}-->

<!--.container {-->
<!--  background: url("../../assets/images/background.jpg");-->
<!--  //height: 600px;-->
<!--  //width: 100%;-->
<!--  //margin: 100px auto 0 auto;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  position:fixed;-->
<!--  margin-top: -20px;/*上边距*/-->
<!--  margin-left: -20px;/*左边距*/-->
<!--  background-size:100% 100%;-->
<!--  .search_box{-->
<!--    width: 800px;-->
<!--    margin-top: 180px;-->
<!--    position: absolute;-->
<!--    left: 25%;-->
<!--  }-->
<!--}-->
<!--.parent {-->
<!--  position: relative;-->
<!--}-->
<!--.search {-->
<!--  width: 300px;-->
<!--  height: 40px;-->
<!--  border-radius: 18px;-->
<!--  outline: none;-->
<!--  border: 1px solid #ccc;-->
<!--  padding-left: 20px;-->
<!--  position: absolute;-->
<!--}-->
<!--.btn {-->
<!--  height: 35px;-->
<!--  width: 35px;-->
<!--  position: absolute;-->
<!--  /*background: url("images/topbar.png") no-repeat -2px -99px;*/-->
<!--  top: 6px;-->
<!--  left: 285px;-->
<!--  border: none;-->
<!--  outline: none;-->
<!--  cursor: pointer;-->
<!--}-->
<!--.el-select {-->
<!--  width: 130px;-->
<!--}-->
<!--.input-with-select {-->
<!--  background-color: #fff;-->
<!--}-->
<!--</style>-->
