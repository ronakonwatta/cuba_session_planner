package com.company.planner.service;

import com.company.planner.entity.Session;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service(SessionService.NAME)
public class SessionServiceBean implements SessionService {
    @Inject
    private DataManager dataManager;
    @Inject
    private Persistence persistence;

    @Override
    public Session rescheduleSession(Session session, LocalDateTime newStartDate) {
        LocalDateTime dateStart = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(8);
        LocalDateTime dayEnd = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(19);

        Long sessionSameTime = dataManager.loadValue("select from planner_sessions where" +
                "(s.startDate between :dayStart and :dayEnd )" +
                "and s.speaker =:speaker " +
                "and s.id <> :sessionId", Long.class)
                .parameter("dayStart", dateStart)
                .parameter("dayEnd", dayEnd)
                .parameter("speaker", session.getSpeaker())
                .parameter("sessionId", session.getId())
                .one();

        if (sessionSameTime >= 2) {
            return session;
        }
        session.setStartDate(newStartDate);
        return dataManager.commit(session);
    }

    private String getMaxSQL(){
        String sqlText = "SELECT MAX(ID) FROM PLANNER_SESSION";
        return sqlText;
    }

    @Override
    public String showSomething() {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Query query = em.createNativeQuery(getMaxSQL());

            String a = null;
            a = query.getFirstResult().toString();
            tx.commit();
            return a;
        }
        catch (Exception ex){
            System.out.println("22222222222222222222222222222222222222222222222222");
            System.out.println(ex);
            return "NO";
        }
    }


}