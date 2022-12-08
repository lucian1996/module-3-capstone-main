<template>
  <div class="group-list">
    <group-card 
    v-for="group in groups" 
    v-bind:key="group.groupId" 
    v-bind:group="group" 
    />
  </div>
</template>

<script>
import GroupService from '../services/GroupService'
import GroupCard from './GroupCard.vue'
export default {
  components: { GroupCard },
  name: 'group-list',
  data() {
    return {
      groups: []
    }
  },
  created() {
    GroupService.getGroups().then(response => {
      this.groups = response.data;
      console.log(this.groups)
    })
  }
}
</script>


<style>

</style>