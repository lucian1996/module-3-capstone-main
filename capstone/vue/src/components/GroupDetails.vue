<template>
  <div>
    <div>{{group.groupId}}</div>
        <list-container 
        v-bind:groupId="$route.params.groupID"
        />
        <button  v-on:click="removeUser()">LEAVE GROUP!</button>
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
        console.log('In components > GroupDetails > retrieveGroup')
        GroupService.getGroup(this.$route.params.groupID)
        .then (response => {
          this.$store.commit("SET_CURRENT_GROUP", response.data);
          console.log(response, "retrieveGroup response")
        })
      },
      //todo mutation(?)
      removeUser() {
        console.log('In components > GroupDetails > removeUser', this.$store.state.group.groupID, this.$store.state.user.userID)
        GroupService.removeUser(this.$store.state.group.groupID, this.$store.state.user.userID)
        .then (response => {
          this.$store.commit(response.data)
          console.log(response, "removeUser response")
        })
      }
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