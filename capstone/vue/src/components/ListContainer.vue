<template>
<div>

  <!-- <v-toolbar app>
      <v-toolbar-title id="title">
          {{ appTitle }}
      </v-toolbar-title>
    </v-toolbar>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          flat
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          {{ item.title }}
        </v-btn> 
      </v-toolbar-items> -->

  <div id="container">
    <create-list-form  v-bind:groupID="groupID"/>
    <list-card
      v-for="list in lists"
      v-bind:key="list.listId"
      v-bind:list="list" />
  </div>
</div>
</template>

<script>
import ListService from "../services/ListService";
import CreateListForm from './CreateListForm.vue';
import ListCard from "./ListCard.vue";
export default {
  components: { ListCard, CreateListForm },

  name: "list-container",
  props: {
    groupId: {
      default: 0,
    },
  },
  data() {
    return { 
      retrieveLists: false,
      appTitle: 'Fridgrr',
      menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
     ], 
     };
  },
  methods: {
    getLists() {
      console.log("list container", this.groupId);
      ListService.getLists(this.groupId)
      .then((response) => {
        this.$store.commit("SET_CURRENT_LISTS", response.data);
        console.log(response, "response");
        this.retrieveLists = true;
      });
    },
  },
  created() {
    console.log("In components > ListContainer > create");
    this.getLists();
    console.log(this.lists);
  },

  computed: {
    lists() {
      console.log(this.groupId, "groupId in lists()", this.retrieveLists);
      if (this.groupId == 0) {
        console.log("made it to first if");
        return [];
      } else if (this.retrieveLists) {
        console.log("made to else if");
        return this.$store.state.list;
      } else {
        this.getLists();
        console.log("made to else");
        return [];
      }
    },
  },
};
</script>

<style scoped>
#container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 20px;
  width: 100%;
  height: 500px;
}
#title{
  font-family:    'Courier New', Courier, monospace;
  font-size:      50px;
  font-weight:    bold;
  color:          whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content{
  font-family:    'Courier New', Courier, monospace;
  font-size:      15px;
  font-weight:    bolder;
}
.v-toolbar__content{
  background-color: #0EAD69;
}
</style>
