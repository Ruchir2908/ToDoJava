<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
		<form action="<%=request.getContextPath()%>/login" method="post">

                    <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>

                    <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>

                	<button type="submit">Submit</button>
            </form>
</body>
</html>