<template>
       <div class="card">
        <div class="container">
            <div></div>
            <button @click="markComplete">mark complete</button>
            <button>edit item</button>
        </div>
        </div>
</template>

<script>
import ItemService from '../services/ItemService'
//import ItemService from '../services/ItemService'

export default {
  name: 'item-card',
  props: ['itemID'],

  methods : {
    markComplete () {
      
      this.item.status = 'complete';
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item)
      }
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