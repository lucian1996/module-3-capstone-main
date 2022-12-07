<template>
  <div class="group-list">
    <div v-for="group in groups" v-bind:key="group.id" class="group">  
      <router-link v-bind:to=" {name:'groups', params: { id:group.groupId}}">
      {{ group.groupName }}
      </router-link>
    </div>
  </div>
</template>

<script>
import GroupService from '../services/GroupService'
export default {
  name: 'group-list',
  data() {
    return {
      groups: []
    }
  },
  created() {
    GroupService.getGroups().then(response => {
      this.groups = response.data;
    })
  }
}
</script>


<style>

</style>