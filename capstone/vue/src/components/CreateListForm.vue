<template>
  <v-dialog
    @click="dialog = true"
        v-model="dialog"
        width="600px"
  >
  <template v-slot:activator="{ on, attrs }">
          <v-card class="d-flex align-center justify-center"  min-height="250"
            v-bind="attrs"
            v-on="on"
          >
          +
          </v-card>
        </template>
    
    <v-card class="form-card">
      <v-label>
        Create List
      </v-label>
      <form v-on:submit.prevent>
      <!-- <label for="title"></label> -->
      <v-text-field v-model="list.name" 
      placeholder="enter the name"
      />
      <label for="description"></label>
      <v-textarea v-model="list.description"
      placeholder="enter the description">
      </v-textarea>
      <v-btn @click="submit()" 
      color="rgb(255, 235, 205)" 
      elevation="15"
      >Create</v-btn>
    </form>
    </v-card>
  </v-dialog>
</template>

<script>
import ListService from "../services/ListService";

export default {
  name: "create-list-form",
  
  data() {
    return {
      dialog: false,
      list: {
          name:'',
          description: ''
      },
      listErrors: false,
      listErrorMsg: "There was a problem creating this list.",
    };
  },
  methods: {
    submit() {
      this.dialog = false;
      this.$router.go();
      if (this.list.name.length <= 0) {
        this.listErrors = true;
        this.listErrorMsg = "List name cannot be blank."
      } else {ListService.createList(this.$store.state.group.groupId, this.list)
     .then (response => {
              this.$store.commit("ADD_LIST", response.data);
      })
        //TODO: this can't be empty, else the user will never be able to navigate there
        //const data = response.dat
    }
    }
  },
};
</script>
<style scoped>

.form-card{
  padding: 5%;
}

</style>
