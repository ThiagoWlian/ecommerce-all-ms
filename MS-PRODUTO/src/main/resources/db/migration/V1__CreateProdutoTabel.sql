CREATE TABLE PRODUTO(
    codigo_barra varchar(12),
    nome varchar(80),
    tipo varchar(20),
    valor decimal,
    estoque int,
    PRIMARY KEY (codigo_barra)
)