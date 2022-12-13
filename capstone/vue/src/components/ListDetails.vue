<template>
  <div>
    <div>{{list}}</div>
      <div id="toggleClaim" >
        <div v-if="isClaimed" @click="isClaimed = !isClaimed">
          <button >Claim</button> <br>
        </div>
        <div v-show="isClaimed">
          <button v-show="this.$store.state.user.id==list.claimedID">unClaim</button> <br>
          <div v-show="this.$store.state.user.id!=list.claimedID">
            List Claimed by
          </div>
        </div>
      </div>
      <div>
        <item-card 
        v-for="item in items"
        v-bind:key="item.itemId"
        v-bind:item="item"
        />
        {{items}}
      </div>
</div>
</template>

<script>
import ItemService from "../services/ItemService";
import ItemCard from "./ItemCard.vue";
export default {
  components: { ItemCard },
  name: "list-details",
  data() {
    return {
      items: [],
      isClaimed: true,
    };
  },
  methods: {
    // getIsClaimed() {
    //   if ( this.$store.state.user.userId
    //     == this.$store.state.list.claimedId ) {
    //     isClaimed = true
    //   }
    // },

    getListById () {

    }
  },
  created() {
    ItemService.getItems(
      this.$route.params.groupID,
      this.$route.params.listID
    ).then((response) => {
      this.items = response.data;
      console.log("here are items", this.items);
    });
  },
  computed: {

    list() {
      return this.$store.state.list.find(l => 
        l.listId == this.$route.params.listID && l.groupId == this.$route.params.groupID
    )
    },
  },
};
</script>

<style></style>
