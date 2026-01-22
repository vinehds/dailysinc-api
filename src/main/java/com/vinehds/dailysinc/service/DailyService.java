package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.repository.DailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;
}
