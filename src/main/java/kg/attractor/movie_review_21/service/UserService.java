package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUserList();

    UserDto getUserById(int id);

    UserDto getUserByName(String name);
}
