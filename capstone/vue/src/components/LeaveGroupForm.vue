<template>
<div>
  <!-- <div>{{group.groupName}}</div> -->
    <form action="">
      <div>{{group.groupName}}</div>
      <button v-show="memberStatus" @click.prevent="removeMemberFromGroup"> Leave Group</button>
    </form>
    </div>
</template>

<script>
import GroupService from '../services/GroupService'
export default {
name: 'leave-group-form',
data() {
    return {
      memberStatus: {
        type: Boolean,
        default: false},
    };
  },

methods : {
    removeMemberFromGroup(){
        console.log('Attempt to remove user from group')
        GroupService.removeUser(this.group.groupId).then (response => {
          this.$router.push('/');
          console.log(response, "removeUser response");
        })
}
},
computed : {
    group () {
      return this.$route.params.group
    },    
},
// created() {
//     showIfMemberOfGroup(this.$route.params.group) {
//         if(this.)
//     }  
}
</script>

<style>

</style>