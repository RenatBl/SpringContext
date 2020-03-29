<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <div>
        <#list users as user>
            <div>
                <strong>Имя пользователя: ${user.userName}</strong>
                <form action="/posts" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit">Перейти</button>
                </form>
            </div>
        </#list>
    </div>
</@p.page>