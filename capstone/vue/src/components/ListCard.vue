<template>
 <v-card-title  class="list-card text-center">
    <!-- <router-link
    v-bind:to="{ name: 'list-details', params: { listID: list.listId, groupID: list.groupId } }"> 
  </router-link> -->
  <v-btn @click="gotolistDetails()">{{ list.listName }}</v-btn>
  
 </v-card-title>
</template>

<script>
export default {
  name: "list-card",
  props: ["list"],
  created() {
  },
  methods: {gotolistDetails() {
    console.log('shit balls');
    this.$router.push({ name: 'list-details', params: { listID: this.list.listID, groupID: this.list.groupID } })
  }}
  
};
</script>
<style scoped>
.list-card{
  background-color: blanchedalmond;
  text-decoration-line: none;
  height: 150px;
  width: 200px;
  border-radius: 4px;
}
</style>
