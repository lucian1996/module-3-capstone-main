<template>
  <div class="wrap">
    <div class=" card incomplete" v-bind:class="[{ complete: isComplete }]">
      <img src="@/assets/lord.png" alt="not working">
      <div v-bind:class="[{ complete: isComplete }]" class="info" >
        {{item.quantity}}
        <br>
      {{item.itemName}}
      </div>
     <div class="no">
        <div @click="markComplete">
      <div v-show="!isComplete">Mark Complete
      </div>
    <div role=button v-show="isComplete">Mark Incomplete
    </div>
    <edit-item-form  v-bind:itemID="itemID"/>
      </div>
    </div>
     </div>
  </div>
</template>

<script>
import ItemService from '../services/ItemService'
import EditItemForm from './EditItemForm.vue';

export default {
  components: {EditItemForm},
  name: 'item-card',
  props : ["itemID"],
  data () {
    return {
      isComplete: false
    }
  },

  methods : {
    markComplete () { 
      this.item.status = !this.item.status;
      ItemService.editItem(this.item)
      this.$router.go()
      },
    editItem() {
      this.item.quantity = this.quantity;
      this.item.itemName = this.name;
      console.table(this.item)
      ItemService.editItem(this.item.groupId, this.item.listId, this.item.itemId,this.item)

    },
     removeItem() {
      ItemService.removeItem(this.item.groupId, this.item.listId, this.item.itemId).then(response => {
        if(response.status == 200) {
          this.$store.commit('DELETE_ITEM', this.item.itemId)
          this.$router.push('list-details')
        }
      })
    }
    },
    created () {
      this.isComplete = this.item.status;
    },
    computed : {
      item () {
        return this.$store.state.items.find (i => 
          i.itemId == this.itemID
        )
      },
      
    }
  
}
</script>
<style scoped>
.complete {
  text-decoration: line-through;
  text-decoration-color: rgb(0, 0, 0);
  text-decoration-thickness: 4px;
  color: blanchedalmond;
  

}
.no {
  text-decoration: none !important;
}
.incomplete {
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
  background: #0ead69;
  box-shadow:0px 2px 1px 2px blanchedalmond;
  border-radius: 4px;
  padding: 8px;
  font-family:    'Courier New', Courier, monospace;
  font-size: 16pt;
  animation-name: x;
  animation-duration: 50s;
  animation-iteration-count: infinite;
  animation-timing-function: ease;
  transition: all;
}
#group-name {
  color: blanchedalmond;
  background: #0ead69;
}
#join-button {
  color: blanchedalmond;
  background-color: rgb(240, 128, 128);
}
.card img {
  width: 100%;
}
.info {
  color: blanchedalmond;
  padding: 0 10px;
  font-family: 'Courier New', Courier, monospace;
  font-weight: 1000;
}

@keyframes x {
  0% {transform: rotateY(0deg) skewX(0deg);}
  25% {transform: rotateY(-0deg) skewX(-90deg);}
  50% {transform: rotateY(0deg) skewX(0deg);}
  75% {transform: rotateY(30deg) skewX(4deg);}
  100% {transform: rotateY(0deg) skewX(0deg);}
}

</style>