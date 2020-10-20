package com.foxconn.sw.macaddress.controller;

import com.foxconn.sw.macaddress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/doLogin")
    public String doLogin(HttpSession session, String username, String password, Model model) {

        String pass = userService.selectPassword(username);
        if (password.equals(pass)) {
            session.setAttribute("LoginState", username);
            model.addAttribute("LoginState", username);
            // 重定向  : 名字 在默认视图解析器里配置
            return "redirect:/list";
        }
        model.addAttribute("loginMsg", "用户名或密码错误！");
        return "home/index";
    }

    @GetMapping("/doQuit")
    public String doQuit(HttpSession session) {
        session.removeAttribute("LoginState");
        session.invalidate();
        return "redirect:index";
    }

    // chat 页
    @GetMapping("/chat")
    public String chat() {
        return "home/dashboard";
    }
}
