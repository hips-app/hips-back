package com.hips.api.repositories;

import com.hips.api.responses.StatisticsPOJO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class StatisticsRepository {
    @PersistenceContext
    EntityManager em;

    static final String STATEMENT_SQLMAP = "Statement-SQL-Mapping";

    public List<StatisticsPOJO> getUserExerciseStatistics(int planId) {

        Query query =  em.createNativeQuery(
                "SELECT dsp.id as daily_sport_plan_id, dsp.date, COUNT(case when pe.checked = true then pe.id end) * 100.0/COUNT(pe.id) AS percentage FROM planned_exercise AS pe INNER JOIN daily_sport_plan AS dsp ON dsp.id = pe.daily_sport_plan_id  WHERE dsp.sport_plan_id = :planId GROUP BY dsp.id, dsp.date",
                STATEMENT_SQLMAP);
        return query.setParameter("planId", planId).getResultList();
    }

    @SqlResultSetMapping(name= STATEMENT_SQLMAP, classes = {
            @ConstructorResult(targetClass = StatisticsPOJO.class,
                    columns = {
                            @ColumnResult(name="daily_sport_plan_id",type = Integer.class),
                            @ColumnResult(name="date",type = GregorianCalendar.class),
                            @ColumnResult(name="percentage",type = Double.class)
                    }
            )
    }) @Entity class StatisticsP{@Id Integer id; GregorianCalendar date; Double percentage;} // <- workaround
    //@Query(value = "SELECT dsp.id as daily_sport_plan_id, dsp.date, COUNT(case when pe.checked = true then pe.id end) * 100.0/COUNT(pe.id) AS percentage FROM planned_exercise AS pe INNER JOIN daily_sport_plan AS dsp ON dsp.id = pe.daily_sport_plan_id  WHERE dsp.sport_plan_id = :planId GROUP BY dsp.id, dsp.date", nativeQuery = true)
    //Collection<StatisticsPOJO> getUserExerciseStatistics(@Param("planId") int planId);

    // SELECT dsp.id as daily_sport_plan_id, dsp.date, COUNT(case when pe.checked = true then pe.id end) * 100.0/COUNT(pe.id) AS percentage
    //FROM planned_exercise AS pe
    //INNER JOIN 	daily_sport_plan AS dsp
    //ON dsp.id = pe.daily_sport_plan_id
    //WHERE dsp.sport_plan_id = 1
    //GROUP BY dsp.id, dsp.date
}