<template>
  <form v-on:submit.prevent="submit">
    <div class="field">
      <h2>Item</h2>
      <label for="title"></label>
      <textarea v-model="item.name" />
    </div>
    <div class="field">
      <label for="description"></label>
      <textarea v-model="item.quantity"></textarea>
    </div>
    <div class="actions">
      <button type="submit">Create</button>
    </div>
  </form>
</template>

<script>
import ItemService from "../services/ItemService";

export default {
  name: "create-item-form",
  props: {
    groupID: {
      default: 0,
    },
  },
  data() {
    return {
      item: {
          quantity:'',
          name: ''
      },
    };
  },
  methods: {
    submit() {
      console.table(this.$store.state.list)
      ItemService.createItem(this.$store.state.group.groupId, this.$route.params.list.listId, this.item);
        //TODO: this can't be empty, else the user will never be able to navigate there
        //const data = response.dat
    },
  },
};
</script>
