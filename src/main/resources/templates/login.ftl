<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
<#--        <link rel="stylesheet" href="../css/login.css">-->
    </head>
    <body>
        <h1>Login page</h1>
        <h3>${errorMessage!}</h3>

        <form method="post" action="/login">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <label><input name="email" type="text" value="${previousLogin!}"> Email</label><br>
            <label><input name="password" type="password"> Password</label><br>
            <input type="submit">
        </form>

        <div>
            <a href="/registration"><button type="button">New User</button></a>
        </div>
    </body>
</html>