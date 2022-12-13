<template>
  <form v-on:submit.prevent="submit">
    <div class="field">
      <h2>Create List</h2>
      <label for="title"></label>
      <textarea v-model="list.name" />
    </div>
    <div class="field">
      <label for="description"></label>
      <textarea v-model="list.description"></textarea>
    </div>
    <div class="actions">
      <button type="submit">Create</button>
    </div>
  </form>
</template>

<script>
import ListService from "../services/ListService";

export default {
  name: "create-list-form",
  props: {
    groupID: {
      default: 0,
    },
  },
  data() {
    return {
      list: {
          name:'',
          description: ''
      },
    };
  },
  methods: {
    submit() {
        console.log('hi new list',this.$store.state.group.groupId);
        console.log(this.list);
      ListService.createList(this.$store.state.group.groupId, this.list).then (response => {
        if (response == 201) {
              this.$store.commit("ADD_LIST", response.data);
      }})
        //TODO: this can't be empty, else the user will never be able to navigate there
        //const data = response.dat
    },
  },
};
</script>
