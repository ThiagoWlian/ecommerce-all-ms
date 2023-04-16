package com.thiagowlian.MSPRODUTO.hateoas.assembler;

import com.thiagowlian.MSPRODUTO.controller.ProdutoController;
import com.thiagowlian.MSPRODUTO.dto.ProdutoDto;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProdutoAssembler extends RepresentationModelAssemblerSupport<ProdutoModel, ProdutoDto> {

    public ProdutoAssembler() {
        super(ProdutoController.class, ProdutoDto.class);
    }

    @Override
    public ProdutoDto toModel(ProdutoModel entity) {
        ProdutoDto produtoDto = new ProdutoDto(entity);
        produtoDto.add(linkTo(methodOn(ProdutoController.class).buscarProdutoEspecifico(entity.getId())).withSelfRel());
        produtoDto.add(linkTo(methodOn(ProdutoController.class).deletarProdutoPorId(entity.getId())).withRel("delete"));
        return produtoDto;
    }

    @Override
    public CollectionModel<ProdutoDto> toCollectionModel(Iterable<? extends ProdutoModel> entities) {
        return  super.toCollectionModel(entities);
    }
}
