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

  //   mounted() {
  //   let data = this.$route.params;
  //   console.log("data is", data.list.listId);
  // }
  data() {
    return { inviteCode: "" };
  },
  methods: {
    addUser() {
      console.log(
        "In components > JoinGroupForm > addUser",
        this.group.groupId,
        this.inviteCode
      );
      GroupService.addUser(
        this.group.groupId,
        this.inviteCode,
        this.group.groupCode
      ).then((response) => {
        this.$router.push(`/groups/${this.group.groupId}`);
        console.log(response, "addUser response");
      });
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
