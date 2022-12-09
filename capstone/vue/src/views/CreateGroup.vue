<template>
  <div>
    <create-group/>
  </div>
</template>

<script>
import CreateGroup from '../components/CreateGroup.vue'
export default {
  components: { CreateGroup },
  name: "home"
};
</script>

<style>
  form {
  background-color: #6b6a66;
  display:flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: flix-start;
  flex-basis: 200px;

  text-align: center;
  line-height: 200%;
  padding: 2em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
  
  border: 5px solid #b7ec8c80;
  border-top-color: rgba(0, 255, 255, 0.493);
  border-bottom-color: rgba(127, 255, 212, 0.514);
}

textarea {
  background: rgba(127, 255, 212, 0.514);
  border: .5px solid rgba(255, 235, 205, 0.534);
  text-align: center; 
  margin-top: 3px;
  max-width: 15em;
  border-radius: 10%;
}
button {
  background: rgba(0, 255, 255, 0.493);
  border: .5px solid rgba(0, 255, 255, 0.493);
  /* border: .5px solid rgba(255, 235, 205, 0.534)  */
  border-radius: 10%;
  max-width: 7em;
}
</style>
