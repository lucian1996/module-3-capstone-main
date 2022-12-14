<template>
  <v-dialog @click="dialog = true"
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
    <v-card>
      <v-form v-on:submit.prevent>
        <div class="field">
          <label for="title"></label>
          <v-text-field v-model="item.name" />
        </div>
        <div class="field">
          <label for="description"></label>
          <v-text-field v-model.number="item.quantity"/>
        </div>
        <v-row justify="center">
                 <v-btn @click="submit()" color="primary" elevation="2">Create</v-btn>
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
          quantity:0,
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
          console.log('here')
        this.$store.commit("ADD_ITEM", r.data)
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
