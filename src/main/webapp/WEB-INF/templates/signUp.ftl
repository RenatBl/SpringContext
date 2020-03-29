<#ftl encoding='UTF-8'>
<#import "parts/html.ftl" as p>
<@p.page>
    <div>
        <form action="/signUp" method="post" name="form">
            <label>Username: <input type="text" name="userName" placeholder="Enter the username"></label>
            <label>E-mail: <input type="text" name="email" placeholder="Enter the email"></label>
            <label>Password: <input type="password" name="password" placeholder="Enter the password"></label>
            <button type="submit">Регистрация</button>
        </form>
    </div>
    <a href="/signIn">Sign In</a>
</@p.page>