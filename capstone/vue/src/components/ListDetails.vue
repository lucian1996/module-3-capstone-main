<template>
<div>
  <div>{{list}}</div>
    <div id="toggleClaim">
      <div v-show="isClaimed">
        <button @click="isClaimed = !isClaimed">Claim</button> <br> unClaimed <br>
      </div>
      <div v-show="!isClaimed">
        <button v-show="this.$store.state.user.id == list.claimedID"
                @click="isClaimed = !isClaimed">unClaim</button> <br>
        <div v-show="this.$store.state.user.id != list.claimedID">
            not Owner
        </div>
      </div>
    </div>
  <div>
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
import ItemCard from "./ItemCard.vue";
export default {
  components: { ItemCard },
  name: "list-details",
  data() {
    return {
      items: [],
      isClaimed: true,
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
  },
};
</script>