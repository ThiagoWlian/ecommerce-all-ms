package com.thiagowlian.MSPRODUTO.controller;

import com.thiagowlian.MSPRODUTO.dto.ProdutoDto;
import com.thiagowlian.MSPRODUTO.dto.ProdutoForm;
import com.thiagowlian.MSPRODUTO.dto.ReduzirEstoqueForm;
import com.thiagowlian.MSPRODUTO.hateoas.ProdutoAssembler;
import com.thiagowlian.MSPRODUTO.messageBroker.producer.ProdutoProducer;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.service.EventProdutoService;
import com.thiagowlian.MSPRODUTO.service.ProdutoService;
import com.thiagowlian.MSPRODUTO.to.ResponseMessageTo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EventProdutoService eventProdutoService;

    @Autowired
    private ProdutoProducer produtoProducer;

    @Autowired
    private ProdutoAssembler produtoAssembler;

    @PostMapping
    public ResponseEntity criarNovoProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        try {
            ProdutoModel produto = new ProdutoModel(produtoForm);
            eventProdutoService.cadastrarProduto(produto);
            ProdutoDto produtoDto = produtoAssembler.toModel(produto);

            return ResponseEntity.created(URI.create(String.format("/produto/%s", produtoDto.getCodigoBarras()))).body(produtoDto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessageTo(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @PatchMapping("/reduzirEstoque")
    public ResponseEntity reduzirEstoqueGrupo(@RequestBody @Valid List<ReduzirEstoqueForm> codigosBarra) {
        try {
            List<ProdutoModel> produtosList = produtoService.buscarProdutoPorListaCodigoBarra(codigosBarra.stream()
                    .map(ReduzirEstoqueForm::codigosBarra)
                    .collect(Collectors.toList()));

            if (!produtosList.isEmpty()) {
                produtoService.reduzirEstoqueProdutoEmUm(produtosList);
                return ResponseEntity.ok().build();
            }

            if (produtosList.size() != codigosBarra.size()) {
                return ResponseEntity.internalServerError().body(new ResponseMessageTo("Código de barras inexistente!"));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity buscarTodosProdutos() {
        try {
            List<ProdutoModel> produtos = produtoService.buscarTodosProdutos();
            CollectionModel<ProdutoDto> produtosDto = produtoAssembler.toCollectionModel(produtos);
            return ResponseEntity.ok().body(produtosDto.getContent());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @GetMapping("/{codigoBarra}")
    public ResponseEntity buscarProdutoEspecifico(@PathVariable String codigoBarra) {
        try {
            Optional<ProdutoModel> produto = produtoService.buscarProdutoPorCodigoBarra(codigoBarra);
            if (produto.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(produto);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @DeleteMapping("/{codigoBarra}")
    public ResponseEntity deletarProdutoPorId(@PathVariable String codigoBarra){
        try {
            Optional<ProdutoModel> produto = produtoService.buscarProdutoPorCodigoBarra(codigoBarra);
            if (produto.isPresent()) {
                produtoService.deletarproduto(produto.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }
}
