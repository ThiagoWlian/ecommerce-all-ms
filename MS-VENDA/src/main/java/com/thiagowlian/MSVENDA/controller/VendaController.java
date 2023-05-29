package com.thiagowlian.MSVENDA.controller;

import com.thiagowlian.MSVENDA.dto.ResponseMessageDto;
import com.thiagowlian.MSVENDA.dto.VendaForm;
import com.thiagowlian.MSVENDA.model.VendaModel;
import com.thiagowlian.MSVENDA.service.VendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity realizarVenda(@RequestBody VendaForm vendaForm) {
        try {
            List<Double> valorProdutos = vendaForm.getListDesconto();
            List<String> codigosBarra = vendaForm.getListProdutoId();
            VendaModel venda = vendaService.cadastrarVenda(new VendaModel(codigosBarra, vendaForm.desconto(), valorProdutos));
            return ResponseEntity.created(URI.create("/venda/" + venda.getId())).build();
        } catch (AmqpException ampqEx) {
            log.error("Ocorreu um erro na criação do evento na fila: " + ampqEx.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseMessageDto(ampqEx.getMessage()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(new ResponseMessageDto(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarVendaPorId(@PathVariable long id) {
        try {
            Optional<VendaModel> optionalVendaModel = vendaService.findById(id);
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
            List<VendaModel> listVendaModel = vendaService.findAll();
            if (listVendaModel.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(listVendaModel);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
