<template>
<div>
  <div data-app id="container">
      <create-list-form />
     <list-card
      v-for="list in lists"
      v-bind:key="list.listId"
      v-bind:list="list" />
  </div>
   {{lists}}
</div>
</template>

<script>
import ListService from "../services/ListService";
import CreateListForm from './CreateListForm.vue';
import ListCard from "./ListCard.vue";
export default {
  components: { ListCard, CreateListForm },

  name: "list-container",


  data() {
    return { 
      appTitle: 'Fridgrr',
      menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
     ], 
     };
  },
  methods: {
    getLists() {
     ListService.getLists(this.$route.params.groupID)
      .then((response) => {
        this.$store.commit("SET_CURRENT_LISTS", response.data)
        console.log(this.$store.state.list)
     });
    },
  },
  created() {

    this.getLists();
    console.log(this.$route.params.groupID)
  },

  computed: {
    lists() {
       return this.$store.state.list;
    }
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
