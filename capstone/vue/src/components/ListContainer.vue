<template>
  <div >
    <list-card
      v-for="list in lists"
      v-bind:key="list.listId"
      v-bind:list="list" />
  </div>
</template>

<script>
import ListService from "../services/ListService";
import ListCard from "./ListCard.vue";
export default {
  components: { ListCard },

  name: "list-container",
  props: {
    groupId: {
      default: 0,
    },
  },
  data() {
    return { retrieveLists: false };
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

<style>

</style>
