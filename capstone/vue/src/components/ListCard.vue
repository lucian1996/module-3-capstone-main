<template>
 <div class="card">
    <router-link
    v-bind:to="{ name: 'list-details', params: { listID: list.listId, groupID: list.groupId } }">
    <div>
      <div class="container">
        <div>{{ list.listName }}</div>
        <div></div>
      </div>
    </div>
  </router-link>
 </div>
</template>

<script>
export default {
  name: "list-card",
  props: ["list"],
  created() {
    console.log("here", this.list.listId);
  },
};
</script>
