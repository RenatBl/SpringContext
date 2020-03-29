<#ftl encoding='UTF-8'>
<#import "parts/html.ftl" as p>
<@p.page>
    <div>
        <form action="/signIn" method="post">
            <label>Username: <input type="text" name="userName" placeholder="Enter the username"></label>
            <label>Password: <input type="password" name="password" placeholder="Enter the password"></label>
            <button type="submit">Войти</button>
        </form>
    </div>
    <a href="/signUp">Sign Up</a>
</@p.page>