<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous"></script>
        <script src="static/js/chat.js"></script>
    </head>

    <style>
        .chat {
            font-family: 'Avenir', serif;
            width: 900px;
            margin-left: 10%;
            margin-top: 2%;
            border: 6px rgba(212, 228, 239, 1) solid;
            border-radius: 6px;
        }

        .chat-box {
            overflow-y: scroll;
            background: #D3D3D3;
            height: 600px;
        }

        .no-msg {
            margin-top: 40px;
            margin-right: auto;
            margin-left: auto;
            text-align: center;
            font-family: 'Avenir', sans-serif;
            font-size: 16pt;
        }

        .bubbleWrapper {
            padding: 10px 10px;
            display: flex;
            justify-content: flex-end;
            flex-direction: column;
            align-self: flex-end;
            color: #fff;
        }

        .inlineContainer {
            display: inline-flex;
        }

        .inlineContainer.own {
            flex-direction: row-reverse;
        }

        .ownBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #5b5377;
            border-radius: 16px 16px 0 16px;
            border: 1px solid #443f56;

        }

        .otherBubble {
            min-width: 60px;
            max-width: 700px;
            padding: 14px 18px;
            margin: 6px 8px;
            background-color: #6C8EA4;
            border-radius: 16px 16px 16px 0;
            border: 1px solid #54788e;

        }

        .own {
            align-self: flex-end;
        }

        .other {
            align-self: flex-start;
        }

        span.own,
        span.other {
            font-size: 14px;
            color: grey;
        }

        .msg-input {
            border-top: 5px rgba(212, 228, 239, 1) solid;
            min-height: 70px;
            vertical-align: center;
            display: flex;
        }

        .inp-box {
            display: inline-block;
            float: left
        }

        .inp {
            padding-left: 8px;
            border-radius: 8px 0 0 8px;
            border: 1px solid #ccc;
            width: 810px;
            height: 30px;
            position: inherit;
            outline: none;
            -webkit-appearance: none;
            vertical-align: middle;
        }

        .send-box {
            display: inline-block;
            float: left;
            border: 1px #ccc solid;
            width: 30px;
            height: 32px;
            border-radius: 0 8px 8px 0;
        }

        .send {
            width: 30px;
            height: 30px;
            border: none;
            background: url("https://img.icons8.com/ios-glyphs/30/000000/telegram-app.png");
            outline: none;
            -webkit-appearance: none;
        }

        .send:hover {
            background: url("https://img.icons8.com/material-two-tone/30/000000/telegram-app.png");
        }

        .send:active {
            transform: translateY(4px);
        }

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

    <body style="background: #DCDCDC" onload="">
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