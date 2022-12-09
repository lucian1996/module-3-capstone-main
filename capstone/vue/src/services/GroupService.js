import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// })

export default {

    getGroups() {
        return axios.get('/groups')
    },

    createGroup(group) {
        return axios.post('/groups', group)
      },
      getGroup(groupID) {
          console.log('group service ', groupID)
          return axios.get(`/groups/${groupID}`)
      }
}