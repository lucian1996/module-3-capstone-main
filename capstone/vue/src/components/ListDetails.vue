<template>
  <div>
    <div>{{list}}</div>
      <div id="toggleClaim" @click="isClaimed = !isClaimed">
        <div v-if="isClaimed">
          <button>Claim</button> <br>
        </div>
        <div v-else>
          <button>unClaim</button> <br>
        </div>
      </div>

    <div>
        <item-card 
        v-for="item in items"
        v-bind:key = "item.itemId"
        v-bind:item="item"
        />
        {{items}}
      </div>
</div>
</template>

<script>
import ItemService from '../services/ItemService'
import ItemCard from './ItemCard.vue'
export default {
  components: { ItemCard },
  name: 'list-details',
  data() {
    return {
      items: [],
      isClaimed: false
    };
  },
  methods: { 
    getIsClaimed() {
      // if ({{ this.$store.state.user.username }} 
      //   == {{ this.$store.state.list.claimedId }}) {
      //   isClaimed = true
      // }
    },
  },
  created () {
    ItemService.getItems(this.$route.params.list.groupId, this.$route.params.list.listId)
    .then(response => {
      this.items = response.data;
      console.log("here are items", this.items)
    })
  },
  computed : {
    list () {
      return this.$route.params.list
    },
    claimStatus() {
      // if (isClaimed) {
      //    return {{ this.$store.state.list.claimedId }} == null;
      // } else {
      //    return {{ this.$store.state.list.claimedId }} == {{ this.$store.state.user.username }};
      // } 
      return null
    }
  },
};
</script>

<style></style>
