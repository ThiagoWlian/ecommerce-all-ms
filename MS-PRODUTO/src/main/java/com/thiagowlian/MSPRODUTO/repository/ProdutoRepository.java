package com.thiagowlian.MSPRODUTO.repository;

import com.thiagowlian.MSPRODUTO.dto.ProdutoDto;
import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    @Query("select new com.thiagowlian.MSPRODUTO.dto.ProdutoDto(p) from ProdutoModel p where p.id = :id")
    public ProdutoDto findProdutoDtoById(long id);

    @Query("select new com.thiagowlian.MSPRODUTO.dto.ProdutoDto(p) from ProdutoModel p")
    public List<ProdutoDto> findProdutoDtoAll();
}
