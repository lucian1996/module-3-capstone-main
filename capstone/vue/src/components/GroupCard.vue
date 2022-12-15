<template>
    <v-card  class=" align-center justify-center"  min-height="250" min-width="250" >
    <router-link class="text-decoration-none"
      v-bind:to="{
        name: 'group-details',
        params: { groupID: this.group.groupId },
      }"
    >
      <div role="button">
        <v-list-item>
          <v-list-item-content class="text-center">
            {{ group.groupName }}
          </v-list-item-content>
        </v-list-item>
        
      </div>
    </router-link>
  <v-card-action>
       <v-btn v-show="!memberStatus">
      <router-link class="text-decoration-none"
        v-bind:to="{
          name: 'join-group-form',
          params: {
            group: this.group,
          },
        }">
        Join
      </router-link>
    </v-btn>
  </v-card-action>
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
<style scoped>
v-card > a {
  text-decoration: none !important;

}
router-link a{
    text-decoration: none !important;
}


</style>