<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  name: 'logout',
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/");
  },
};
</script>
