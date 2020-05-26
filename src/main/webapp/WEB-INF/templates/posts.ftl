<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <style>
        /* === Remove input autofocus webkit === */
        *:focus {outline: none;}

        /* === Form Typography === */
        .contact_form {
            font: 14px/21px "Lucida Sans", "Lucida Grande", "Lucida Sans Unicode", sans-serif;
            float: right;
            vertical-align: center;
            border: 2px #696969 solid;
            border-radius: 8px;
            margin-top: 110px;
            margin-right: 80px;
            position: fixed;
            alignment: right;
            margin-left: 680px;
            background: #D3D3D3;
        }

        .contact_form h2, .contact_form label {
            font-family: Georgia, Times, "Times New Roman", serif;
        }

        .form_hint, .required_notification {
            font-size: 11px;
        }

        /* === List Styles === */
        .contact_form ul {
            width:750px;
            list-style-type:none;
            list-style-position:outside;
            margin:0px;
            padding:0px;
        }
        .contact_form li{
            padding:12px;
            border-bottom:1px solid #eee;
            position:relative;
        }
        .contact_form li:first-child, .contact_form li:last-child {
            border-bottom:1px solid #777;
        }

        /* === Form Header === */
        .contact_form h2 {
            margin:0;
            display: inline;
        }
        .required_notification {
            color:#d45252;
            margin:5px 0 0 0;
            display:inline;
            float:right;
        }

        /* === Form Elements === */
        .contact_form label {
            width:150px;
            margin-top: 3px;
            display:inline-block;
            float:left;
            padding:3px;
        }
        .contact_form input {
            height:20px;
            width:220px;
            padding:5px 8px;
        }
        .contact_form textarea {padding:8px; width:300px;}
        .contact_form button {margin-left:156px;}

        /* form element visual styles */
        .contact_form input, .contact_form textarea {
            border:1px solid #aaa;
            box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
            border-radius:2px;
            padding-right:30px;
            -moz-transition: padding .25s;
            -webkit-transition: padding .25s;
            -o-transition: padding .25s;
            transition: padding .25s;
        }
        .contact_form input:focus, .contact_form textarea:focus {
            background: #fff;
            border:1px solid #555;
            box-shadow: 0 0 3px #aaa;
            padding-right:70px;
        }

        .contact_form input:focus + .form_hint {display: inline;}
        .contact_form input:required:valid + .form_hint {background: #28921f;}
        .contact_form input:required:valid + .form_hint::before {color:#28921f;}

        /* === Button Style === */
        button.submit {
            background-color: #696969;
            background: -webkit-gradient(linear, left top, left bottom, from(#696969), to(#808080));
            background: -webkit-linear-gradient(top, #696969, #808080);
            background: -moz-linear-gradient(top, #696969, #808080);
            background: -ms-linear-gradient(top, #696969, #808080);
            background: -o-linear-gradient(top, #696969, #808080);
            background: linear-gradient(top, #696969, #808080);
            border: 1px solid #808080;
            border-radius: 3px;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            -ms-border-radius: 3px;
            -o-border-radius: 3px;
            color: white;
            font-weight: bold;
            padding: 6px 20px;
            text-align: center;
            text-shadow: 0 -1px 0 #696969;
        }
        button.submit:hover {
            opacity:.85;
            cursor: pointer;
        }
        button.submit:active {
            border: 1px solid #20911e;
            box-shadow: 0 0 10px 5px #356b0b inset;
            -webkit-box-shadow:0 0 10px 5px #356b0b inset ;
            -moz-box-shadow: 0 0 10px 5px #356b0b inset;
            -ms-box-shadow: 0 0 10px 5px #356b0b inset;
            -o-box-shadow: 0 0 10px 5px #356b0b inset;

        }
    </style>


    <#if owner == true>
        <form class="contact_form" action="/newPost" method="post" name="post">
            <ul>
                <li>
                    <h2>${language.get("posts.newpost")}</h2>
                    <span class="required_notification">${language.get("posts.newpost.req")}</span>
                </li>
                <li>
                    <label for="header">${language.get("posts.newpost.header")}:</label>
                    <input type="text" name="header" id="header" required />
                </li>
                <li>
                    <label for="content">${language.get("posts.newpost.content")}:</label>
                    <textarea name="content" id="content" cols="40" rows="6" required ></textarea>
                </li>
                <li>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button class="submit" type="submit">Post</button>
                </li>
            </ul>
        </form>

        <div class="sort"
             style="width: 38%; height: 50px; margin-left: 30px; margin-top: 25px; margin-bottom: 12px; border-bottom: 1px black solid; text-align: center">
            <div style="display: inline-block;">
                <form action="/posts" method="get">
                    <input type="hidden" name="sort" value="asc">
                    <label for="asc">${language.get("posts.asc")}: </label>
                    <button id="asc" type="submit" style="border: 1px black solid; border-radius: 5px"><img
                                src="https://img.icons8.com/ios-filled/30/000000/generic-sorting.png"/>
                    </button>
                </form>
            </div>
            <div style="display: inline-block">
                <form action="/posts" method="get">
                    <input type="hidden" name="sort" value="desc">
                    <label for="desc" style="margin-left: 20px">${language.get("posts.desc")}: </label>
                    <button id="desc" type="submit" style="border: 1px black solid; border-radius: 5px"><img
                                src="https://img.icons8.com/ios-filled/30/000000/generic-sorting-2.png"/>
                    </button>
                </form>
            </div>
        </div>
    </#if>

    <div>
        <#if posts?has_content>
            <#list posts as post>
                <div class="card" style="margin-left: 45px; margin-top: 7px; margin-bottom: 7px; max-width: 36%; border-radius: 10px">
                    <div class="card-header">
                        ${post.header}
                    </div>
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <p>${post.content}</p>
                            <footer class="blockquote-footer"><cite title="Source Title">${post.date}</cite></footer>
                        </blockquote>
                    </div>
                </div>
            </#list>
            <#else >
            <div style="margin-left: 65px; margin-top: 40px;font-family: 'Raleway', sans-serif; font-weight: 400; font-size: 25px;">
                ${language.get("posts.noposts")}
            </div>
        </#if>
    </div>


</@p.page>