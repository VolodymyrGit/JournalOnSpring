<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <title>Add Homework</title>

    </head>
    <body>

        <form method="post" action="/hw">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="description" value="${description!}">
            <input type="hidden" name="hwId" value="${hwId!}">
            <input type="submit">

        </form>
    </body>
</html>