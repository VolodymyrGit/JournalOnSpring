<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
              crossorigin="anonymous">
    </head>

    <body>

    <h1>List of registered users</h1>

    <h2>Admin ${admin.getUserName()!} ${admin.getEmail()!}</h2>

    <table class="table table-bordered border-primary">
        <#list users as user>

            <tr>
                <td>${user.getUserName()!} ${user.getEmail()!}</td>

                <td>
                    Roles:
                    <#list user.getRoles() as role>
                        ${role.name()!}
                    <#else >
                        <p> not defined</p>
                    </#list>
                </td>

                <td>
                    <form method="post" action="/set-role">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="id" value="${user.getId()!}">
                        <label><select name="role" required>
                                <#list roles as role>
                                    <option value="${role.name()!}">${role.name()!}</option>
                                </#list>
                            </select> Role </label>
                        <input type="submit" value="Set Role">
                    </form>
                </td>

                <td>
                    Group:
                    <#if user.getGroup()??>
                        ${user.getGroup().getId()!}
                    <#else >
                        <p> not defined</p>
                    </#if>
                </td>

                <td>
                    <form method="post" action="/set-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="hidden" name="userId" value="${user.getId()!}">
                        <label><select name="group" required>
                                    <#list groups as group>
                                        <option value="${group.getId()!}">${group.getId()!}</option>
                                    <#else>
                                        <option>There are no groups in the database</option>
                                    </#list>
                            </select> Group id</label><br>
                        <input type="submit" value="Set Group">
                    </form>
                </td>
            </tr>
        <#else >

            <tr>
                <td><p>There are no users yet</p></td>
            </tr>
        </#list>
    </table>

    <a href="/cabinet">
        <button type="button">Cabinet</button>
    </a>

    <a href="/logout">
        <button type="button">logout</button>
    </a>
    </body>
</html>