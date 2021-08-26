package com.company.planner.service;

import com.company.planner.entity.Session;

import java.time.LocalDateTime;

public interface SessionService {
    String NAME = "planner_SessionService";

    Session rescheduleSession(Session session, LocalDateTime newStartDate);

    String showSomething();
}