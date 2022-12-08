<template>
   <div class="card">
    <router-link v-bind:to=" {name:'group-details', params: { groupID: this.group.groupId}}">
   
   
  <div class="container">
    <div>{{group.groupName}}</div>
  </div>
  </router-link>
</div>
</template>

<script>

export default {
  name: 'group-card',
  props: ['group'],
}
</script>


<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 100%;
  border-style: solid;
  border-color: #cccccc;
  border-width: 1px;
  border-radius: 4px;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
</style>