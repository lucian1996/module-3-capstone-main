<template>
 <v-card-title class="list-card text-center">
    <router-link
    v-bind:to="{ name: 'list-details', params: { listID: list.listId, groupID: list.groupId } }">
      \ {{ list.listName }}
  </router-link>
 </v-card-title>
</template>

<script>
export default {
  name: "list-card",
  props: ["list"],
  created() {
  },
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
