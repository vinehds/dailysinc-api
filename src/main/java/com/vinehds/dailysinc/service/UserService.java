package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.model.entities.User;
import com.vinehds.dailysinc.repository.UserRepository;
import com.vinehds.dailysinc.service.exception.DataBaseException;
import com.vinehds.dailysinc.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        try{
            return userRepository.findAll();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User user) {
        try{
            return userRepository.save(user);
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public User updateUser(Long id, User updatedUser) {
        try {
            if(!isExists(id))  throw new ResourceNotFoundException(id);

            User entity = userRepository.getReferenceById(id);
            updateData(entity, updatedUser);

            return userRepository.save(entity);
        }catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void deleteById(Long id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

}
