package com.thiagowlian.MSPRODUTO.repository;

import com.thiagowlian.MSPRODUTO.model.ProdutoModel;
import com.thiagowlian.MSPRODUTO.model.ProdutoTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ProdutoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("simpleJdbcInsertProduto")
    SimpleJdbcInsert simpleJdbcInsert;

    public void insert(ProdutoModel produtoModel) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("codigo_barra", produtoModel.getCodigoBarras());
        parameters.put("nome", produtoModel.getNome());
        parameters.put("valor", produtoModel.getValor());
        parameters.put("estoque", produtoModel.getEstoque());
        parameters.put("tipo", produtoModel.getProdutoTipo().toString());
        simpleJdbcInsert.execute(parameters);
    }

    public void update(ProdutoModel produtoModel) {
        jdbcTemplate.update("UPDATE PRODUTO SET nome = ?, tipo = ?, valor = ?, estoque = ? WHERE codigo_barra = ?",
                produtoModel.getNome(),
                produtoModel.getProdutoTipo().toString(),
                produtoModel.getValor(),
                produtoModel.getEstoque(),
                produtoModel.getCodigoBarras()
        );
    }

    public List<ProdutoModel> findAll(){
        return jdbcTemplate.query("SELECT * FROM PRODUTO", new BeanPropertyRowMapper(ProdutoModel.class));
    }

    public List<ProdutoModel> findAllByCodigosBarra(List<String> codigosBarra){
        return jdbcTemplate.query("SELECT codigo_barra, nome, tipo, valor, estoque FROM PRODUTO WHERE codigo_barra IN (?)", (rs, rowNum) -> {
                    ProdutoModel produtoModel = new ProdutoModel();
                    produtoModel.setCodigoBarras(rs.getString("codigo_barra"));
                    produtoModel.setNome(rs.getString("nome"));
                    produtoModel.setProdutoTipo(ProdutoTipo.valueOf(rs.getString("tipo")));
                    produtoModel.setValor(rs.getDouble("valor"));
                    produtoModel.setEstoque(rs.getLong("estoque"));
                    return produtoModel;
                },
                codigosBarra.toArray());
    }

    public Optional<ProdutoModel> findByCodigoBarra(String codigoBarra){
        ProdutoModel produtoModel = (ProdutoModel) jdbcTemplate.queryForObject("SELECT * FROM PRODUTO where codigoBarra = ?", new BeanPropertyRowMapper(ProdutoModel.class), codigoBarra);
        return Optional.of(produtoModel);
    }
}
