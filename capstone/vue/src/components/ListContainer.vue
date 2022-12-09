<template>
  <div class="group-list">
    <!-- <list-card 
        v-for="list in lists"
        v-bind:key = "list.listId"
        v-bind:list="list"
     /> -->
    {{ lists }}
  </div>
</template>

<script>
import ListService from '../services/ListService'
//import ListCard from './ListCard.vue'
export default {
    //components: { ListCard },
  
  name: 'list-container',
   props: {
    groupId: {
      type: Number,
      default: 0
    },
   },
    data () { 
        return {retrieveLists: false};
    },
  methods : {
       getLists() {
           console.log("get lits method ", this.groupId);
        ListService.getLists(this.groupId)
        .then (response => {
          this.$store.commit("SET_CURRENT_LISTS", response.data);
          console.log(response, "response");
          this.retrieveLists = true;
        })
      },
  },
  created () {
          console.log('created container');
        this.getLists();
        console.log(this.lists);
        },
  
  computed : {
       lists() {
           console.log(this.groupId, "groupId in lists()", this.retrieveLists)
           if (this.groupId == 0) {
               console.log('made it to first if')
               return [];
           }
           else if (this.retrieveLists) {
               console.log('made to else if');
               return this.$store.state.list;
           }
        else {
            this.getLists();
            console.log('made to else')
            return [];
            }
            
    }
  }
}
</script>

<style>
.group-list {
  display: grid;
  /* grid-template-columns: 7fr 7fr; */
  grid-column-gap: 0px;
  grid-row-gap: 8px;
  justify-items: stretch;
  align-items: stretch;
  grid-template-areas: "1 1 1 1 1 1 1 1 1";

  background-color: #3a2e34;
  /* display: flex; */
  /* flex-direction: row; */
  /* flex-wrap: wrap; */

  text-align: center;
  line-height: 200%;
  padding: 1em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 2.85%;

  /* border: 5px solid #7db892; */
  border-top-color: #6c80a1;
  border-bottom-color: #6c80a1;
}
</style>

