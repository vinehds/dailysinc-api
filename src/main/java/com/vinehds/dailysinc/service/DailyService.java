package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.controller.dto.DailyDTO;
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

    public Daily insertDaily(Daily daily) {
        try{
            return dailyRepository.save(daily);
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Daily updateDaily(Long id, DailyDTO updatedDaily) {
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

    private void updateData(Daily entity, DailyDTO obj) {
        entity.setDate(obj.date());
        entity.setLastDayLog(obj.lastDayLog());
        entity.setNextDayPlan(obj.nextDayPlan());
        entity.setBlockers(obj.blockers());
    }
}
