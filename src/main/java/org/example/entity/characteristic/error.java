/*
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Error page</title>
    <meta charset="utf-8" />
</head>
<body th:with="httpStatus=${T(org.springframework.http.HttpStatus).valueOf(#response.status)}">
<h1 th:text="|${httpStatus} - ${httpStatus.reasonPhrase}|">404</h1>
<p th:utext="${errorMessage}">Error java.lang.NullPointerException</p>
<a href="login" th:href="@{/login}">Back to Home Page</a>
</body>
</html

 */