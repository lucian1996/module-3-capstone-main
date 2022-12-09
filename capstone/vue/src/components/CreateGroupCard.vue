<template>
   <div class="card">
  <div class="container">
      <router-link :to="{ name: 'createGroup' }"> + Create Group</router-link> 
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
.container {
  font-weight: b;
}
.card:hover {
  box-shadow: 0px 1px 2px 3px #f09374c4;
  text-emphasis: bolder;
}
</style>