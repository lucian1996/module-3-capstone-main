<template>
       <v-card  class="incomplete" v-bind:class="[{ complete: isComplete }]" max-width ="400 outlined">
         <v-list-item>
          <v-list-item-content>
           <div class="mx-auto"
      max-width="344"
      outlined>
               Quantity: {{item.quantity}}
                Name: {{item.itemName}}
                
            </div>
            </v-list-item-content>
         </v-list-item>
         <v-list-item>
          <v-list-item-content>
  
            </v-list-item-content>
         </v-list-item>
         <v-card-actions>
            <v-btn @click="markComplete">mark complete</v-btn>
            <edit-item-form  v-bind:itemID="itemID"/>
         </v-card-actions>
        </v-card>
</template>

<script>
import ItemService from '../services/ItemService'
import EditItemForm from './EditItemForm.vue';

export default {
  components: {EditItemForm},
  name: 'item-card',
  props : ["itemID"],
  data () {
    return {
      isComplete: false
    }
  },

  methods : {
    markComplete () { 
      this.item.status = true;
      ItemService.editItem(this.item)
      this.$router.go()
      },
    editItem() {
      this.item.quantity = this.quantity;
      this.item.itemName = this.name;
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item)

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
      this.isComplete = this.item.status;
    },
    computed : {
      item () {
        return this.$store.state.items.find (i => 
          i.itemId == this.itemID
        )
      },
      
    }
  
}
</script>
<style scoped>
.complete {
  text-decoration: line-through;
  background-color: gray !important;
}
.incomplete {
  background-color: rgb(173, 173, 248);
}
</style>