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
  background-color:#3a2e34;
  display: flex;
  flex-direction: row;

  text-align: center;
  line-height: 200%;
  padding: 1em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
  
  /* border: 5px solid #7db892; */
  border-top-color: #6c80a1;
  border-bottom-color: #6c80a1;
}
</style>