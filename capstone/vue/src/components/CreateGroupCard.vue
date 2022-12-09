<template>
   <div class="card">
  <div class="container">
      <router-link :to="{ name: 'createGroup' }"> Create Group</router-link>
    </div>
</div>
</template>

<script>
export default {
  name: 'create-group-card',
}
</script>


<style>
  .card {
  background-color: #51434a;
  display: flex;
  text-align: center;
  border-radius: 12%;
  padding: 1em;
  padding-top: 0em;
  padding-bottom: 0em;
  margin: .5em;
  margin-top: .25em;
  margin-bottom: .25em;
}
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
</style>