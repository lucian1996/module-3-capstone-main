<template>
    <v-card >
    <router-link :class="{ disabled: !memberStatus }"
      v-bind:to="{
        name: 'group-details',
        params: { groupID: this.group.groupId },
      }"
    >
      <div role="button">
        <div>{{ group.groupName }}</div>
      </div>
    </router-link>

    <div v-show="!memberStatus">
      <router-link
        v-bind:to="{
          name: 'join-group-form',
          params: {
            group: this.group,
          },
        }">
        <button>join</button>
      </router-link>
    </div>
  </v-card>
  
</template>

<script>
import GroupService from "../services/GroupService";
export default {
  name: "group-card",
  props: ["group"],
  data() {
    return {
      memberStatus : false
    }
  },

  methods: {
    getMemberStatus() {
      GroupService.getMemberByUsername(this.group.groupId, this.$store.state.user.username)
      .then (response => {
          this.memberStatus = response.data;
        }
      )
    },
  },
  
  mounted() {
   this.getMemberStatus();
  }
}
</script>

<style>

</style>
