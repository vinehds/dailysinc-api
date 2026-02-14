package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.controller.dto.TeamDTO;
import com.vinehds.dailysinc.model.entities.Team;
import com.vinehds.dailysinc.repository.TeamRepository;
import com.vinehds.dailysinc.service.exception.DataBaseException;
import com.vinehds.dailysinc.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

   private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        try{
            return teamRepository.findAll();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

   public Team getTeamById(Long id) {
       return teamRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(id));
   }

   public Team insertTeam(Team team) {
       try{
           return teamRepository.save(team);
       } catch (Exception e) {
           throw new DataBaseException(e.getMessage());
       }
   }

    public Team updateTeam(Long id, TeamDTO updatedTeam) {
        try {
            if(!isExists(id)) throw new ResourceNotFoundException(id);

            Team entity = teamRepository.getReferenceById(id);
            updateData(entity, updatedTeam);

            return teamRepository.save(entity);
        }catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void deleteById(Long id){
        try {
            Team team = getTeamById(id);
            teamRepository.delete(team);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public boolean isExists(Long id) {
        return teamRepository.existsById(id);
    }

    private void updateData(Team entity, TeamDTO obj) {
        entity.setName(obj.name());
        entity.setDescription(obj.description());
    }


}
