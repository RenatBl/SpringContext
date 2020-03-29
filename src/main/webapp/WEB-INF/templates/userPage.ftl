<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <div>
        <strong>Имя пользователя: <p>${user.getUserName()}</p></strong>
        <strong>E-mai: <p>${user.getEmail()}</p></strong>
    </div>
    <form action="/posts" method="get">
        <input type="hidden" name="id" value="${user.getId()}">
        <button type="submit">Публикации</button>
    </form>
</@p.page>