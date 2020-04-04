<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <div>
    <#list users as user>
    <div class="list-group" style="width: 50%; margin-left: 45px">
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">${user.userName}</h5>
                <small class="text-muted">Posts: ${user.postsQuantity}</small>
            </div>
            <p class="mb-1">${user.email}</p>
            <#if authority == true>
                <form action="/changeUser" method="get" style="display: inline-block; margin-top: 10px !important">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-dark">Edit</button>
                </form>
            </#if>
            <form action="/posts" method="get" style="display: inline-block; float: right; margin-top: 10px !important">
                <input type="hidden" name="id" value="${user.id}">
                <button type="submit" class="btn btn-light" style="border: #4e555b 1px solid">Posts</button>
            </form>
        </div>
    </div>
        </#list>
    </div>
</@p.page>