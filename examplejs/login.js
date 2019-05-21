const axios = require('axios');
const oauth = require('axios-oauth-client');
const getOwnerCredentials = oauth.client(axios.create(), {
  url: 'https://lambda-essentialism-backend.herokuapp.com/oauth/token',
  client_id: 'lambda-client',
  client_secret: 'lambda-secret',
  username: 'testuser',
  password: 'password'
});

// => { "access_token": "...", "expires_in": 900, ... }

async function login() {
  const auth = await getOwnerCredentials();
  console.log(await auth);
}
login();
