<template>
  <div>
    <div>{{group}}</div>
    <form action="">
      <label for="Join Group Code"></label>
      <input type="text" placeholder="Group Code" id="">
      
    </form>
    
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