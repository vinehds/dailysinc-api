package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.controller.dto.UserDTO;
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
import java.util.Objects;

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
            var userWithEmail = userRepository.findByEmail(user.getEmail());
            if(Objects.nonNull(userWithEmail)) {
                throw new DataBaseException("User with this email already exists");
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public User updateUser(Long id, UserDTO updatedUser) {
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

    private void updateData(User entity, UserDTO obj) {
        entity.setName(obj.name());
        entity.setEmail(obj.email());
        entity.setActive(obj.active());
        entity.setRole(obj.role());
        entity.setSeniority(obj.seniority());
        entity.setDepartment(obj.department());

        Team team = null;
        if(Objects.nonNull(obj.teamId())) {
            team = new Team();
            team.setId(obj.teamId());
        }

        entity.setTeam(team);
    }

}
