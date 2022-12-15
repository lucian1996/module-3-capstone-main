<template>
    
  <v-dialog @click="dialog = true"
  v-model="dialog"
        width="600px"
        >
     <template v-slot:activator="{ on, attrs }">
          <v-btn class="d-flex align-center justify-center"  min-height="250"
            v-bind="attrs"
            v-on="on"
          >
          + create item
          </v-btn>
        </template>
    <v-card class="form-card">
      <v-form v-on:submit.prevent>
        <div class="field">
          <label for="name">Name</label>
          <v-text-field placeholder="name" v-model="item.name" />
        </div>
        <div class="field">
          <label for="quantity">Quantity</label>
          <v-text-field placeholder="quantity" v-model.number="item.quantity"/>
        </div>
        <v-row justify="center">
                 <v-btn @click="submit()" color="rgb(255, 235, 205)" elevation="2">Create</v-btn>
              </v-row>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import ItemService from "../services/ItemService";

export default {
  name: "create-item-form",
  data() {
    return {
      dialog: false,
      item: {
          quantity:'',
          name: ''
      },
    };
  },
  methods: {
    submit() {
      this.dialog = false;
      console.table(this.$store.state.list)
      ItemService.createItem(this.list.groupId, this.list.listId, this.item)
      .then(r => {
        console.warn(r)
        if (r == 201) {
          

        this.$store.commit("ADD_ITEM", r.data)
        this.$router.go()
       }})
    },
  },
   computed: {

    list() {
      return this.$store.state.list.find(l => 
        l.listId == this.$route.params.listID
    )
    }
    },
};
</script>
<style scoped>
.form-card{
padding: 5%;
}
</style>
