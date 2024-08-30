package kg.attractor.movie_review_21.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review_21.common.Utilities;
import kg.attractor.movie_review_21.dao.UserDao;
import kg.attractor.movie_review_21.dto.UserDto;
import kg.attractor.movie_review_21.model.User;
import kg.attractor.movie_review_21.repository.RoleRepository;
import kg.attractor.movie_review_21.repository.UserRepository;
import kg.attractor.movie_review_21.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RoleRepository roleRepository;

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
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(new HashSet<>());
        user.setEnabled(Boolean.TRUE);

        user.addRole(roleRepository.findByRoleName("GUEST"));
        userRepository.saveAndFlush(user);
    }

    @Override
    public Map<String, Object> forgotPassword(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        try {
            makeResetPasswordLink(request);
            model.put("message", "we have sent reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.put("error", e.getMessage());
        } catch (MessagingException e) {
            model.put("error", "Error while sending reset password link to your email.");
        }
        return model;
    }

    @Override
    public Map<String, Object> resetPasswordGet(String token) {
        Map<String, Object> model = new HashMap<>();
        try {
            getByToken(token);
            model.put("token", token);
        } catch (UsernameNotFoundException e) {
            model.put("error", "Invalid token");
        }
        return model;
    }

    @Override
    public Map<String, Object> resetPasswordPost(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try {
            User user = getByToken(token);
            updatePassword(user, password);
            model.put("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException e) {
            model.put("message", "Invalid token");
        }
        return model;
    }

    private void makeResetPasswordLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateToken(token, email);

        String resetPasswordLink = Utilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendMail(email, resetPasswordLink);
    }

    private User getByToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void updatePassword(User user, String password) {
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    private void updateToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email: " + email));
        user.setResetPasswordToken(token);
        userRepository.save(user);
    }

}
