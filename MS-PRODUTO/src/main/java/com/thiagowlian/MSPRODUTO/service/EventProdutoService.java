package com.thiagowlian.MSPRODUTO.service;

import com.thiagowlian.MSPRODUTO.messageBroker.producer.ProdutoProducer;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.document.EventModel;
import com.thiagowlian.MSPRODUTO.model.document.EventType;
import com.thiagowlian.MSPRODUTO.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProdutoService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProdutoProducer produtoProducer;

    public EventModel cadastrarProduto(ProdutoModel produto) {
        EventModel event = eventRepository.insert(new EventModel(EventType.NOVO_PRODUTO, produto));
        produtoProducer.publishProdutoCreatedEvent(produto);
        return event;
    }
}
