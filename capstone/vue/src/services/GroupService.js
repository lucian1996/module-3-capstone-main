import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
})

export default {

    getGroups() {
        return http.get('/')
    },

    createGroup(group) {
        return http.post('/', group)
      },
}