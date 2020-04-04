<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            border-radius: 0 0 5px 5px;
            max-width: 300px;
            margin: auto;
            text-align: center;
            font-family: arial;
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        .posts {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
            border-radius: 0 0 5px 5px;
        }

        .posts:hover {
            opacity: 0.7;
        }
    </style>

    <body>
<div class="card">
    <img src="https://image.flaticon.com/icons/png/512/56/56745.png" alt="" style="width:100%; margin-top: 10px">
    <h1>${user.getUserName()}</h1>
    <p class="title">${user.getEmail()}</p>

    <form action="/posts" method="get">
        <input type="hidden" name="id" value="${user.id}">
        <button class="posts" type="submit">Posts</button>
    </form>
</div>
</@p.page>