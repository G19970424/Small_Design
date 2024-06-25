import axios from 'axios'
let Ajax= axios.create({
    baseURL: "http://127.0.0.1:7095/design",
    timeout:5000
})
const get=(url,params)=> {
    //Ajax.defaults.headers['token'] = localStorage.getItem('token')
    return new Promise((relove, reject) => {
        Ajax.get(url, {params}).then(res => {
            relove(res.data);
        }).catch(err => reject(err))
    })
}
const post=(url,params)=> {
    //Ajax.defaults.headers['token'] = localStorage.getItem('token')
    return new Promise((relove, reject) => {
        Ajax.post(url, params).then(res => {
            relove(res.data);
        }).catch(err => reject(err))
    })
}
export default{
    get, post
}