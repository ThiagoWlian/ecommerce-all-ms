package com.thiagowlian.MSPRODUTO.controller;

import com.thiagowlian.MSPRODUTO.dto.ProdutoDto;
import com.thiagowlian.MSPRODUTO.dto.ProdutoForm;
import com.thiagowlian.MSPRODUTO.dto.ReduzirEstoqueForm;
import com.thiagowlian.MSPRODUTO.hateoas.assembler.ProdutoAssembler;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
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
    private ProdutoAssembler produtoAssembler;

    @PostMapping
    public ResponseEntity criarNovoProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        try {
            ProdutoModel produto = new ProdutoModel(produtoForm);
            ProdutoModel produtoPersistido = produtoService.cadastrarProduto(produto);
            ProdutoDto produtoDto = produtoAssembler.toModel(produtoPersistido);

            return ResponseEntity.created(URI.create(String.format("/produto/%s", produtoDto.getId()))).body(produtoDto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ResponseMessageTo(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @PatchMapping("/reduzirEstoque")
    public ResponseEntity reduzirEstoqueGrupo(@RequestBody @Valid List<ReduzirEstoqueForm> ids) {
        try {
            List<ProdutoModel> produtoModel = produtoService.buscarProdutoPorListaId(ids.stream()
                    .map(ReduzirEstoqueForm::id)
                    .collect(Collectors.toList()));

            if (!produtoModel.isEmpty()) {
                produtoService.reduzirEstoqueProduto(produtoModel);
                return ResponseEntity.ok().build();
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

    @GetMapping("/{id}")
    public ResponseEntity buscarProdutoEspecifico(@PathVariable long id) {
        try {
            Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);
            if (produto.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(produto);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(new ResponseMessageTo(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProdutoPorId(@PathVariable long id){
        try {
            Optional<ProdutoModel> produto = produtoService.buscarProdutoPorId(id);
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
