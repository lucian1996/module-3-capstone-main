<template>
  <div class="group-list">
    <create-group-card/>
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
import CreateGroupCard from './CreateGroupCard.vue'

export default {
  components: { GroupCard , CreateGroupCard },
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
  .group-list {
  background-color: #6b6a66;
  display: flex;
  flex-direction: row;

  text-align: center;
  line-height: 200%;
  padding: 1em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
  
  border: 5px solid #b7ec8c80;
  border-top-color: rgba(0, 255, 255, 0.493);
  border-bottom-color: rgba(127, 255, 212, 0.514);
}
</style>