<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>

    <div style="margin: 50px; width: 40%; padding: 20px; border-radius: 4px; border: grey 1px solid">
        <form action="/change" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Username</span>
                </div>
                <input type="text" class="form-control" name="userName" aria-label="Username"
                       aria-describedby="basic-addon1" value="${user.userName}"/>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Email</span>
                </div>
                <input type="text" class="form-control" name="email" aria-label="Username"
                       aria-describedby="basic-addon1" value="${user.email}">
            </div>

            <div>
                <button type="submit" class="btn btn-dark">Edit</button>
            </div>
        </form>
    </div>
</@p.page>