<template>
  <div>
    <div>{{group.groupId}}</div>
      <router-link v-bind:to=" {name:'list-container', params: { groupID: group.groupId}}">
        <list-container />
      </router-link>

    
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
        console.log('yo in here')
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