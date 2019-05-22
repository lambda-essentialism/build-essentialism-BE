# Backend Endpoints

### API URL `https://lambda-essentialism-backend.herokuapp.com`

---

## POST  `/api/register`
expected in body
```js
// ALL FIELDS REQUIRED
{
	"firstname": "foo", 
	"lastname": "bar",
	"email": "foo@bar.com", 
	"username": "foobar",
	"password": "password",
}
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


## GET  `/api/value/{valueid}`
sample response
```js
{
  "valueid": 2,
  "title": "Creativity, discovering, or inventing things to make a difference in the world"
}
```

