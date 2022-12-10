<template>
  <div>
    <div>{{list}}</div>
    
  </div>
  
</template>

<script>
export default {

    name: 'list-details',

  //   mounted() {
  //   let data = this.$route.params;
  //   console.log("data is", data.list.listId);
  // }
  computed : {
    list () {
      return this.$route.params.list
    }
  }
  } 
</script>

<style>

</style>