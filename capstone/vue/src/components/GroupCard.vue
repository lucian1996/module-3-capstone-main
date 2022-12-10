<template>
<!-- TODO @click="group-details(this.group.groupId)" -->
  <div class="card btn" role="button">
    <router-link
      v-bind:to="{
        name: 'group-details',
        params: { groupID: this.group.groupId },
      }">
      <div class="container btn" role="button">
        <div>{{ group.groupName }}</div>
        <div></div>
      </div>
    </router-link>

    <router-link
      v-bind:to="{ name: 'join-group-form', params: { group: this.group } }"> 
      <button v-show=groupMember>join</button>
      <button v-show=!groupMember>Leave</button>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "group-card",
  props: ["group"],
  data() {
    return {
      // TODO Wire this into if $store user is member of selected group
        groupMember: true
    };
  },
  // methods: {
  //   leaveJoinButton(){
  //     if(user is a member of group){
  //       display Leave group as a clickable button
  //     }
  //     display Join group which sends you to a joinGroup page
  //   }
  // }
};
</script>

<style>
.card {
  width: 100px;
  text-overflow: ellipsis;
  height: 100px;
  background-color: #51434a;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  align-items: center;
  border-radius: 6%;
  padding: 1em;
  padding-top: 0em;
  padding-bottom: 0em;
  margin: 0.5em;
  margin-top: 0.25em;
  margin-bottom: 0.25em;
  
}
.card > div {
  text-overflow: ellipsis;
}

.card:hover {
  box-shadow: 0px 1px 2px 3px #f09374c4;
  text-emphasis: bolder;
}
</style>
