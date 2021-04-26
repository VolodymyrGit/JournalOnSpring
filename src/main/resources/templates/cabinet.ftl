<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${currentUser.getUserName()} Cabinet</title>
    </head>

    <body>

        <div class="user-data">

            <h1>${currentUser.getUserName()}</h1>

            <#if confirmEmailErrorMessage??>
                <h3 style="color: red">${confirmEmailErrorMessage}</h3>
            </#if>
            <h1>Email - ${currentUser.getEmail()}</h1>

            <h1>Phone Number - ${currentUser.getPhoneNumber()}</h1>

            <#if currentUser.getGroup()??>

                <#list currentUser.getRoles() as role>

                    <#if role != "ADMIN">

                        <h1>Group Id - ${currentUser.getGroup().getId()!}</h1>
                    </#if>
                </#list>
            <#else >
                <h1>The group has not been defined</h1>
            </#if>

            <#list currentUser.getRoles() as role>
                <h1>${role.name()!}</h1>
            <#else >
                <h1>The role has not been defined</h1>
            </#list>

            <h1>Password ***</h1>

            <a href="/change-info"><button type="button">Change Info Data</button></a>

            <#list currentUser.getRoles() as role>

                <#if role == "ADMIN">

                    <a href="/admin-page"><button type="button">Admin Page</button></a>

                    <form method="post" action="/cabinet-add-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="text" name="info" placeholder="group info">
                        <input type="number" name="newGroupId">
                        <input type="submit" value="Add new group">
                    </form>

                    <#if groups??>

                        <form method="get" action="/table">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <label>Go to Group <select name="group" required>
                                    <#list groups as group>
                                        <option value="${group.getId()!}">${group.getId()!}</option>
                                    <#else>
                                        <option>There are no groups in the database</option>
                                    </#list>
                                </select> Table</label>
                            <input type="submit" value="Go">
                        </form>
                    </#if>
                </#if>
            </#list>
        </div>

        <div class="buttons">
            <#list currentUser.getRoles() as role>
                <#if role != "ADMIN">
                    <a href="/table"><button type="button">Journal Table</button></a>
                </#if>
            </#list>
            <a href="/logout"><button type="button">Logout</button></a>
        </div>
    </body>
</html>