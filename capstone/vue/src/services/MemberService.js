   import axios from 'axios';


// const http = axios.create({
//     baseURL: "http://localhost:9000"
// })

export default {
   getMembers(groupID) {
        console.log(axios.get(`/groups/${groupID}/members`), 'axios call')
        return axios.get(`/groups/${groupID}/members`)
    }
   }