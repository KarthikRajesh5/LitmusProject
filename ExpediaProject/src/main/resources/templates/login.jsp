<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        form
        {
            width: 300px;
            margin: 0 auto;

        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        h1 {
            text-align: center;
            margin: 24px 0 12px 0;
            position: relative;
        }
        input[type=submit]{
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }
        .container {
            padding: 8px;
        }

    </style>
</head>
<body>
<h1 >Login</h1>

${SPRING_SECURITY_LAST_EXCEPTION.message}
<div class="container" style="background-color:#f1f1f1">
    <form action="login" method='POST'>

        <table>
            <tr>
                <td><b>Username:</b></td>
                <td><input type='text' name='username' value=''></td></br>
            </tr>
            <tr>
                <td><b>Password:</b></td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="submit" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>