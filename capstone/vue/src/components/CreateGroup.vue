<template>
  <form v-on:submit.prevent>
    <div class="field">
      <h2>Create Group</h2>
      <label for="title"></label>
      <textarea id="groupName" name="description" rows="1" cols="30" placeholder="group name"
      v-model="group.groupName" />
    </div>
    <div class="field">
      <label for="description"></label>
      <textarea id="description" name="description" rows="2" cols="30" placeholder="Description"
      v-model="group.groupDescription"></textarea>
        </div>
    <div class="actions">
      <button type="submit" v-on:click="saveGroup()">Create</button>
    </div>
  </form>
</template>

<script>
import GroupService from "../services/GroupService";

export default {
  name: 'create-group',
  data() {
    return {
      group: {
        groupDescription: '',
        groupId: '',
        groupName:	'',
        groupOwnerId:	'',
        groupCode: '',
      },
    };
  },
  methods: {
    saveGroup() {
      GroupService.createGroup(this.group).then((response) => {
         //const data = response.data
        if (response.status == 201) {
         this.group.groupName = '';
          this.group.groupDescription = '';
          this.$router.push({ name: "home" });
        }
      });
    },
  },
};
</script>

