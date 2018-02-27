<html>

<head>
<title>First Web Application</title>
</head>

<body>
	Welcome to the login page
	<br>
	<br>
    <font color="red">${errorMessage}</font>
    <form action="/logincheck" method="post">
        Name : <input type="text" name="name" />
        Password : <input type="password" name="password" /> 
        <input type="submit" />
    </form>
</body>

</html>