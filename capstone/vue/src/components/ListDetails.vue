<template>
  <html>

    <header>
          <v-toolbar app>
      <v-toolbar-title id="title">
          \ {{ appTitle }}  \ {{this.$store.state.user.username}} \ {{this.$store.state.group.groupName}} \ {{listName}}
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
    </header>

    <!-- <div id="toggleClaim">
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
    </div> -->
    <body>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
       <div data-app data=app class="wrap">
      <div>
         <create-item-form/>
      </div>
      </div>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      
       <div id="container">
          <item-card
        v-for="item in items"
        v-bind:key="item.dateModified"
        v-bind:itemID="item.itemId"
        :class="{complete : listComplete==true}"
      />
     </div>
    </body>
     </html>
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
      listName: '',
      listComplete: "",
      appTitle: 'Fridgrr',
      menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'members', path: '/members'},
            { title: 'logout', path: '/' },
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
    this.listName = this.list.listName;
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
  text-shadow: 1.5px 1.5px 0px rgba(240, 128, 128, 0.349);
}
.v-btn__content{
  font-family:    'Courier New', Courier, monospace;
  font-size:      15px;
  font-weight:    bolder;
}
.v-toolbar__content{
  background-color: #0EAD69;
}

.theme--light.v-btn.v-btn--has-bg {
    background-color: #0EAD69;
}

#container {
  display: grid;
  grid-template-columns: repeat(4,1fr) 60px;
  grid-gap: 3%;
  box-sizing: border-box;
  gap: 50px;
  width: 100%;
  height: 50px;
}
.wrap {
  width: 30vw !important;
  height: 25vh !important;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(240, 128, 128, 0.03) !important;
}

</style>