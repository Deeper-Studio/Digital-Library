import request from '../utils/request'

export function login(data) {
    return request({
        baseURL: 'http://localhost:8090/',
        url: 'http://localhost:8090/',
        method: 'post',
        data
    })
}

export function getInfo(token) {
    return request({
        url: '/vue-admin-template/user/info',
        method: 'get',
        params: { token }
    })
}

export function logout() {
    return request({
        url: '/vue-admin-template/user/logout',
        method: 'post'
    })
}
