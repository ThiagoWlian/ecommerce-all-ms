package com.thiagowlian.MSVENDA.service;

import com.thiagowlian.MSVENDA.model.StatusVenda;
import com.thiagowlian.MSVENDA.model.VendaModel;
import com.thiagowlian.MSVENDA.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public VendaModel cadastrarVenda(VendaModel vendaModel) {
        return vendaRepository.save(vendaModel);
    }

    public void atualizarStatusVenda(long vendaId, StatusVenda statusVenda){
        Optional<VendaModel> venda = vendaRepository.findById(vendaId);
        if (venda.isPresent()) {
            venda.get().setStatusVenda(statusVenda);
        }
    }
}
