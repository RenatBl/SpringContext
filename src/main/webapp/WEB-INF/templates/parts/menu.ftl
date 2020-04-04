<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
    </head>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Raleway:400,200,300,100,500,700,600,800,900);

            .clear {
                clear: both;
            }

            .wrapper {
                width: 100%;
                margin-bottom: 20px;
                background: #d4d4d4;
                font-family: 'Raleway', sans-serif;
                font-weight: 400;
                font-size: 14px;
                line-height: 26px;
                color: #666;
            }

            .navigation {
                list-style: none;
                padding: 0;
                margin: 0;
                background: rgb(58, 58, 58);
            }

            .navigation li {
                float: left;
            }

            .navigation li:hover {
                background: #222;
            }

            .navigation li:first-child {
                -webkit-border-radius: 5px 5px 0 0;
                border-radius: 5px 0 0 5px;
            }

            .navigation li a {
                display: block;
                padding: 0 20px;
                text-decoration: none;
                line-height: 40px;
                color: #fff;
            }

            .navigation ul {
                display: none;
                position: absolute;
                list-style: none;
                margin-left: -3px;
                padding: 0;
                overflow: hidden;
            }

            .navigation ul li {
                float: none;
            }

            .navigation li:hover > ul {
                display: block;
                background: #222;
                border: solid 3px #fff;
                border-top: 0;

                -webkit-border-radius: 0 0 8px 8px;
                border-radius: 0 0 8px 8px;

                -webkit-box-shadow: 0px 3px 3px 0px rgba(0, 0, 0, 0.25);
                box-shadow: 0px 3px 3px 0px rgba(0, 0, 0, 0.25);
            }

            .navigation li:hover > ul li:hover {
                -webkit-border-radius: 0 0 5px 5px;
                border-radius: 0 0 5px 5px;
            }

            .navigation li li a:hover {
                background: #000;
            }

            .navigation ul li:last-child a,
            .navigation ul li:last-child a:hover {
                -webkit-border-radius: 0 0 5px 5px;
                border-radius: 0 0 5px 5px;
            }

            .logout {
                float: right;
            }
        </style>

    <body style="background: #DCDCDC">
    <div class="wrapper">
        <ul class="navigation">
            <li><a href="/user" title="Profile">Profile</a></li>
            <li><a href="/users" title="All users">All users</a></li>
            <li><a href="/posts" title="Posts">Posts</a></li>
            <li class="logout"><a href="/logout" title="Logout">Logout</a></li>
            <div class="clear"></div>
        </ul>
    </div>
    <#nested/>
    </body>
    </html>
    </#macro>