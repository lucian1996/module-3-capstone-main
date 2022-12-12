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
          return axios.get(`/groups/${groupID}`)
      },
      //TODO need something along the lines of
      //button @click="removeUser({{$store.state.user.userID}}, {{$store.state.group.groupID}})">
      //in GroupDetails
      removeUser(groupID) {
        console.log("removeUser", groupID)
        return axios.delete(`/groups/${groupID}/members`)
    },
    addUser(groupID, groupCode) {
        console.log("addUser", groupID, groupCode)
        return axios.post(`/groups/${groupID}/members?confirmCode=${groupCode}&groupCode=${groupCode}`)
    },
    getMemberByUsername(groupID, username) {
        console.log("getAllMembers", groupID, username)
        return axios.get(`/groups/${groupID}/members/${username}`)
    }
}