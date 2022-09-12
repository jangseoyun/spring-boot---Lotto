package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.user.UserService;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.dto.LoginDto;
import lotto.practice.random.logging.Logging;
import lotto.practice.random.security.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private final SecurityService securityService;

    //회원가입 폼
    @GetMapping("/join-form")
    public String joinForm(Model model){
        model.addAttribute("joinDto", new JoinDto()); //화면에서 객체에 접근할 수 있게됨
        return "user/join-form";
    }

    //회원가입
    @Logging
    @PostMapping("/join")
    public String join(@Valid JoinDto joinDto, BindingResult result) {

        if (result.hasErrors()) {
            log.info("회원가입시 null 발생");
            return "user/join-form";
        }

        Long resultJoinNo = userService.joinUser(joinDto);

        if (resultJoinNo == null) {
            return "redirect:/user/join-form";
        }

        return "redirect:/user/login-form";

    }

    //로그인 폼
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    //로그인
    /*@PostMapping("/login")
    public Map<String, Object> login(@RequestParam(value = "subject") String subject) {//실제로는 로그인 정보 받기User
        String token = securityService.createToken(subject, (2 * 1000 * 60));//만료시간은 보통 30분이지만 테스트를 위해서 2분
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }*/
    @PostMapping("/login")
    public String login(LoginDto accessUser, HttpSession session) {
        log.info("accessUser : " + accessUser);
        User getUser = userService.login(accessUser);
        session.setAttribute("userId", getUser.getUserId());
        session.setAttribute("userNo", getUser.getNo());

        return "redirect:/lottery/index";
    }

    //값 검증하는것
    /*@GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = securityService.getSubject(token);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }*/

    //로그아웃
    public void logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userPw");
        session.invalidate();
    }


}
