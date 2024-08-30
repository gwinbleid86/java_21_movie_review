package kg.attractor.movie_review_21.service;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review_21.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> getUserList();

    UserDto getUserById(int id);

    UserDto getUserByName(String name);

    void createUser(UserDto userDto);

    Map<String, Object> forgotPassword(HttpServletRequest request);

    Map<String, Object> resetPasswordGet(String token);

    Map<String, Object> resetPasswordPost(HttpServletRequest request);
}
