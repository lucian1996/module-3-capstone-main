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
        return console.log('Attempt to remove user from group')
        // Should remove group member then redirect member to "/"
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