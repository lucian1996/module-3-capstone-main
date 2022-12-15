<template>
  <div>
    <div id="nav">

       <v-toolbar app>
      <v-toolbar-title id="title">
          {{ appTitle }}  \ 
      </v-toolbar-title>

      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          flat
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    </div>

  </div>
</template>

<script>

export default {

    name: 'group-members',
    data() {
    return {
    appTitle: 'Fridgrr',
        menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
          { title: 'members', path: '/members',},
     ],

}
    }
}
</script>

<style>
#title{
  font-family:    'Courier New', Courier, monospace;
  font-size:      50px;
  font-weight:    bold;
  color:          whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content{
  font-family:    'Courier New', Courier, monospace;
  font-size:      15px;
  font-weight:    bolder;
}
.v-toolbar__content{
  background-color: #0EAD69;
}
</style>