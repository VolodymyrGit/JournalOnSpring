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
        <#else >
            <h1>There will be information about the teacher</h1>
            <h2>Teacher not added yet</h2>
        </#list>

        <table class="table table-bordered border-primary">

            <tr>
                <td> Students</td>

                <#list lessons as lesson>

                    <td>${lesson.getId()!}<br>${lesson.getLessonDate()!}</td>

                <#else>

                    <#list currentUser.getRoles() as role>
                        <#if role == "TEACHER" || role == "ADMIN" && !students??>
                            <td>
                                <a href="/add-lesson">Add Lesson</a>
                            </td>
                        </#if>
                    </#list>

                </#list>

            </tr>

            <#list students as student>

                <tr>

                <td>${hStudent.getUserName()!"No Students in the list"}</td>



                    <#list stHomeworks as homework>

                        <#if (homework.getHwDescription())??>

                            <td
                                    <#if homework.isDone()>
                                        style="background-color: green"

                                    <#else>
                                        style="background-color: yellow"
                                    </#if>
                            >

                                <#if currentUser.getId() == hStudent.getId()>
                                <a href="/hw?hwId=${homework.getId()}">
                                    </#if>
                                    ${homework.getHwDescription()!}
                                </a>
                            </td>

                        <#else>
                            <td>
                                <#if (currentUser.getId()) == (hStudent.getId())>
                                    <a href="/hw?hwId=${homework.getId()}">add</a>
                                </#if>
                            </td>
                        </#if>

                    <#else>
                        <td>There are no homeworks in Database</td>
                    </#list>
                </tr>
                <#list homeworks as hStudent, stHomeworks>

                <#else>
                    <tr>
                        <td>There are no students in the DataBase</td>
                    </tr>
                </#list>

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