package com.hips.api.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hips.api.repositories.PlannedExerciseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedExerciseService {
    @Autowired
    PlannedExerciseRepository plannedExerciseRepository;

    public Calendar castDate(Date date) {
        String date1=date.toString();
        String iniDate = date1.substring(0, Math.min(date1.length(), 10));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateIni = null;
        try {
          dateIni = dateFormat.parse(iniDate);
        } catch (Exception e) {
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dateIni);
        return cal1;
    }
    public int getAllPlannedExercises(Date dateIni , Date dateFin) {
        Calendar iniDate= castDate(dateIni);
        Calendar finDate= castDate(dateFin);
        int number = plannedExerciseRepository.getPlannedExercise(iniDate, finDate);
        return number;
    }
    public int getCheckedPlannedExercises(Date dateIni , Date dateFin) {
        Calendar iniDate= castDate(dateIni);
        Calendar finDate= castDate(dateFin);
        int number = plannedExerciseRepository.getCheckedPlannedExercise(iniDate, finDate);
        return number;
    }
}
