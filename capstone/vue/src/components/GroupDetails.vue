<template>
  <div>
    <div id="nav">
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
   return { groupErrors: false,
    groupErrorMsg: "There was a problem that prevented this action from completing."
   };
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
    //shouldn't this be elsewhere?
    removeUser() {
      console.log(
        "In components > GroupDetails > removeUser",
        this.$route.params.groupID
      );
      GroupService.removeUser(this.$route.params.groupID).then((response) => {
        this.$router.push(`/`);
        console.log(response, "removeUser response");
      })
      .catch((error => {
        const response = error.response
        this.groupErrors = true;
        if (response.status === 400) {
          this.groupErrorMsg = error.response.data.message;
        }
      }));
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

<style>

</style>
