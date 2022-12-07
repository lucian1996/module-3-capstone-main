<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="title">Group Name</label>
      <input type="text" v-model="group.groupName" />
    </div>
    <div class="field">
      <label for="description">What is this group to do?</label>
      <textarea id="description" name="description" rows="2" cols="10" placeholder="Description"
      v-model="group.groupDescription"></textarea>
        </div>
    <div class="actions">
      <button type="submit" v-on:click="saveGroup()">create Group</button>
    </div>
  </form>
</template>

<script>
import GroupService from "../services/GroupService";

export default {
  name: "create-group",
  data() {
    return {
      group: {
        groupDescription: '',
        groupId: '',
        groupName:	'',
        groupOwnerId:	'',
        users: []
      },
    };
  },
  methods: {
    saveGroup() {
      GroupService.createGroup(this.group).then((response) => {
         
        if (response.status == 201) {
         this.group.groupName = '';
          this.groupDescription = '';
          this.$router.push({ name: "home" });
        }
      });
    },
  },
};
</script>