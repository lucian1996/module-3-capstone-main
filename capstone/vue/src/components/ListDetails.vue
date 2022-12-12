<template>
  <div>
    <div>{{list}}</div>
    <item-card 
    v-for="item in items"
    v-bind:key = "item.itemId"
    v-bind:item="item"
    />
    {{items}}
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
    };
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
  },
};
</script>

<style></style>
