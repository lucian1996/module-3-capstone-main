<template>
<div>
  <create-item-form/>
  <div id="toggleClaim">
    <div v-show="this.list && this.list.claimedId != 0">
      <button v-show="this.list && this.$store.state.user.id == this.list.claimedId"
              @click="unclaimList()"
              >Unclaim</button> 
        <button v-show="this.list && this.$store.state.user.id == this.list.claimedId
                        && this.list.listCompleted == false"
          @click="completeList()">Mark List complete</button>
          <button v-show="this.list && this.$store.state.user.id == this.list.claimedId 
                            && this.list.listCompleted == true"
          @click="ListIncomplete()">Mark List Incomplete</button>
      <div v-show="this.list && this.$store.state.user.id != this.list.claimedId">
            <br> Claimed by another User
      </div>
    </div>
    <div v-show="this.list && this.list.claimedId == 0">
          <button v-show="this.list && this.$store.state.user.id != this.list.claimedId"
                  @click="claimList()"
                  >Claim</button> <br>
    </div>
  </div>

  <div id="listItems">
    <item-card 
    v-for="item in items"
    v-bind:key="item.dateModified"
    v-bind:itemID="item.itemId"/>
    {{items}}
  </div>
</div>
</template>

<script>
import ItemService from "../services/ItemService";
import ListService from '../services/ListService';
import CreateItemForm from './CreateItemForm.vue';
import ItemCard from "./ItemCard.vue";
export default {
  components: { ItemCard, CreateItemForm },
  name: "list-details",
  data() {
    return {
      listComplete: ''
    }
  },
  methods: {
    claimList() {
      ListService.claimList(this.list.groupId, this.list.listId).then(response => {
        if(response.status == 200)
      this.$store.commit('UPDATE_CLAIMED_ID', {listId: this.list.listId, userId: this.$store.state.user.id})
      })
        },
    unclaimList() {
      ListService.unclaimList(this.list.groupId, this.list.listId).then(response => {
        if(response.status == 200) {
          this.$store.commit('UPDATE_CLAIMED_ID', {listId: this.list.listId, userId: 0})
        }
      })
    
      },
    getItems () {
      ItemService.getItems(
      this.$route.params.groupID,
      this.$route.params.listID
      ).then((response) => {
      this.$store.commit("SET_ITEMS", response.data)
      console.log("here are items", this.items);
    });
    },
    completeList() {
        this.listComplete = true;
        ListService.markListComplete(this.list.groupId, this.list.listId, this.isComplete).then(response => {
          if(response.status == 200) {
           this.$store.commit('MARK_LIST_COMPLETE', {groupId: this.list.groupId, listId: this.list.listId, isComplete: this.isComplete})
          }
        })
      },
      ListIncomplete() {
        this.listComplete = false;
        ListService.markListIncomplete(this.list.groupId, this.list.listId).then(response => {
          if(response.status == 200) {
            this.$store.commit('MARK_LIST_INCOMPLETE', {groupId: this.list.groupId, listId: this.list.listId, isComplete: this.isComplete})
          }
        })
      }
    },
  created() {
    this.getItems()
  },
  
  computed: {

    list() {
      return this.$store.state.list.find(l => 
        l.listId == this.$route.params.listID && l.groupId == this.$route.params.groupID
    )
    
    },
    items() {
      return this.$store.state.items;
    }

    // isClaimedVerification() {
    //   if (this.list.claimedId == 0) {
    //     return this.isClaimed = false 
    //   } else {
    //     return this.isClaimed = true 
    //   }
    // }

    // upadateClaimID() {
    //   if (isClaimed == true) {
    //     return this.list.claimedId = this.user.id
    //   }
    // } 

  },
};
</script>