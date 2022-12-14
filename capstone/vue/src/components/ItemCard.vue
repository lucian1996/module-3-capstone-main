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
      },
    editItem() {
      this.item.quantity = this.quantity;
      console.log(this.item.quantity)
      this.item.itemName = this.name;
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item).then (r =>console.log('here is the edit item response', r.data))
    },
     removeItem() {
      ItemService.removeItem(this.item.groupId, this.item.listId, this.item.itemId).then(response => {
        if(response.status == 200) {
          this.$store.commit('DELETE_ITEM', this.item.itemId)
          this.$router.push('list-details')
        }
      })
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