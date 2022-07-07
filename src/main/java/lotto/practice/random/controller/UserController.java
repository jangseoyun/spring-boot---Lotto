package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 1. 회원가입
 * 2. 로그인
 */

@Slf4j
@Controller
@RequiredArgsConstructor //의존성 주입
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //회원가입 폼
    @GetMapping ("/joinForm")
    public String joinForm(Model model){
        model.addAttribute("joinDto", new JoinDto()); //화면에서 객체에 접근할 수 있게됨
        return "user/join-form";
    }

    //회원가입
    @PostMapping("/join")
    public String join(@Valid JoinDto joinDto
                        , BindingResult result){
        if(result.hasErrors()){
            log.info("회원가입시 null 발생");
            return "user/join-form";
        }

        Long resultJoinNo = userService.joinUser(joinDto);
        //resultJoinNo 있으면 loginForm으로, 없으면 가입실패 페이지
        return "redirect:/user/loginForm";
    }

    //로그인 폼
    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/login-form";
    }

    //로그인
    @PostMapping("/login")
    public String login(ModelAttribute Users, HttpSession httpSession){

        return "";
    }

    //로그아웃






}
