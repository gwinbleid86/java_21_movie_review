package kg.attractor.movie_review_21.controller.api;

import jakarta.validation.Valid;
import kg.attractor.movie_review_21.dto.UserDto;
import kg.attractor.movie_review_21.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUserList(){
        return userService.getUserList();
    }

    @PostMapping
    public void addUser(@RequestBody @Valid UserDto userDto) {
        userService.createUser(userDto);
    }
}
