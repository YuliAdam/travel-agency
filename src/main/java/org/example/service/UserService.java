package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.UserRequest;
import org.example.controller.response.UserResponse;
import org.example.entity.Users;
import org.example.entity.characteristic.Role;
import org.example.exception.EntityNotExistException;
import org.example.exception.LoginJustExistException;
import org.example.repository.UserRepository;
import org.example.utils.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUser() {
        return userRepository.findAllUsers().stream().map(UserResponse::new).toList();
    }

    public List<UserResponse> findUsers(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        return (paramtr == null || paramtr.isBlank())
                ? userRepository.findUsers(page).stream().map(UserResponse::new).toList()
                : userRepository.findUsers(paramtr.trim(), page).stream().map(UserResponse::new).toList();
    }

    public UserResponse getUser(Long id) {
        return new UserResponse(userRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.USER_NOT_EXIST, id))));
    }

    public UserResponse getUserByLogin(String login) {
        return new UserResponse(userRepository.findByLogin(login));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.USER_NOT_EXIST, id)));
        userRepository.deleteById(id);
    }

    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.findByLogin(userRequest.getLogin()) == null) {
            Users user = new Users(userRequest.getUserName(), userRequest.getLogin(), userRequest.getTell()
                    , new BCryptPasswordEncoder(12).encode(userRequest.getPassword()), Role.USER);
            if (userRequest.getRole() == null || userRequest.getRole().equals(Role.USER)) {
                return new UserResponse(userRepository.save(user));
            } else {
                user.setRole(Role.ADMIN);
                return new UserResponse(userRepository.save(user));
            }
        } else {
            throw new LoginJustExistException(String.format(Messages.LOGIN_JUST_EXIST, userRequest.getLogin()));
        }
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        if (userRepository.findByLogin(userRequest.getLogin()) == null) {
            Users existingUser = userRepository.findById(id).get();
            existingUser.setUserName(userRequest.getUserName());
            existingUser.setLogin(userRequest.getLogin());
            existingUser.setTell(userRequest.getTell());
            if (!existingUser.getPassword().equals(userRequest.getPassword())) {
                existingUser.setPassword(new BCryptPasswordEncoder(12).encode(userRequest.getPassword()));
            }
            existingUser.setRole(userRequest.getRole());
            return new UserResponse(userRepository.save(existingUser));
        } else {
            throw new LoginJustExistException(String.format(Messages.LOGIN_JUST_EXIST, userRequest.getLogin()));
        }
    }

}
