<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <div>
        <#list users as user>
            <div class="list-group" style="width: 50%; margin-left: 45px">
                <div class="list-group-item list-group-item-action flex-column align-items-start">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">${user.userName}</h5>
                        <small class="text-muted">${language.get("users.posts")}: ${user.postsQuantity}</small>
                    </div>
                    <p class="mb-1">${user.email}</p>
                    <#if authority == true>
                        <form action="/changeUser" method="get"
                              style="display: inline-block; margin-top: 10px !important">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <button type="submit" class="btn btn-dark">${language.get("users.edit")}</button>
                        </form>
                        <form action="/chat" method="get">
                            <input type="hidden" name="owner" value="${user.userName}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <button type="submit" class="btn btn-danger">${language.get("users.chat")}</button>
                        </form>
                    </#if>
                    <form action="/posts" method="get"
                          style="display: inline-block; float: right; margin-top: 10px !important">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-light"
                                style="border: #4e555b 1px solid">${language.get("users.posts")}</button>
                    </form>
                </div>
            </div>
        </#list>
    </div>
</@p.page>