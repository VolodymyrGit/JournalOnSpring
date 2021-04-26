<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${currentUser.getUserName()} Cabinet</title>
        <link rel="stylesheet" href="../css/cabinet.css">
    </head>
    <body>
        <div class="user-data">
            <h1>${currentUser.getUserName()}</h1>
            <h1>Email - ${currentUser.getEmail()}</h1>
            <h1>Phone Number - ${currentUser.getPhoneNumber()}</h1>
            <h1>Group Id - ${currentUser.getGroup().getId()}</h1>
            <h1>${currentUser.getRole().name()}</h1>
            <h1>Password ***</h1>
            <a href="/change-info"><button type="button">Change Info Data</button></a>
        </div>

        <div class="buttons">
            <a href="/table"><button type="button">Journal Table</button></a>

            <a href="/logout"><button type="button">Logout</button></a>
        </div>
    </body>
</html>