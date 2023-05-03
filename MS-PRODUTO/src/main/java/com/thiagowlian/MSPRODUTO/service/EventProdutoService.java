package com.thiagowlian.MSPRODUTO.service;

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

    public EventModel cadastrarProduto(ProdutoModel produto) {
        return eventRepository.insert(new EventModel(EventType.NOVO_PRODUTO, produto));
    }
}
