<template>
  <div class="card"
      :class="[{ active: isActive}, card-form] ">
    <div @click="setStatus()" v-show="!isActive">+</div>
    <form v-show="isActive" v-on:submit.prevent="submit">
      <label for="title"></label>
      <input v-model="list.name" 
      placeholder="enter the name"
      />
      <label for="description"></label>
      <textarea v-model="list.description"
      placeholder="enter the description">
      </textarea>
      <button type="submit">Create</button>
  </form></div>
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
      isActive: false,
      list: {
          name:'',
          description: ''
      },
      listErrors: false,
      listErrorMsg: "There was a problem creating this list.",
    };
  },
  methods: {
    setStatus() {
      console.log("status", this.status)
      if (this.isActive == true) {
        this.isActive = false;
      } else {
        this.isActive = true;
      }
    },
    submit() {
      if (this.list.name.length <= 0) {
        this.listErrors = true;
        this.listErrorMsg = "List name cannot be blank."
      } else {ListService.createList(this.$store.state.group.groupId, this.list)
      .then (response => {
        if (response == 201) {
              this.$store.commit("ADD_LIST", response.data);
              this.setStatus();
      }})
        //TODO: this can't be empty, else the user will never be able to navigate there
        //const data = response.dat
    }
    }
  },
};
</script>
