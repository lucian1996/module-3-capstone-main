<template>
  <html>
    <header>
      <v-toolbar app>
        <v-toolbar-title id="title">
          \ {{ appTitle }} \ {{ this.$store.state.user.username }} \
          {{ group.groupName }}
        </v-toolbar-title>

        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn
            flat
            color="blanchedalmond"
            v-for="item in menuItems"
            :key="item.title"
            :to="item.path">
            {{ item.title }}
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
    </header>

    <body>
      <list-container v-bind:groupId="$route.params.groupID" />
        <div v-show="!isOwner" @click="removeUser()" class="card">
        <img src="@/assets/lord.png" alt="not working">
        <div class="info">
        <h4>Leave Group</h4>
        </div>
        </div>
     
      <div>
        <div data-app id="container">
          <item-card
            v-for="item in items"
            v-bind:key="item.dateModified"
            v-bind:itemID="item.itemId"
            :class="{ complete: listComplete == true }" />
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import MemberService from "../services/MemberService";
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
      isOwner: false,
    appTitle: 'Fridgrr',
        menuItems: [
          { title: 'groups', path: '/groups' },
          { title: 'members', path: '/members'},
          { title: 'logout', path: '/' },
     ],
    groupErrors: false,
    groupErrorMsg: "There was a problem that prevented this action from completing."
   };
  },
  methods: {
    retrieveGroup() {
      GroupService.getGroup(this.$route.params.groupID).then((response) => {
        this.$store.commit("SET_CURRENT_GROUP", response.data);
      });
    },

    removeUser() {
      GroupService.removeUser(this.$route.params.groupID)
        .then(() => this.$router.push("/groups"))
        .catch((error) => {
          const response = error.response;
          this.groupErrors = true;
          if (response.status === 400) {
            this.groupErrorMsg = error.response.data.message;
          }
        });
    },
    retrieveMembers() {
      MemberService.getMembers(this.$route.params.groupID).then((response) => {
        this.$store.commit("SET_CURRENT_MEMBERS", response.data);
      });
    },

    groupDate() {
      GroupService.getGroupCreatedDate(this.group.groupID);
    },
    getMembers() {
      console.log("test");
    },
  },

  created() {
    this.retrieveGroup();
    this.retrieveMembers();
  },
  mounted () {
     if (this.$store.state.user.userId == this.$store.state.group.ownerId) {
      this.isOwner = true;
    }
  },
  computed: {
    group() {
      return this.$store.state.group;
    },
    members() {
      return this.$store.state.members;
    },


  },
};
</script>

<style scoped>
#title {
  font-family: "Courier New", Courier, monospace;
  font-size: 50px;
  font-weight: bold;
  color: whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content {
  font-family: "Courier New", Courier, monospace;
  font-size: 15px;
  font-weight: bolder;
}
.v-toolbar__content {
  background-color: #0ead69;
}
.theme--light.v-btn.v-btn--has-bg {
  background-color: #0ead69;
}
</style>
