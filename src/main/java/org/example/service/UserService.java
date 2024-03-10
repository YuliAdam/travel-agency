package org.example.service;

import org.example.controller.request.UserRequest;
import org.example.controller.response.AdminResponse;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.UserResponse;
import org.example.entity.Admin;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
@Autowired
    UserRepository userRepository;
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserResponse::new).toList();
    }
    public List<UserResponse> findUserByName(String name) {
        return userRepository.findAll().stream().map(UserResponse::new).filter( user -> user.getUserName().replaceAll(" ","").equals(name)).toList();
    }
    public UserResponse getUser(Long id){
        return new UserResponse(userRepository.findById(id).get());
    }
    public String deleteUser(Long id){
        try {
            userRepository.findById(id).get() ;
            userRepository.deleteById(id);
            return "User deleted. ";
        } catch (NoSuchElementException e){
            System.out.println("Error. Class UserService. User not found. "+ e);
            return "User not found. "+e;
        }
    }
    public UserResponse createUser(UserRequest userRequest){
        try {
        User user= new User(userRequest.getUserName(), userRequest.getEmail(),userRequest.getTell());
        return  new UserResponse(userRepository.save(user));
        } catch (DataIntegrityViolationException e){
            System.out.println("Error. Class UserService. User_name cannot be null. "+e);
            return new UserResponse("User_name cannot be null. "+e);
        }
    }
    public UserResponse updateUser(Long id, UserRequest userRequest){
        try {
        User existingUser = userRepository.findById(id).get();
        existingUser.setUserName(userRequest.getUserName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setTell(userRequest.getTell());
        return new UserResponse(userRepository.save(existingUser));
    }catch (NoSuchElementException e){
        System.out.println("Error. Class UserService. User not found. "+e);
        return new UserResponse("User not found. "+e);
    }catch (DataIntegrityViolationException e){
        System.out.println("Error. Class UserService. User_name cannot be null. "+e);
        return new UserResponse("User_name cannot be null. "+e);
    }
    }

}
