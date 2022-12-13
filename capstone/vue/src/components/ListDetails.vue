<template>
  <div>
    <div>{{list}}</div>
    <item-card 
    v-for="item in items"
    v-bind:key = "item.itemId"
    v-bind:item="item"
    />
    {{items}}
      <create-item-form  v-bind:groupID="groupID"/>
  </div>
</template>

<script>
import ItemService from '../services/ItemService'
import CreateItemForm from './CreateItemForm.vue';
import ItemCard from './ItemCard.vue'
export default {
  components: { ItemCard, CreateItemForm },
  name: 'list-details',
  data() {
    return {
      items: [],
      groupID: this.$store.state.group.groupId
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
