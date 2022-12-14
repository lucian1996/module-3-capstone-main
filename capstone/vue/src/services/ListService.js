import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// })

export default {

    getLists(groupID) {
        console.log(axios.get(`/groups/${groupID}/lists`), 'axios call')
        return axios.get(`/groups/${groupID}/lists`)
    },
    createList(groupID, list) {
        console.log('groupID', list)
        //    return axios.post(`/groups/${groupID}/members?groupCode=${groupCode}?confirmCode=${confirmCode}`)
       return axios.post(`groups/${groupID}/lists?listDescription=${list.description}&listName=${list.name}`);
      //return axios.post(`/groups/1001/lists?listDescription=test%20description&listName=coolname`);
     //   lists?listDescription=test%20description&listName=coolname
    },
    getList(groupID, listID) {
        return axios.get(`/groups/${groupID}/lists/${listID}`)
    },
    claimList(groupID, listID) {
        return axios.put(`groups/${groupID}/lists/${listID}/claim`)
    },
    unclaimList(groupID, listID) {
        return axios.put(`groups/${groupID}/lists/${listID}/unclaim`)
    },
    markListComplete(groupID, listID) {
        return axios.put(`groups/${groupID}/lists/${listID}/isComplete`)
    }
}
