<template>
  <div class="card btn" role="button">
    <router-link :class="{ disabled: memberStatus }"
      v-bind:to="{
        name: 'group-details',
        params: { groupID: this.group.groupId },
      }"
    >
      <div class="container btn" role="button">
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
        }"
      >
        <button>join</button>
      </router-link>
    </div>

    <!-- <div v-show="this.$root.isMember==1">
      <router-link
        v-bind:to="{ name: 'leave-group-form', params: { group: this.group } }">
        <div>leave</div>
      </router-link>
    </div> -->
  </div>
  
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
.disabled {
    pointer-events:none; 
    opacity:0.6;        
 }
</style>
