<template>
  <div>
      <h2 class="group-name">{{group}}</h2>
      
  </div>
</template>

<script>
import GroupService from '../services/GroupService'
export default {

    name: 'group-details',
    props: {
    groupID: {
      type: Number,
      default: 0
    },
    },
    methods : {
      retrieveGroup() {
        GroupService.getGroup(this.$route.params.groupID)
        .then (response => {
          this.$store.commit("SET_CURRENT_GROUP", response.data);
        })
      }
    },
    created() {
      this.retrieveGroup();
    },
    computed: {
    group() {
      return this.$store.state.group;
    }
  }
}
</script>

<style>

</style>