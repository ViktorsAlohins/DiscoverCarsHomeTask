# DiscoverCarsHomeTask
#Web app in Docker, HTTP routes return Hello World in 3 formats, with customizable colors. Includes authentication, reverse proxy, and DB.

#Application is located on port 3000, or through the proxy on port 8080, that requires the authentication on proxy level, with "demo" as login and password.
#nginx is used to setup proxy server.
#No any other dependencies other than Docker required on tester's computer, everything needed is inside the container. 

#http://localhost:8080:/api/environment - sends GET request that returns "Hello World" in HTML format (default format)
#http://localhost:8080/api/environment?format=html&bgColor=%23"COLORCODE"&fgColor=%23"COLORCODE - returns html GET request with changable background and foreground colors, you can specify the color in HEX format, it's case insentive.
#http://localhost:8080/api/environment?format=json - sends GET request that returns "Hello World" in json format
#http://localhost:8080/api/environment?format=xml - sends GET request that returns "Hello World" in json xml

#http://localhost:8080/api/headers - sends GET request that returns the key-value pairs in HTML format (default format)
#http://localhost:8080/api/headers?format=json - sends GET request that returns the key-value pairs in JSON format
#http://localhost:8080/api/headers?format=xml - sends GET request that returns the key-value pairs in XML format

#http://localhost:8080/api/post - sends POST request that accept only HTTP POST requests, if any other format or request is sent returns an error. 
