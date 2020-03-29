package ru.itis.context.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.context.services.ConfirmService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConfirmingController {
    @Autowired
    private ConfirmService service;

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmUser(HttpServletRequest req) {
        String key = req.getParameter("key");
        service.setConfirm(key);
        return "redirect:/signIn";
    }
}
