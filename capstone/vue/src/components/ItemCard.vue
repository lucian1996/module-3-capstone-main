<template>
       <div class="card">
        <div class="container">
            <div>{{item.itemId}}</div>

            <button @click="markComplete">mark complete</button>
            <button>edit item</button>
        </div>
        </div>
</template>

<script>
//import ItemService from '../services/ItemService'

export default {
  name: 'item-card',
  props: ['item'],

  methods : {
    markComplete () {
      //ItemService.
      }
    },
    created () {
      console.warn('today im here',this.item)
    }
  
}
</script>