window.addEventListener('load', () => {
    const form = document.getElementById('login-form')

    form.addEventListener('submit', event => {
        event.preventDefault()

        let data = event.target
        let formData = new FormData(data)

        let user = Object.fromEntries(formData)

        saveUser(user)

        // makeRequest('/images/byName?id=1', updateOptions({method: 'post'}))

    })
})

const baseUrl = 'http://localhost:8089'

function saveUser(user) {
    let userAsJson = JSON.stringify(user)
    localStorage.setItem('user', userAsJson)
}

function restoreUser() {
    let userAsJson = localStorage.getItem('user')
    return JSON.parse(userAsJson)
}

function makeHeaders() {
    const user = restoreUser()
    const headers = new Headers()
    headers.append('Content-Type', 'application/json')

    if (user) headers.append('Authorization', 'Basic ' + btoa(user.username + ':' + user.password))

    return headers
}

const requestSettings = {
    method: 'get',
    headers: makeHeaders()
}

async function makeRequest(url, options) {
    let settings = options || requestSettings
    let response = fetch(url, settings)

    if (response.ok) return await response.json()
    else {
        let error = new Error((await response).statusText)
        error.response = response
        throw error
    }
}

function updateOptions(options) {
    let update = {...options}
    update.mode = 'cors'
    update.headers = makeHeaders()
    return update
}