<template>
      <v-dialog @click="dialog = true"
        v-model="dialog"
        width="600px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-card class="d-flex align-center justify-center"  min-height="250"
            v-bind="attrs"
            v-on="on"
          >
          +
          </v-card>
        </template>
  
        <v-card class="form-card">
              <v-form v-on:submit.prevent>
              <v-text-field
              label="Name"
              placeholder="enter the group name"
              v-model="group.groupName"/>
              <v-textarea
              label="Description"
              placeholder="enter the description"
              v-model="group.groupDescription">
              </v-textarea>
              <v-row justify="center">
                 <v-btn @click="createGroup()" color="primary" elevation="2">Create</v-btn>
              </v-row>
            </v-form>
        </v-card>
      </v-dialog>
</template>

<script>
import GroupService from "../services/GroupService";

export default {
  name: "create-group-card",
  data() {
    return {
      dialog: false,
      group: {
        groupDescription: "",
        groupId: "",
        groupName: "",
        groupOwnerId: "",
        groupCode: "",
      },
      groupErrors: false,
      groupErrorMsg: "There was a problem creating this group."
    };
  },
  methods: {
    createGroup() {
      if (this.group.groupName == "") {
        this.groupErrors = true;
        this.groupErrorMsg = "The group name cannot be blank."
      }
      GroupService.createGroup(this.group).then((response) => {
        if (response.status == 201) {
          this.dialog = false
          this.$router.go()
        }
      });
    },
  },
};
</script>
