<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Journal Table</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
              crossorigin="anonymous">
        <link rel="stylesheet" href="../css/table.css">
    </head>

    <body>
        <#list teachers as teacher>
            <h1>${teacher.getUserName()!} - ${teacher.getEmail()!}</h1>
        </#list>

        <table class="table table-bordered border-primary">

            <tr>
                <td> Students</td>

                <#list lessons as lesson>
                    <td>${lesson.getId()!}<br>${lesson.getLessonDate()!}</td>

                    <#else>
                    <td>There ara no lesson yet</td>
                </#list>

                <#if currentUser.getRole().equals("TEACHER")>
                    <td>
                        <a href="/add-lesson">Add Lesson</a>
                    </td>
                </#if>
            </tr>

            <#list homeworks as student, stHomeworks>
                <tr>
                    <td>${student.getUserName()!}</td>

                    <#list stHomeworks as homework>

                        <#if (homework.getHwDescription())??>

                            <td
                            <#if homework.isDone()>
                                style="background-color: green"

                            <#else>
                                    style="background-color: yellow"
                            </#if>
                            >

                                <#if currentUser.getId() == student.getId()>
                                    <a href="/hw?hwId=${homework.getId()}">
                                </#if>
                                        ${homework.getHwDescription()!}
                                    </a>
                            </td>

                        <#else>
                            <td>
                                <#if (currentUser.getId()) == (student.getId())>
                                    <a href="/hw?hwId=${homework.getId()}">add</a>
                                </#if>
                            </td>
                        </#if>

                    <#else>
                        <td>There are no homeworks in Database</td>
                    </#list>
                </tr>

                    <#else>
                        <tr>
                            <td>There are no students in the DataBase</td>
                        </tr>
            </#list>
        </table>

        <div>
            <a href="/cabinet">
                <button type="button">Cabinet</button>
            </a>

            <a href="/logout">
                <button type="button">Logout</button>
            </a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
                crossorigin="anonymous">
        </script>
    </body>
</html>