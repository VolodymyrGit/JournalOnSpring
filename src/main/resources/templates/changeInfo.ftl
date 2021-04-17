<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Change ${currentUser.getUserName()} Info</title>
    </head>

    <body>

        <h2>Change Info Cabinet</h2>

        <form method="post" action="/change-info">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="${currentUser.getId()}">
            <label><input type="text" name="name" value="${currentUser.getUserName()}"></label>
            <input type="submit" value="Confirm Name">
        </form>

        <h2>${emailErrorMessage!}</h2>

        <form method="post" action="/change-info">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="${currentUser.getId()}">
            <label><input type="text" name="email" value="${currentUser.getEmail()}" required></label>
            <input type="submit" value="Confirm Email">
        </form>

        <form method="post" action="/change-info">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="${currentUser.getId()}">
            <label><input type="text" name="phone" value="${currentUser.getPhoneNumber()}"></label>
            <input type="submit" value="Confirm Phone">
        </form>

        <h2>${passwordErrorMessage!}</h2>

        <form method="post" action="/change-info">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="id" value="${currentUser.getId()}">
            <input type="password" name="password" placeholder="Current Password" required>
            <input type="password" name="npassword" placeholder="New Password" required>
            <input type="submit" value="Change Password">
        </form>

        <div>
            <a href="/cabinet">
                <button type="button">Cabinet</button>
            </a>

            <a href="/logout">
                <button type="button">Logout</button>
            </a>
        </div>
    </body>
</html>