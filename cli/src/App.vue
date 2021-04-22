<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: "App",
  data() {
    return {
      activeIndex: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key).catch(err => {});
    }
  }
}
</script>

<style>
.app_header{
  float: left;
  width: 100%;
}
</style>
