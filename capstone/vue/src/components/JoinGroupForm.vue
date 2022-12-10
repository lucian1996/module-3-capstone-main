<template>
  <div>
    <div>{{group}}</div>
    
  </div>
  
</template>

<script>
export default {

    name: 'join-group-form',

  //   mounted() {
  //   let data = this.$route.params;
  //   console.log("data is", data.list.listId);
  // }
  computed : {
    group () {
      return this.$route.params.group
    }
  }
  } 
</script>

<style>

</style>