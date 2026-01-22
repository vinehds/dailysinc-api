package com.vinehds.dailysinc.service;

import com.vinehds.dailysinc.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

   private final TeamRepository teamRepository;

}
