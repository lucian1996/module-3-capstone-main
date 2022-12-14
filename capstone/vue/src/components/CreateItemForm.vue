<template>
  <div>
    <form v-on:submit.prevent="submit">
    <div class="field">
      <label for="title"></label>
      <textarea v-model="item.name" />
    </div>
    <div class="field">
      <label for="description"></label>
      <input v-model.number="item.quantity"/>
    </div>
    <div class="actions">
      <button type="submit">Create</button>
    </div>
  </form>
  </div>
</template>

<script>
import ItemService from "../services/ItemService";

export default {
  name: "create-item-form",
  data() {
    return {
      item: {
          quantity:0,
          name: ''
      },
    };
  },
  methods: {
    submit() {
      console.table(this.$store.state.list)
      ItemService.createItem(this.list.groupId, this.list.listId, this.item)
      .then(r => {
        console.warn(r)
        if (r == 201) {
          console.log('here')
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
