<template>
       <v-card >
        <div>
            <div></div>
            <v-btn @click="markComplete">mark complete</v-btn>
            <edit-item-form/>
        </div>
        </v-card>
</template>

<script>
import ItemService from '../services/ItemService'
import EditItemForm from './EditItemForm.vue';

export default {
  components: {EditItemForm},
  name: 'item-card',



  methods : {
    markComplete () { 
      this.item.status = 'complete';
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item)
      },
    },
    created () {
      console.warn('today im here',this.item)
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