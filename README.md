# api-test-rest-assured

这里有两个工程，一个是sever，简单的写了几个api
另一个是用RestAssured测试这个API server

使用方法：
server工程是apringboot+swaggerUI的，在Application.java处run as java application即可启动server。
启动后，可以打开http://localhost:8888/swagger/index.html 访问swaggerUI页面，看到所有的API

RestAssured测试工程是只是写了几个junit的测试用例，在ApiTest.java文件。
