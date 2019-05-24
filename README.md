# Backend Endpoints
## Base Url
`https://lambda-essentialism-backend.herokuapp.com/api`

# API URL `https://lambda-essentialism-backend.herokuapp.com`
## POST  `/api/register`
expected in body
```js
// ALL FEILDS REQUIRED
{
	"firstname": "foo", 
	"lastname": "bar",
	"email": "foo@bar.com", 
	"username": "foobar",
	"password": "password",
}
```

## LOGIN 
Example axios function getting the oath2 token and making a request

```javascript
const axios = require('axios');

const API = 'https://lambda-essentialism-backend.herokuapp.com';

const reqData = {
  username: 'admin',
  password: 'password',
  grant_type: 'password'
};

const queryString = Object.keys(reqData)
  .map(key => key + '=' + reqData[key])
  .join('&');

const headers = {
  url: `${API}/oauth/token`,
  method: 'post',
  withCredentials: true,
  auth: { username: 'lambda-client', password: 'lambda-secret' },
  data: queryString
};

axios
  .request(headers)
  .then(res => res.data.access_token)
  .then(token =>
    axios.get(`${API}/api/thisuser`, {
      headers: {
        Authorization: 'Bearer ' + token
      }
    })
  )
  .then(res => console.log(res.data))
  .catch(err => console.log(err));
```


## GET  `/api/values`
sample response
```js
[
  {
    "valueid": 1,
    "title": "Athletic ability",
  },
  {
    "valueid": 2,
    "title": "Creativity, discovering, or inventing things to make a difference in the world",
  },
  {
    "valueid": 3,
    "title": "Independence",
  },
  {
    "valueid": 4,
    "title": "Kindness and generosity",
  }
// ......
]
```


## POST  `/api/value/{valueid}`
Post to this end point with and the active user will have this value added to his userValues
sample response
```js
{
  "valueid": 2,
  "title": "Creativity, discovering, or inventing things to make a difference in the world"
}
```


## DELETE	`/api/value{valueid}`
Send a delete request to this endpoint and it will delete that value for the current user signed in;

## GET `/api/projects`
Must be logged in to make request
Example response
```js
[
  {
    "projectid": 17,
    "title": "The enemy of my enemy is the enemy I kill last"
  },
  {
    "projectid": 18,
    "title": "Beam me up"
  },
// ...
]
```

## POST `/api/projects`
Must be logged in to make request
```js
// Expected in body
{
	"title": "Get SH*T done"
}
```

## DELETE `/api/projects/{projectId}`
Post request has to be from a user that is logged in and is the project owner

