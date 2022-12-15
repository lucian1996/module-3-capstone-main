<template>
       <v-card  max-width ="800 outlined">
         <v-list-item>
          <v-list-item-content class="incomplete" v-bind:class="[{ complete: isComplete }]">
           <div class="mx-auto"
      max-width="800"
      outlined>
              {{item.quantity}}
              {{item.itemName}}
            </div>
            </v-list-item-content>
         </v-list-item>
         <v-list-item>
          <v-list-item-content>
  
            </v-list-item-content>
         </v-list-item>
         <v-card-actions>
            <v-btn @click="markComplete">
              <div v-show="!isComplete">Mark Complete</div>
                <div v-show="isComplete">Mark Incomplete</div>
            </v-btn>
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
      this.item.status = !this.item.status;
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
}
.incomplete {
}
</style>