package com.vinehds.dailysinc.messaging.event;

import com.vinehds.dailysinc.controller.dto.EmailDTO;


public record DailyEmailRequestedEvent(
        Long developerId,
        Long emailId,
        EmailDTO email
) {}