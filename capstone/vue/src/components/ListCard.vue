<template>
 <v-card-title class="list-card text-center">
    <!-- <router-link
    v-bind:to="{ name: 'list-details', params: { listID: list.listId, groupID: list.groupId } }"> 
  </router-link> -->
  <v-btn @click="gotolistDetails()">{{ list.listName }}</v-btn>
  
 </v-card-title>
</template>

<script>
export default {
  name: "list-card",
  props: ["list"],
  data() {
    return {
    images: ['1.png', '2.png', '3.png', '4.png', '5.png', '6.png', '7.png'],
    resultImage: ''
  }
  },
  created() {

  },
  methods: {
    gotolistDetails() {
    this.$router.push({ name: 'list-details', params: { listID: this.list.listID, groupID: this.list.groupID } })
  }},
  randomImage() {
      const random = Math.floor(Math.random() * this.images.length)
      this.resultImage = this.images[random];
  }
}
</script>
<style scoped>
.list-card{
  background-color: blanchedalmond;
  text-decoration-line: none;
  height: 150px;
  width: 200px;
  border-radius: 4px;
}
</style>
