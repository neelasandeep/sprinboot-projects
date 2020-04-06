<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome to sandeep
<form action="addalien" method="post">
Enter Aid:<input type="text" name="aid"><br>
Enter Aname:<input type="text" name="aname"><br>
<input type="submit" value="submit">

<br>
getAliens
<a href="getAliens">mvc form </a>

</form>
<hr>
<form action="getAlien" method="post">
Enter Aid:<input type="text" name="aid"><br>

<input type="submit" value="submit">



</form>
<hr>
<form action="removeAlien" method="post">
Enter Aid:<input type="text" name="aid"><br>

<input type="submit" value="submit">



</form>
<hr>
Alien By Name
<form action="byNameOfAlien" method="post">
Enter Aid:<input type="text" name="aname"><br>

<input type="submit" value="submit">
<br>
<a href="logout">logout</a>



</form>
</body>
</html>