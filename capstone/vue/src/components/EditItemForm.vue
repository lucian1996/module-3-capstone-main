<template>
      <v-dialog @click="dialog = true"
        v-model="dialog"
        width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="d-flex align-center justify-center"  min-height="250"
            v-bind="attrs"
            v-on="on"
          >
          Edit
          </v-btn>
        </template>
  
        <v-card class="form-card">
              <v-form v-on:submit.prevent>
              <div>
                 <label for="quantity">Quantity</label>
                  <v-text-field
                  v-model.number="quantity"
                  value=''/>
              </div>
              <div>
                <label for="name">Name</label>
                <v-text-field
                  v-model="name"
                  value="test"
                  id ="name"/>
              </div>
              <v-btn type="submit">Save</v-btn>
            </v-form>
        </v-card>
      </v-dialog>
</template>
<script>
import ItemService from '../services/ItemService'

export default {
  name: 'edit-item-form',
  data () {
    return {
      dialog: false,
      quantity: 0,
      name: '',
    }
    },
    methods : {
     editItem() {
      this.item.quantity = this.quantity;
      console.log(this.item.quantity)
      this.item.itemName = this.name;
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item).then (r =>console.log('here is the edit item response', r.data))
    }
    }
  }
</script>
