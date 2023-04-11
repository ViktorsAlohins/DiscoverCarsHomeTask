# Web app in Docker

This web app returns "Hello World" in 3 formats, with customizable colors. It includes authentication, a reverse proxy, and a database. No other dependencies are required on the tester's computer other than Docker.

## Features

- Application available on port 3000 or through the proxy on port 8080
- Authentication on the proxy level with "demo" as login and password
- Nginx is used to set up the proxy server

## Endpoints

### Environment

- `GET http://localhost:8080/api/environment`: Returns "Hello World" in HTML format (default)
- `GET http://localhost:8080/api/environment?format=html&bgColor=%23"COLORCODE"&fgColor=%23"COLORCODE`: Returns "Hello World" in HTML format with customizable background and foreground colors (HEX format, case insensitive)
- `GET http://localhost:8080/api/environment?format=json`: Returns "Hello World" in JSON format
- `GET http://localhost:8080/api/environment?format=xml`: Returns "Hello World" in XML format

### Headers

- `GET http://localhost:8080/api/headers`: Returns key-value pairs of headers in HTML format (default)
- `GET http://localhost:8080/api/headers?format=json`: Returns key-value pairs of headers in JSON format
- `GET http://localhost:8080/api/headers?format=xml`: Returns key-value pairs of headers in XML format

### Post

- `POST http://localhost:8080/api/post`: Accepts only HTTP POST requests; returns an error for other request methods
