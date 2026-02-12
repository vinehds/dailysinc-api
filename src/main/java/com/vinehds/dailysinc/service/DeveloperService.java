package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.controller.dto.DeveloperDTO;
import com.vinehds.dailysinc.model.entities.Developer;
import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.repository.DeveloperRepository;
import com.vinehds.dailysinc.service.exception.DataBaseException;
import com.vinehds.dailysinc.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<Developer> getAllDevelopers() {
        try{
            return developerRepository.findAll();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Developer insertDeveloper(Developer developer) {
        try{
            return developerRepository.save(developer);
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Developer updateDeveloper(Long id, DeveloperDTO updatedDeveloperDTO) {
        try {
            if(!isExists(id))  throw new ResourceNotFoundException(id);

            Developer entity = developerRepository.getReferenceById(id);
            updateData(entity, updatedDeveloperDTO);

            return developerRepository.save(entity);
        }catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void deleteById(Long id){
        try {
            developerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private boolean isExists(Long id) {
        return developerRepository.existsById(id);
    }

    private void updateData(Developer entity, DeveloperDTO obj) {
        entity.setName(obj.name());
        entity.setActive(obj.active());
        entity.setSeniority(obj.seniority());
        entity.setDepartment(obj.department());

        Team team = new Team();
        team.setId(obj.teamId());
        entity.setTeam(team);
    }

}
