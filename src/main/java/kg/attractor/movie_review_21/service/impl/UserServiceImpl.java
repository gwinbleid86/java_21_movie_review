package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.UserDao;
import kg.attractor.movie_review_21.dto.UserDto;
import kg.attractor.movie_review_21.model.User;
import kg.attractor.movie_review_21.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Override
    public List<UserDto> getUserList() {
        var list = userDao.getAllUsers();

        return list.stream()
                .map(e -> UserDto.builder()
                        .email(e.getEmail())
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
                .email(user.getEmail())
                .name(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userDao.getUserByUsername(name)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void createUser(UserDto userDto) {
        log.info(userDto.toString());
        log.info(encoder.encode(userDto.getPassword()));
    }
}
