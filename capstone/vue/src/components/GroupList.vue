<template>
  <div class="group-list">
    <create-group-card />
    <group-card
      v-for="group in groups"
      v-bind:key="group.groupId"
      v-bind:group="group" />
  </div>
</template>

<script>
import GroupService from "../services/GroupService";
import GroupCard from "./GroupCard.vue";
import CreateGroupCard from "./CreateGroupCard.vue";

export default {
  components: { GroupCard, CreateGroupCard },
  name: "group-list",
  data() {
    return {
      groups: [],
    };
  },
  created() {
    GroupService.getGroups().then((response) => {
      this.groups = response.data;
    });
  },
};
</script>

<style>
.group-list {
  display: grid;
  grid-column-gap: 0px;
  grid-row-gap: 8px;
  justify-items: stretch;
  align-items: stretch;
  grid-template-areas: "1 1 1 1 1 1 1 1 1";
  background-color: #3a2e34;
  text-align: center;
  line-height: 200%;
  padding: 1em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 2.85%;

  border: 4px solid #7db892;
  border-top-color: #6c80a1;
  border-bottom-color: #6c80a1;
}
@media only screen and (max-width: 1400px) {
  .group-list {
    display: grid;
    grid-template-areas: " 1 1 1 1 1 ";
    grid-template-columns: 1fr;
  }
}
@media only screen and (max-width: 820px) {
  .group-list {
    display: grid;
    grid-template-areas: " 1 1 1 ";
    grid-template-columns: 1fr;
  }
}
@media only screen and (max-width: 525px) {
  .group-list {
    display: grid;
    grid-template-areas: " 1 1 ";
    grid-template-columns: 1fr;
  }
}
</style>
