<template>
   <div class="card">
  <div class="container">
    <div>{{list}}</div>
    <div> </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'list-card',
  props: ['list'],
}
</script>