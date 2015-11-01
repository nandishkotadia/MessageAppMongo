package org.websocket.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.websocket.models.LastReadLog;

public interface LastReadLogRepository extends MongoRepository<LastReadLog, String> {

   LastReadLog findBySenderUsernameAndReceiverUsername(String senderUsername,String receiverUsername);

}
