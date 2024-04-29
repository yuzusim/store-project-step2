package com.example.storeprojectstep1.user;

import com.example.storeprojectstep1.errors.exception.Exception401;
import com.example.storeprojectstep1.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepo;

    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        //System.out.println(reqDTO);

        User sessionUser = userService.save(reqDTO.toEntity());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/login-form";
    }

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(@RequestParam("username") String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) { // 회원가입 해도 된다.
            return new ApiUtil<>(true);
        } else { // 회원가입 하면 안된다.
            return new ApiUtil<>(false);
        }
    }


    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        System.out.println(reqDTO);


        if (reqDTO.getUsername().length() < 3) {
            // 유효하지 않은 경우 에러 페이지나 로그인 폼으로 리다이렉션
            return "redirect:/user/login-form";
        }

        try {
            User user = userRepo.findByUsernameAndPassword(reqDTO);

//            User sessionUser = userService.login(reqDTO);

            // 권한 체크
            Boolean isCheck = false;
            if (user.getRole() == 2) { // 1 -> admin, 2 -> user
                isCheck = true;
            }

            // 세션에 사용자 정보 및 권한 체크 결과 설정
            session.setAttribute("isCheck", isCheck);
            session.setAttribute("sessionUser", user); // 락카에 담음 (StateFul)

            return "redirect:/";

        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }

    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        // 세션(session)을 무효화(invalidate)하는 작업을 수행
        session.invalidate();
        return "redirect:/";
    }
}
