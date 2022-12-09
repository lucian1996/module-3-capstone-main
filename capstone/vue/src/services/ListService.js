import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// })

export default {

    getLists(groupID) {
        return axios.get(`/groups/${groupID}/lists`)
    },
    getList(groupID, listID)
    {
        return axios.get(`groups/${groupID}/lists/${listID}`)
    }

}