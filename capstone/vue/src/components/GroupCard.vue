<template>
       <div class="wrap">
        <div class="card">
             <img src="@/assets/lord.png" alt="not working">
          <div class="info">
            <h4>
              <router-link class="text-decoration-none"
              v-bind:to="{
              name: 'group-details',
              params: { groupID: this.group.groupId },
              }"
              >
              <div role="button">
              <v-list-item>
              <v-list-item-content class="text-center">
              {{ group.groupName }}
              </v-list-item-content>
              </v-list-item>

              </div>
              </router-link>
              <v-btn v-show="!memberStatus">
              <router-link class="text-decoration-none"
              v-bind:to="{
              name: 'join-group-form',
              params: {
              group: this.group,
              },
              }">
              Join
              </router-link>
              </v-btn>
            </h4>
          </div>
        </div>
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
<style scoped>
v-card > a {
  text-decoration: none !important;

}
router-link a{
    text-decoration: none !important;
}
.wrap {
  width: 25vh;
  height: 40vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(240, 128, 128, 0.05);
}
.card {
  width: 260px;
  min-height: 200px;
  background: #2B2B2B;
  border-radius: 10px;
  padding: 6px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  animation-name: x;
  animation-duration: 60s;
  animation-iteration-count: infinite;
  animation-timing-function: ease;
  transition: all;
}
.card img {
  width: 100%;
}
.info h4{
  color: white;
  padding: 0 10px;
}

@keyframes x {
  0% {transform: rotateY(0deg) skewX(0deg);}
  25% {transform: rotateY(-0deg) skewX(-90deg);}
  50% {transform: rotateY(0deg) skewX(0deg);}
  75% {transform: rotateY(30deg) skewX(4deg);}
  100% {transform: rotateY(0deg) skewX(0deg);}

}

</style>