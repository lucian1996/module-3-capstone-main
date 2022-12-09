<template>
  <div>
    <div>{{group.groupId}}</div>
        <list-container 
        v-bind:key="group.groupId" 
        v-bind:group="group" 
        />

    
  </div>
  
</template>

<script>
import GroupService from '../services/GroupService'
import ListContainer from './ListContainer.vue';
export default {
  components: { ListContainer },

    name: 'group-details',
    props: {
    groupID: {
      type: Number,
      default: 0
    },
    },
    methods : {
     
      retrieveGroup() {
        console.log('group detials print')
        GroupService.getGroup(this.$route.params.groupID)
        .then (response => {
          this.$store.commit("SET_CURRENT_GROUP", response.data);
        })
      },
    },
    created() {
      this.retrieveGroup();
      console.log(this.group)
    },
    computed: {
    group() {
      return this.$store.state.group;
    },

  }
}
</script>

<style>

</style>