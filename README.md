# jwtcodebase

Spring Security- JWT

What is JWT?
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained 
way for securely transmitting information between parties as a JSON object. 
A JWT is cryptographically signed so there is a guarantee we can trust it when we receive it, 
as no middleman can intercept and modify it, or the data it holds, without invalidating it.

JWT, or JSON Web Tokens (RFC 7519), is a standard that is mostly used for securing REST APIs. 

JWT is the best way to communicate securely between client and server.

JWT follows a stateless authentication mechanism - we can implement JWT authentication entirely 
stateless without storing session information on the server side.



During the first request the client sends a POST request with username and password. 
Upon successful authentication the server generates the JWT sends this JWT to the client. 
This JWT can contain a payload of data. On all subsequent requests the client sends this JWT 
token in the header. Using this token the server authenticates the user. So we don't need the 
client to send the user name and password to the server during each request for authentication, 
but only once after which the server issues a JWT to the client. A JWT payload can contain things 
like user ID so that when the client again sends the JWT, you can be sure that it is issued by you, 
and you can see to whom it was issued.


