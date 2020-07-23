# REST API Rate Limit Demo

Shows REST API request rate limiting for both Generic REST and Custom REST in CUBA using https://github.com/vladimir-bukhtoyarov/bucket4j library.

If you consider using Generic REST API, do not forget to assign proper privileges to a user - `rest-api-access` role. 

There is a small `HelloService` that returns `Hello %name%!` greeting, where name is a URL parameter. The service is exposed using both Generic REST API in `web` module
and Custom REST API in `portal` module. 

For both modules, there are two rate limiting interceptors (effectively, copied and pasted). They return `X-Rate-Limit-Remaining` header that shows how many requests remain.

Just run the application, assign `rest-api-access` role to a user `admin` and try to test the API using curl, ArC, PostMan or a similar tool.

To test Custom RestAPI, issue the following request:

```
GET http://localhost:8080/app-portal/hello/Billy
content-type: text/plain
authorization: Basic YWRtaW46YWRtaW4=
```

To test the Generic API, get a token first:
```
POST http://localhost:8080/app/rest/v2/oauth/token
content-type: application/x-www-form-urlencoded
authorization: Basic Y2xpZW50OnNlY3JldA==

grant_type=password&username=admin&password=admin
```
You'll get a response that looks like this:
```
{
"access_token": "d478ca70-de52-471d-a6f0-d476333089d8",
"token_type": "bearer",
"refresh_token": "1ab7c16e-fb4c-4b64-a352-76977bc1a082",
"expires_in": 43199,
"scope": "rest-api"
}
```
Use the `access_token` value to get an access to the API:
```
GET http://localhost:8080/app/rest/v2/services/throttler_HelloService/sayHello?name=Bobby
authorization: Bearer d478ca70-de52-471d-a6f0-d476333089d8
```

That's it. Note the responce header `x-rate-limit-remaining: 19` that shows how many requests are left. For the further details, refer to the [Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) library documentation.
