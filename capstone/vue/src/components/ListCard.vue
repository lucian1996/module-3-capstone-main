<template>
 <v-card class="d-flex align-center justify-center"  min-height="250">
    <router-link
    v-bind:to="{ name: 'list-details', params: { listID: list.listId, groupID: list.groupId } }">
    <div>
      <div>
        <div>{{ list.listName }}</div>
        <div></div>
      </div>
    </div>
  </router-link>
 </v-card>
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
