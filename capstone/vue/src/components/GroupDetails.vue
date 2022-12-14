<template>
  <div>
    <div id="nav">

       <v-toolbar app>
      <v-toolbar-title id="title">
          {{ appTitle }}
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          flat
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>

      <!-- <div>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link> &nbsp;
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
      </div> -->
    </div>
    <h1>{{ group.groupName }}</h1>
    <h2>{{group.groupDescription}}</h2>
    <div>
      
    <list-container v-bind:groupId="$route.params.groupID" />
    </div>
    <button v-on:click="removeUser()">LEAVE GROUP!</button>
    {{group}}
  </div>
</template>

<script>
import MemberService from "../services/MemberService"
import GroupService from "../services/GroupService";
import ListContainer from "./ListContainer.vue";
export default {
  components: { ListContainer },

  name: "group-details",
  props: {
    groupID: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
    appTitle: 'Fridgrr',
        menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
     ],
  }
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
    retrieveMembers() {
     MemberService.getMembers(this.$route.params.groupID).then((response) => {
        this.$store.commit("SET_CURRENT_MEMBERS", response.data);
        console.log(response.data, "retrieveMembers response");
     });
    },

    groupDate() {
      
      GroupService.getGroupCreatedDate(this.group.groupID).then((response) =>
      {console.log(response, "groupDate response")})
    }
  },
  created() {
    this.retrieveGroup();
    this.retrieveMembers();
    console.log(this.group);
  },
  computed: {
    group() {
      return this.$store.state.group;
    },
    members() {
      return this.$store.state.members;
    }
  },
};
</script>

<style scoped>
#title{
  font-family:    'Courier New', Courier, monospace;
  font-size:      50px;
  font-weight:    bold;
  color:          whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content{
  font-family:    'Courier New', Courier, monospace;
  font-size:      15px;
  font-weight:    bolder;
}
.v-toolbar__content{
  background-color: #0EAD69;
}
</style>
