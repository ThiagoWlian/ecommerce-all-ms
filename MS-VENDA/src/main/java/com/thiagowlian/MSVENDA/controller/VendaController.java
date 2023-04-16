package com.thiagowlian.MSVENDA.controller;

import com.thiagowlian.MSVENDA.dto.ReducaoEstoqueDto;
import com.thiagowlian.MSVENDA.dto.ResponseMessageTo;
import com.thiagowlian.MSVENDA.dto.VendaForm;
import com.thiagowlian.MSVENDA.messageProducer.ProdutoMessageProducer;
import com.thiagowlian.MSVENDA.model.VendaModel;
import com.thiagowlian.MSVENDA.repository.VendaRepository;
import com.thiagowlian.MSVENDA.service.VendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoMessageProducer produtoMessageProducer;

    @PostMapping
    public ResponseEntity realizarVenda(@RequestBody VendaForm vendaForm) {
        try {
            List<Double> valorProdutos = vendaForm.getListDesconto();
            List<Long> produtosId = vendaForm.getListProdutoId();
            VendaModel venda = vendaService.cadastrarVenda(new VendaModel(produtosId, vendaForm.desconto(), valorProdutos));

            ReducaoEstoqueDto reducaoEstoqueDtos = new ReducaoEstoqueDto(venda.getId(), produtosId);
            produtoMessageProducer.producerReducaoEstoque(reducaoEstoqueDtos);
            return ResponseEntity.created(URI.create("/venda/" + venda.getId())).build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarVendaPorId(@PathVariable long id) {
        try {
            Optional<VendaModel> optionalVendaModel = vendaRepository.findById(id);
            if (optionalVendaModel.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(optionalVendaModel);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity buscarVendaPorId() {
        try {
            List<VendaModel> listVendaModel = vendaRepository.findAll();
            if (listVendaModel.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(listVendaModel);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
