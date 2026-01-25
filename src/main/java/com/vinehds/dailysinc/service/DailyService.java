package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.model.entities.Daily;
import com.vinehds.dailysinc.repository.DailyRepository;
import com.vinehds.dailysinc.service.exception.DataBaseException;
import com.vinehds.dailysinc.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;

    public List<Daily> getAllDailies() {
        try{
            return dailyRepository.findAll();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Daily getDailyById(Long id) {
        return dailyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Daily insertDaily(Daily team) {
        try{
            return dailyRepository.save(team);
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Daily updateDaily(Long id, Daily updatedDaily) {
        try {
            if(!isExists(id)) throw new ResourceNotFoundException(id);

            Daily entity = dailyRepository.getReferenceById(id);
            updateData(entity, updatedDaily);

            return dailyRepository.save(entity);
        }catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void deleteById(Long id){
        try {
            dailyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private boolean isExists(Long id) {
        return dailyRepository.existsById(id);
    }

    private void updateData(Daily entity, Daily obj) {
        entity.setDate(obj.getDate());
        entity.setLastDayLog(obj.getLastDayLog());
        entity.setNextDayPlan(obj.getNextDayPlan());
        entity.setBlockers(obj.getBlockers());
    }
}
