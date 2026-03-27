package com.example.ota_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    // 仮のユーザー名とパスワード（後でDBに変更可能）
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    // ログイン画面表示
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // ログイン処理
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        if(ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("loggedIn", true);
            return "redirect:/dashboard";           
        }        
        return "redirect:/login?error";                   
     }

     // ログアウト
     @GetMapping("/logout")
     public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
     }
}
