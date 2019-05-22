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
