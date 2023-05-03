package com.thiagowlian.MSVENDA.service;

import com.thiagowlian.MSVENDA.dto.ReducaoEstoqueDto;
import com.thiagowlian.MSVENDA.messageBroker.producer.VendaMessageProducer;
import com.thiagowlian.MSVENDA.model.StatusVenda;
import com.thiagowlian.MSVENDA.model.VendaModel;
import com.thiagowlian.MSVENDA.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaMessageProducer vendaMessageProducer;

    @Transactional
    public VendaModel cadastrarVenda(VendaModel vendaModel) {
        VendaModel venda = vendaRepository.save(vendaModel);
        vendaMessageProducer.producerVendaRealizada(new ReducaoEstoqueDto(venda.getId(), venda.getProdutosId()));
        return venda;
    }

    public void atualizarStatusVenda(long vendaId, StatusVenda statusVenda) {
        Optional<VendaModel> venda = vendaRepository.findById(vendaId);
        if (venda.isPresent()) {
            venda.get().setStatusVenda(statusVenda);
        }
    }

    public Optional<VendaModel> findById(long id) {
        return vendaRepository.findById(id);
    }

    public List<VendaModel> findAll() {
        return vendaRepository.findAll();
    }
}
