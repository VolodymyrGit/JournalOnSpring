<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>File</title>
    </head>
    <body>

        <#list files! as file>
            <img src="${pathname!}\${file!}">
        </#list>

        <form method="post" action="/file" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input name="file" type="file">
            <input type="submit">
        </form>

    </body>
</html>