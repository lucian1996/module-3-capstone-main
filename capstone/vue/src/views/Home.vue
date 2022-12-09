<template>
<div>
  <div>
    <group-list />
  </div>
</div>
</template>

<script>
import GroupList from "../components/GroupList.vue";
export default {
  components: { GroupList },
  name: "home",
};
</script>

<style>
form {
  background-color: #6b6a66;
  display: flex;
  text-align: center;
  line-height: 200%;
  padding: 2em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
}
#createGroup {
  min-width: em;
  max-width: 3em;
}

  /* border: 5px solid #b7ec8c80;
  border-top-color: rgba(0, 255, 255, 0.493);
  border-bottom-color: rgba(127, 255, 212, 0.514); */
</style>
