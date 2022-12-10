<template>
   <router-link v-bind:to=" {name:'list-details', params: { list: this.list}}">
       <div class="card">
        <div class="container">
            <div>{{list.listName}}</div>
            <div> </div>
        </div>
        </div>
   </router-link>
</template>

<script>

export default {
  name: 'list-card',
  props: ['list'],
  created () {
      console.log('here', this.list.listId)
  }
}
</script>