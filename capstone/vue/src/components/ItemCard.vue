<template>
       <div >
        <div>
            <div></div>
            <button @click="markComplete">mark complete</button>
            <button>edit item</button>
            <form v-on:submit.prevent="editItem">
              <div>
                 <label for="quantity">Quantity</label>
                  <input
                  v-model.number="quantity"
                  value=''/>
              </div>
              <div>
                <label for="name">Name</label>
                <input
                  v-model="name"
                  value="test"
                  id ="name"/>
              </div>
              <button type="submit">Save</button>
            </form>
        </div>
        </div>
</template>

<script>
import ItemService from '../services/ItemService'
//import ItemService from '../services/ItemService'

export default {
  name: 'item-card',
  props: ['itemID'],

  data () {
    return {
      quantity: 0,
      name: '',
    }
  },

  methods : {
    markComplete () { 
      this.item.status = 'complete';
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item)
      },
    editItem() {
      this.item.quantity = this.quantity;
      console.log(this.item.quantity)
      this.item.itemName = this.name;
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item).then (r =>console.log('here is the edit item response', r.data))
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