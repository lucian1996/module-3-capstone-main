<template>
 <div class="card" :class="[{active: isActive}]">
   <div @click="setStatus()" v-show="!isActive">+</div>
    <form v-show="isActive" v-on:submit.prevent>
    <div v-show="phase == 1">
      <div>Name</div>
      <textarea
      placeholder="enter the group name"
      v-model="group.groupName" />
      <button @click="goToNextPhase()">Next</button>
    </div>
    <div v-show="phase == 2">
      <div>Description</div>
      <textarea
      placeholder="enter the description"
      v-model="group.groupDescription"></textarea>
    <button @click="createGroup()">Create</button>
    </div>
  </form>
 </div>
</template>

<script>
import GroupService from "../services/GroupService";

export default {
  name: "create-group-card",
  data() {
    return {
      isActive: false,
      phase: 0,
      group: {
        groupDescription: "",
        groupId: "",
        groupName: "",
        groupOwnerId: "",
        groupCode: "",
      },
    };
  },
  methods: {
    setStatus() {
      if (!this.isActive) {
        this.isActive = true;
        this.phase = 1
        console.log('here')
      }
      else {
        this.isActive = false;
        console.log('there')
      }
    },
    goToNextPhase() {
        this.phase = 2
    },
    createGroup() {
      GroupService.createGroup(this.group).then((response) => {
        if (response.status == 201) {
          this.setStatus();
        }
      });
    },
  },
};
</script>
