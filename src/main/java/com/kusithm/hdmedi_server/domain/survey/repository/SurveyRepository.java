package com.kusithm.hdmedi_server.domain.survey.repository;

import com.kusithm.hdmedi_server.domain.survey.domain.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SurveyRepository extends MongoRepository<Survey, String> {
    Optional<Survey> findByUserId(Long userId);
}
