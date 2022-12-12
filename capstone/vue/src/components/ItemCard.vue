<template>
       <div class="card">
        <div class="container">
            <div>{{item.itemId}}</div>
            <button>edit</button>
        </div>
        </div>
</template>

<script>

export default {
  name: 'item-card',
  props: ['item'],
}
</script>