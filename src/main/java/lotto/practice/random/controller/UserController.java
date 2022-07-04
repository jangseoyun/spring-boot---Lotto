package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor //의존성 주입
@RequestMapping("/user")
public class UserController {

    //로그인 폼
    @GetMapping("/loginForm")
    public String loginForm(){
        return "/user/login-form";
    }

    @PostMapping("/login")
    public String login(ModelAttribute Users, HttpSession httpSession){

        return "";
    }

    //회원가입 폼
    @GetMapping ("/joinForm")
    public String joinForm(){
        return "/user/join-form";

    }




}
