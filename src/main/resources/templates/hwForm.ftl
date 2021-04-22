<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <title>Add Homework</title>

    </head>
    <body>
        <h2>Set Homework</h2>

        <form method="post" action="/homework">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="hidden" name="hwId" value="${homework.getId()}">
            <input type="text" name="hwDescription" value="${homework.getHwDescription()!}">
            <input type="submit">
        </form>
    </body>
</html>