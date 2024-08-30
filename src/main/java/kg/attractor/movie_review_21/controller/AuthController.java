package kg.attractor.movie_review_21.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.movie_review_21.dto.UserDto;
import kg.attractor.movie_review_21.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.createUser(userDto);
            return "redirect:/";
        }

        model.addAttribute("userDto", userDto);
        return "auth/register";
    }

    @GetMapping("forgot_password")
    public String forgotPassword() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String forgotPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(userService.forgotPassword(request));
        return "auth/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String resetPassword(@RequestParam String token, Model model) {
        model.addAllAttributes(userService.resetPasswordGet(token));
        return "auth/reset_password_form";
    }

    @PostMapping("reset_password")
    public String resetPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(userService.resetPasswordPost(request));
        return "auth/message";
    }
}
