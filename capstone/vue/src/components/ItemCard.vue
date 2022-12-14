<template>
       <v-card >
         <div @click="log()">log</div>
         {{item.quantity}}
         {{item.itemName}}
            <v-btn @click="markComplete">mark complete</v-btn>
            <edit-item-form  v-bind:itemID="itemID"/>
        </v-card>
</template>

<script>
import ItemService from '../services/ItemService'
import EditItemForm from './EditItemForm.vue';

export default {
  components: {EditItemForm},
  name: 'item-card',
  props : ["itemID"],


  methods : {
    markComplete () { 
      this.item.status = 'complete';
      console.table(this.item)
      ItemService.editItem(this.item)
      },
      log() {
        console.log('item id here', this.itemID)
      }
    },
    created () {
    },
    computed : {
      item () {
        return this.$store.state.items.find (i => 
          i.itemId = this.itemID
        )
      }
    }
  
}
</script>

list() {
      return this.$store.state.list.find(l => 
        l.listId == this.$route.params.listID && l.groupId == this.$route.params.groupID
    )
    
    },