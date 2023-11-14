# EDU-EXCHANGE PROJECT

---
## Start project
1. Clone repo
2. cd front-side/
3. npm install
4. cd ..
5. docker-compose build
6. docker-compose up -d
---
## Links

1. front : https://edu-exchange.netlify.app/
2. back : https://eduexchange.onrender.com/api/v1/swagger-ui/index.html
3. docker : https://hub.docker.com/layers/joyau/sourcemind/v1.2/images/sha256-d941b60b6ff34e8961f4e8d629cec1b3dd9148373f2818d2f0d9e4ecaf78d166?tab=layers

## How to run locally

**1. You should consider comments in application.properties of back-end folder to make app work locally : use your own database with your credentials**

**2. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:9898>

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /v1/api/auth/register | Sign up | [JSON](#register) |
| POST   | /v1/api/auth/login | Log in | [JSON](#login) |
|GET     | /v1/api/userinfo     | get a user infor

### Posts

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /v1/api/posts | Get all posts | |
| GET    | /v1/api/posts/{id} | Get post by id | |
| GET    | /v1/api/user/{userPseudo}/posts | Get post that belongs to user identified by pseudo | |
| POST   | /v1/api/posts | Create new post  | [JSON](#postcreate) |
| PUT    | /v1/api/posts | Update post () | [JSON](#postupdate) |

### Comments

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /v1/api/posts/{id}/comments | Get all comments of post with provided id | |
| GET    | /v1/api/comments/{id}/replies | Get all replies to comment with given id | |
| POST   | /v1/api/comments | Create new comment | [JSON](#commentcreate) |
| PUT    | /v1/api/comments | update existing comment | [JSON](#commentupdate) |


### Topics

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /v1/api/topics | Get all topics | |
| GET    | /v1/api/topics/{topicName} | Get all topics with topic topicName | |

Test them using postman or any other rest client.

## Sample Valid JSON Request Bodys

##### <a id="register">Register -> /v1/api/auth/register</a>
```json
{
	"fullname": "Leanne",
	"pseudo": "string",
    "role": "STUDENT",
	"password": "password",
	"email": "leanne.graham@gmail.com",
    "speciality":null
}
```

##### <a id="login">Log In -> /v1/api/auth/signin</a>
```json
{
	"username": "leanne",
	"password": "password"
}
```

#### <a id="commentcreate">Create comment -> /v1/api/comments</a>
```json
{
	"content": "comment content",
	"imagePaths" : ["/path/to/image1.jpg"],
	"authorId":1,
	"postId":"2",
	"parentId":null
}
```

#### <a id="commentupdate"> Update comment /v1/api/comments</a>
```json

{
	"content": "new comment content",
	"imagePaths" : ["/path/to/image1.jpg"],
	"authorId":1, //there are three user available
	"postId":"2", // required
	"parentId":null,
    "id":1 //
}
```

#### <a id="postcreate">Create post -> /v1/api/posts</a>
```json
{
	"content":"my new content",
	"imagePaths": [
        "/path/to/image1.jpg"{
	"content":"operation would lead to a conflict (for example, when trying to update a resource with a version that is older than the current version on the server). Remember, these are just general guidelines and the actual status code you should return depends on the specifics of your API and the result of the update operation.",
	"imagePaths": [
        "/path/to/image1.jpg",
        "/path/to/image2.jpg"
    ],
    "userId": 1,
    "topicNames": ["Topic1", "Topic2", "Topic3"]
},
        "/path/to/image2.jpg"
    ],
    "userId": 1,
    "topicNames": ["Topic8", "Topic2", "Topic3"]
}
```

#### <a id="postupdate">Update post/v1/api/post</a>
```json

{
	"content":"atus code, such as: 400 (Bad Request) if the client sent a request that the server cannot understand or process. 404 (Not Found) if the resource that the client is trying to update does not exist. 409 (Conflict) if the update ",
	"imagePaths": [
        "/path/to/image1.jpg",
        "/path/to/image2.jpg"
    ],
    "userId": 1,
    "topicNames": ["Topic9", "Topic2", "Topic3"],
	"id":1 // new field and required for update
}