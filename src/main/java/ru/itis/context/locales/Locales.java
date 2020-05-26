package ru.itis.context.locales;

import java.util.HashMap;
import java.util.Map;

public class Locales {

    public static Map<String, String> RU = new HashMap<>();
    public static Map<String, String> ENG = new HashMap<>();

    static {
//        Russian
        RU.put("signin.login", "Имя пользователя");
        RU.put("signin.password", "Пароль");
        RU.put("signin.signin", "Войти");

        RU.put("signup.login", "Имя пользователя");
        RU.put("signup.email", "Электронная почта");
        RU.put("signup.password", "Пароль");
        RU.put("signup.signup", "Зарегистрироваться");

        RU.put("navbar.profile","Профиль");
        RU.put("navbar.users","Все пользователи");
        RU.put("navbar.posts","Публикации");
        RU.put("navbar.chat","Чат");
        RU.put("navbar.logout", "Выход");

        RU.put("chat.nomsg", "Пока сообщений нет");

        RU.put("posts.newpost", "Новая публикация");
        RU.put("posts.newpost.req", "* Обязательные поля");
        RU.put("posts.newpost.header", "Заголовок");
        RU.put("posts.newpost.content", "Содержание");
        RU.put("posts.newpost.post", "Опубликовать");
        RU.put("posts.asc", "В порядке возрастания");
        RU.put("posts.desc", "В порядке убывания");
        RU.put("posts.noposts", "Пока постов нет");

        RU.put("users.edit", "Редактировать");
        RU.put("users.posts", "Публикации");
        RU.put("users.chat", "Чат");

//        English
        ENG.put("signin.login", "Login");
        ENG.put("signin.password", "Password");
        ENG.put("signin.signin", "Sign In");

        ENG.put("signup.login", "Login");
        ENG.put("signup.email", "Email");
        ENG.put("signup.password", "Password");
        ENG.put("signup.signup", "Sign Up");

        ENG.put("navbar.profile","Profile");
        ENG.put("navbar.users","Users");
        ENG.put("navbar.posts","Posts");
        ENG.put("navbar.chat","Chat");
        ENG.put("navbar.logout", "Logout");

        ENG.put("chat.nomsg", "No messages yet");

        ENG.put("posts.newpost", "New post");
        RU.put("posts.newpost.req", "* Required Field");
        ENG.put("posts.newpost.header", "Header");
        ENG.put("posts.newpost.content", "Content");
        ENG.put("posts.newpost.post", "Post");
        ENG.put("posts.asc", "In ascending order");
        ENG.put("posts.desc", "In descending order");
        ENG.put("posts.noposts", "No posts yet");

        ENG.put("users.edit", "Edit");
        ENG.put("users.posts", "Posts");
        ENG.put("users.chat", "Chat");
    }
}
