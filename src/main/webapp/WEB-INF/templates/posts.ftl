<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <#if owner == true>
    <div>
        <form action="/newPost" method="post" name="post">
            <label>Заголовок: <input type="text" name="header"></label>
            <label>Текст: <input type="text" name="content"></label>
            <input type="hidden" name="id" value="${id}">
            <button type="submit">Опубликовать</button>
        </form>
    </div>
    </#if>
    <div>
        <#if posts?has_content>
            <#list posts as post>
                <div>
                    <h3>${post.header}</h3>
                    <p>${post.content}</p>
                </div>
            </#list>
            <#else >
            <div>
                Пока постов нет
            </div>
        </#if>
    </div>
</@p.page>