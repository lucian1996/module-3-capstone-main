<template>
  <div>
    <div>{{list}}</div>
    {{items}}
    <button></button>
  </div>
  
</template>

<script>
import ItemService from '../services/ItemService'
export default {
  name: 'list-details',
  data() {
    return {
      items : []
    }
  },
 
    created () {
      ItemService.getItems.then(response => {
        this.items = response.data;
      })
    },
     computed : {
    list () {
      return this.$route.params.list
    },
  }
  } 
</script>

<style>

</style>