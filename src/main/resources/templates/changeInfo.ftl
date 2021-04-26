<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Change ${currentUser.getUserName()} Info</title>
    </head>
    <body>
        <h2>Here you can change information about you</h2>
        <h2>${errorMessage!}</h2>
        <form method="post" action="/change-info">
            <input type="hidden" name="id" value="${currentUser.getId()}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <label><input type="text" name="name" value="${currentUser.getUserName()}"> Name</label><br>
            <label><input type="text" name="email" value="${currentUser.getEmail()}"> Email</label><br>
            <label><input type="text" name="phone" value="${currentUser.getPhoneNumber()}"> Phone number</label><br>
            <label><input type="text" name="groupId" value="${currentUser.getGroup().getId()}"> Group id</label><br>
            <label><select name="role">
                    <option value="STUDENT">Student</option>
                    <option value="TEACHER">Teacher</option>
                </select> Role</label><br>
            <label><input type="password" name="password"> Current Password</label><br>
            <label><input type="password" name="npassword"> New Password</label><br>
            <input type="submit">
        </form>
    </body>
</html>