<template>
  <div>

    <v-toolbar app>
      <v-toolbar-title id="title">
          {{ appTitle }}
      </v-toolbar-title>
    </v-toolbar> 
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


    <div id="toggleClaim">
      <div v-show="this.list && this.list.claimedId != 0">
        <button
          v-show="this.list && this.$store.state.user.id == this.list.claimedId"
          @click="unclaimList()"
        >
          Unclaim
        </button>
        <button
          v-show="
            this.list &&
            this.$store.state.user.id == this.list.claimedId &&
            this.list.listCompleted == false
          "
          @click="completeList()"
        >
          Mark List complete
        </button>
        <button
          v-show="
            this.list &&
            this.$store.state.user.id == this.list.claimedId &&
            this.list.listCompleted == true
          "
          @click="ListIncomplete()"
        >
          Mark List Incomplete
        </button>
        <div
          v-show="this.list && this.$store.state.user.id != this.list.claimedId"
        >
          <br />
          Claimed by another User
        </div>
      </div>
      <div v-show="this.list && this.list.claimedId == 0">
        <button
          v-show="this.list && this.$store.state.user.id != this.list.claimedId"
          @click="claimList()"
        >
          Claim
        </button>
        <br />
      </div>
    </div>
    <div @click="logLists()">log lists</div>
     <div  data-app id ="container">
     <create-item-form/>
      <item-card
        v-for="item in items"
        v-bind:key="item.dateModified"
        v-bind:itemID="item.itemId"
        :class="{complete : listComplete==true}"
      />
     </div>
  </div>
</template>

<script>
import ItemService from "../services/ItemService";
import ListService from "../services/ListService";
import CreateItemForm from "./CreateItemForm.vue";
import ItemCard from "./ItemCard.vue";
export default {
  components: { ItemCard, CreateItemForm },
  name: "list-details",
  data() {
    return {
      listComplete: "",
      appTitle: 'Fridgrr',
      menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
          { title: 'members', path: '/members'},
             ]
    };
  },
  methods: {
    claimList() {
      ListService.claimList(this.list.groupId, this.list.listId).then(
        (response) => {
          if (response.status == 200)
            this.$store.commit("UPDATE_CLAIMED_ID", {
              listId: this.list.listId,
              userId: this.$store.state.user.id,
            });
            // this.$router.go()
        }
      );
    },
    unclaimList() {
      ListService.unclaimList(this.list.groupId, this.list.listId).then(
        (response) => {
          if (response.status == 200) {
            this.$store.commit("UPDATE_CLAIMED_ID", {
              listId: this.list.listId,
              userId: 0,
            });
            // this.$router.go()
          }
        }
      );
    },
    getItems() {
      ItemService.getItems(
        this.$route.params.groupID,
        this.$route.params.listID
      ).then((response) => {
        this.$store.commit("SET_ITEMS", response.data);
      });
    },
    completeList() {
      
      ListService.markListComplete(
        this.list.groupId,
        this.list.listId,
      ).then((response) => {
        if (response.status == 200) {
          this.$store.commit("MARK_LIST_COMPLETE", {
            groupId: this.list.groupId,
            listId: this.list.listId,
            isComplete: this.isComplete,
          });
        }
      })
      ItemService.markItemsComplete(this.list.groupId, this.list.listId).then(response => {
        if (response.status == 200) {
          this.listComplete = true;
          // this.$router.go()
        }
      })
    },
    ListIncomplete() {
     
      ListService.markListIncomplete(this.list.groupId, this.list.listId).then(
        (response) => {
          if (response.status == 200) {
            this.$store.commit("MARK_LIST_INCOMPLETE", {
              groupId: this.list.groupId,
              listId: this.list.listId,
              isComplete: this.isComplete,
            });
          }
        }
      );
      ItemService.markItemsIncomplete(this.list.groupId, this.list.listId).then(response => {
        if (response.status == 200) {
           this.listComplete = false;
          //  this.$router.go()
        }
      })
    },
   logLists () {
       console.table(this.$store.state.lists);
     }
  },
  created() {
    this.getItems();
  },

  computed: {
    list() {
      return this.$store.state.list.find(
        (l) =>
          l.listId == this.$route.params.listID
      );
    },
    items() {
      console.log('items in state', this.$store.state.items)
      return this.$store.state.items;
    },
  },
};
</script>
<style>
#filter {
  display: flex;
}
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
#container {
  display: grid;
  grid-template-columns: 1fr;
  gap: 20px;
  width: 100%;
  height: 500px;
}
</style>