package com.example.lifters.services;

import com.example.lifters.models.SessionModel;
import com.example.lifters.repositories.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public SessionModel getCurrentSession() {
        return sessionRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("No active session found"));
    }

    @Transactional
    public void openSession() {
        SessionModel session = new SessionModel();
        session.openSession();
        sessionRepository.save(session);
    }

    @Transactional
    public void closeSession() {
        SessionModel session = getCurrentSession();
        session.closeSession();
        sessionRepository.save(session);
    }
}
