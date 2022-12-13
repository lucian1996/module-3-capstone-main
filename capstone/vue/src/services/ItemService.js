import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
// })

export default {

    getItems(groupID, listID) {
        return axios.get(`/groups/${groupID}/lists/${listID}/items`)
    },
    createItem(groupID, listID, item) {
        console.log(groupID, listID, item.name, item.quantity)
        return axios.post(`groups/${groupID}/lists/${listID}/items?itemName=${item.name}&quantity=${item.quantity}`)
    },
    
    editItem(groupID, listID, itemID, item) {
        console.log('edit item, data inside', groupID, listID, item)
        return axios.put(`groups/${groupID}/lists/${listID}/items/${itemID}`, item)
    }
}
//http://localhost:9000/groups/1001/lists/2001/items/3001