<template>
<div>
  <div>{{list}}</div>

  <div id="toggleClaim">
    <div v-show="isClaimed">
      <button v-show="this.$store.state.user.id == list.claimedID"
              @click="unclaimList"
              >unClaim</button> 
      <div v-show="this.$store.state.user.id != list.claimedID">
            <br> Claimed by another User
      </div>
    </div>
    <div v-show="!isClaimed">
          <button v-show="this.$store.state.user.id != list.claimedID"
                  @click="claimList"
                  >Claim</button> <br>
    </div>
  </div>

  <div id="listItems">
    <item-card 
    v-for="item in items"
    v-bind:key="item.itemId"
    v-bind:item="item"/>
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
      items: [],
      isClaimed: false,
    }
  },
  methods: {
    claimList() {
      // check listId is null
    ListService.claimList(this.list.groupId, this.list.listId)
    this.isClaimed = true;
  },
    unclaimList() {
      // check listId is not null
      ListService.unclaimList(this.list.groupId, this.list.listId)
      this.isClaimed = false;
    }
  },
  created() {
    ItemService.getItems(
      this.$route.params.list.groupId,
      this.$route.params.list.listId
    ).then((response) => {
      this.items = response.data;
      console.log("here are items", this.items);
    });
  },
  computed: {
    list() {
      return this.$route.params.list;
    },

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