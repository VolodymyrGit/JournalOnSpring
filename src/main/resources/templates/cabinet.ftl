<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${currentUser.getUserName()} Cabinet</title>
<#--        <link rel="stylesheet" href="../css/cabinet.css">-->
    </head>
    <body>
        <div class="user-data">
            <h1>${currentUser.getUserName()}</h1>
            <h1>Email - ${currentUser.getEmail()}</h1>
            <h1>Phone Number - ${currentUser.getPhoneNumber()}</h1>

            <#if currentUser.getGroup()??>
                <h1>Group Id - ${currentUser.getGroup().getId()!}</h1>
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
                        <a href="/list-users"><button type="button">List Users</button></a>

                        <form method="post" action="/cabinet">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="text" name="info" placeholder="group info">
                            <input type="number" name="newGroupId">
                            <input type="submit" value="Add new group">
                        </form>

                        <#if groups??>
                            <#list groups as group>

                                <form method="get" action="/table">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <input type="hidden" name="group" value="${group.getId()}">
                                    <input type="submit" value="Table of group ${group.getId()}">
                                </form>
                            </#list>
                        </#if>
                    </#if>
                </#list>
        </div>

        <div class="buttons">
            <a href="/table"><button type="button">Journal Table</button></a>

            <a href="/logout"><button type="button">Logout</button></a>
        </div>
    </body>
</html>