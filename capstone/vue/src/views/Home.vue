<template>
  <div class="home">
    <h1>Home</h1>
    <create-group/>
    <group-list/>
  </div>
</template>

<script>
import GroupList from '../components/GroupList.vue';
import CreateGroup from '../components/CreateGroup.vue'
export default {
  components: { GroupList,  CreateGroup},
  name: "home"
};
</script>
