package com.vinehds.dailysinc.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinehds.dailysinc.model.entities.Daily;
import com.vinehds.dailysinc.model.entities.User;

import java.time.LocalDate;

public record DailyDTO(Long id, @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3") LocalDate date,
                       String lastDayLog, String nextDayPlan, String blockers, Long authorId) {

    public static DailyDTO fromEntity(Daily daily) {
        return new DailyDTO(daily.getId(), daily.getDate(), daily.getLastDayLog(), daily.getNextDayPlan(),
                daily.getBlockers(), daily.getAuthor() != null ? daily.getAuthor().getId() : null);
    }

    public Daily toEntity() {
        Daily daily = new Daily();
        User author = new User();
        author.setId(this.authorId);
        daily.setAuthor(author);
        daily.setDate(this.date);
        daily.setLastDayLog(this.lastDayLog);
        daily.setNextDayPlan(this.nextDayPlan);
        daily.setBlockers(this.blockers);
        return daily;
    }
}
