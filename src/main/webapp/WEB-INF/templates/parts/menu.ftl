<#import "html.ftl" as h>
<@h.page>
    <#macro page>
    <header>
        <div>
            <a href="/user">
                <div>
                    Профиль
                </div>
            </a>
            <a href="/users">
                <div>
                    Все пользователи
                </div>
            </a>
            <a href="/files">
                <div>
                    Загруженные файлы
                </div>
            </a>
        </div>
        <div style="alignment: right">
            <a href="/logout">Logout</a>
        </div>
    </header>
        <#nested/>
    </#macro>
</@h.page>