<template>
  <div>
    <div id="nav">
      <div>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link> &nbsp;
        <button>Filter</button>
      </div>
      <div>
        <router-link
          v-bind:to="{ name: 'editUser' }"
          v-if="$store.state.token != ''"
          >{{ $store.state.user.username }}</router-link> &nbsp;|&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link>
      </div>
      <div>{{group.dateCreated}}</div>
    </div>
    <h1>hi {{ group.groupName }}</h1>
    <div>
      Group Code: <br />
      <div id="groupCode">{{ group.groupCode }}</div>
      <div>
        {{group.dateCreated}}
    </div>

    </div>
    <create-list-form  v-bind:groupID="groupID"/>
    <list-container v-bind:groupId="$route.params.groupID" />
    <button v-on:click="removeUser()">LEAVE GROUP!</button>
    {{group}}
  </div>
</template>

<script>
import GroupService from "../services/GroupService";
import CreateListForm from './CreateListForm.vue';
import ListContainer from "./ListContainer.vue";
export default {
  components: { ListContainer, CreateListForm },

  name: "group-details",
  props: {
    groupID: {
      type: Number,
      default: 0,
    },
  },
  methods: {
    retrieveGroup() {
      console.log('groupID', this.groupID)
      console.log("In components > GroupDetails > retrieveGroup");
      GroupService.getGroup(this.$route.params.groupID).then((response) => {
        this.$store.commit("SET_CURRENT_GROUP", response.data);
        console.log(response, "retrieveGroup response");
      });
    },
    //todo mutation to discard current group from $store?
    //shouldn't this be elsewhere?
    removeUser() {
      console.log(
        "In components > GroupDetails > removeUser",
        this.$route.params.groupID
      );
      GroupService.removeUser(this.$route.params.groupID).then((response) => {
        this.$router.push(`/`);
        console.log(response, "removeUser response");
      });
    },
    groupDate() {
      
      GroupService.getGroupCreatedDate(this.group.groupID).then((response) =>
      {console.log(response, "groupDate response")})
    }
  },
  created() {
    this.retrieveGroup();
    // this.groupDate();
    console.log(this.group);
  },
  computed: {
    group() {
      return this.$store.state.group;
    },
  },
};
</script>

<style>
#groupCode {
  color: #f09374;
}
</style>
