<template>
  <div>
    <h1>{{ group.groupName }}</h1>
    <form v-on:submit.prevent="addUser">
      <label for="groupCode">Group code:</label>
      <input
        type="text"
        placeholder="Group Code"
        v-model="inviteCode"
        id="inviteCode"
        name="inviteCode" />
      <br />
      <button>Submit</button>
    </form>
  </div>
</template>

<script>
import GroupService from "../services/GroupService";
export default {
  name: "join-group-form",
  data() {
    return { inviteCode: "", 
    joinErrors: false,
      joinErrorMsg: "There was a problem when trying to join this group.",
      };
  },
  methods: {
    addUser() {
      if (this.group.groupCode != this.inviteCode) {
        this.joinErrors = true;
        this.joinErrorMsg = "Invalid invite code.";
      } else {
  
      GroupService.addUser(
        this.group.groupId,
        this.inviteCode,
        this.group.groupCode
      ).then(() => {
        this.$router.push(`/groups/${this.group.groupId}`);
      })
      .catch((error => {
        const response = error.response
        this.joinErrors = true;
        if (response.status === 400) {
          this.joinErrorMsg = error.response.data.message;
        }
      }));
      }
    },
  },
  computed: {
    group() {
      return this.$route.params.group;
    },
  },
};
</script>

<style></style>
