<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
    </head>

    <body>

        <h1>Registration page</h1>

        <form method="post" action="/registration/validate-email">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <label><input type="text" name="name" required> Name</label><br>
            <#if regexErrorMessage??>
                <h3>${regexErrorMessage}</h3>
            </#if>
            <#if alreadyExistErrorMessage??>
                <h3>${alreadyExistErrorMessage}</h3>
            </#if>
            <label><input type="text" name="email" required> Email</label><br>
            <label><input type="text" name="phone" required> Phone number</label><br>
            <label><input type="password" name="password" required> Password</label><br>
            <input type="submit">
        </form>

        <div>
            <a href="/login"><button type="button">Login</button></a>
        </div>
    </body>
</html>