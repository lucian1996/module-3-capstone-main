<template>
       <div class="card">
        <div class="container">
            <div>{{item.itemName}}</div>
            <div> </div>
        </div>
        </div>
</template>

<script>

export default {
  name: 'item-card',
  props: ['item'],
}
</script>