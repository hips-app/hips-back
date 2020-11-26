package com.hips.api.services;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.repositories.StatisticsRepository;
import com.hips.api.responses.StatisticsPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class StatisticsService {
    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    StatisticsRepository statisticsRepository;


    public List<StatisticsPOJO> getUserStatistics(Integer userId){
        List<StatisticsPOJO> statistics = statisticsRepository.getUserExerciseStatistics(userId);

        return  statistics;
    }

}
