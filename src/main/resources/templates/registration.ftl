<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="../css/registration.css">
    </head>

    <body>

        <h1>Registration page</h1>

        <form method="post" action="/registration">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <label><input type="text" name="name" required> Name</label><br>
            <label><input type="text" name="email" required> Email</label><br>
            <label><input type="text" name="phone" required> Phone number</label><br>

<#--            <label><select name="groupId" required>-->
<#--                    <#list groups as group>-->
<#--                        <option value="${group.getId()}">${group.getId()}</option>-->
<#--                        <#else>-->
<#--                            <option>There are no groups in the database</option>-->
<#--                    </#list>-->
<#--                </select> Group id</label><br>-->

            <label><input type="password" name="password" required> Password</label><br>
<#--            <label><select name="role" required>-->
<#--                    <option value="STUDENT">Student</option>-->
<#--                    <option value="TEACHER">Teacher</option>-->
<#--                </select> Role</label><br>-->
            <input type="submit">
        </form>

        <div>
            <a href="/login"><button type="button">Login</button></a>
        </div>
    </body>
</html>