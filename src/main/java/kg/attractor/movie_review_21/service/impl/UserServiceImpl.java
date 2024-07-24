package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.UserDao;
import kg.attractor.movie_review_21.dto.UserDto;
import kg.attractor.movie_review_21.model.User;
import kg.attractor.movie_review_21.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUserList() {
        var list = userDao.getAllUsers();

        return list.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getUsername())
                        .password(e.getPassword())
                        .build())
                .toList();
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userDao.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.builder()
                .id(user.getId())
                .name(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userDao.getUserByUsername(name)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.builder()
                .id(user.getId())
                .name(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
