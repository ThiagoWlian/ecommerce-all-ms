package com.thiagowlian.MSPRODUTO.repository;

import com.thiagowlian.MSPRODUTO.model.document.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<EventModel, String> {
}
