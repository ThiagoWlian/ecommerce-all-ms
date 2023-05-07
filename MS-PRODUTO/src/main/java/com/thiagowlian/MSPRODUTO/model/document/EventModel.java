package com.thiagowlian.MSPRODUTO.model.document;

import com.thiagowlian.MSPRODUTO.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
@NoArgsConstructor
public class EventModel {

    @Id
    private String id;
    private LocalDateTime eventDate = LocalDateTime.now();
    private EventType eventType;
    private Object content;

    public EventModel(EventType eventType, Object content) {
        this.eventType = eventType;
        this.content = content;
    }
}

