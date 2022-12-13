<template>
<div>
  <div>{{list}}</div>
  
  <div id="toggleClaim">
    <div v-show="this.list && this.list.claimedId != 0">
      <button v-show="this.list && this.$store.state.user.id == this.list.claimedId"
              @click="unclaimList()"
              >Unclaim</button> 
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
import ItemCard from "./ItemCard.vue";
export default {
  components: { ItemCard },
  name: "list-details",
  data() {
    return {
    }
  },
  methods: {
    claimList() {
    //   ListService.claimList(this.list.groupId, this.list.listId).then(response=> {
    //     if(response.status == 200) {
    //       this.isClaimed = true;
    //   this.$router.push({name: 'list-details', params: {groupID: this.list.groupId, listID: this.list.listId}}
    //     )
    //     }
    //   })
      // check listId is null
      console.log(this.list.claimedId)
      ListService.claimList(this.list.groupId, this.list.listId).then(response => {
        if(response.status == 200)
      console.log(this.$store.state.user.id)
      console.log(response)
      this.$store.commit('UPDATE_CLAIMED_ID', {listId: this.list.listId, userId: this.$store.state.user.id})
      console.log(this.list.claimedId)
      this.isClaimed = true})
        },
    unclaimList() {
      // check listId is not null
      ListService.unclaimList(this.list.groupId, this.list.listId).then(response => {
        if(response.status == 200) {
          this.$store.commit('UPDATE_CLAIMED_ID', {listId: this.list.listId, userId: null})
          this.isClaimed = false;
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