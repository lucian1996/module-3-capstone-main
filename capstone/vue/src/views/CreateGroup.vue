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
  background-color: #3a2e34;
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
  

}

textarea {
  background: #51434a;
  border: .5px solid #51434a;
  text-align: center; 
  margin-top: 3px;
  max-width: 10em;
  border-radius: 10%;
}
button {
  background: #5d7065;
  border: .5px solid #5d7065;
  /* border: .5px solid rgba(255, 235, 205, 0.534)  */
  border-radius: 10%;
  max-width: 10em;
}
</style>
