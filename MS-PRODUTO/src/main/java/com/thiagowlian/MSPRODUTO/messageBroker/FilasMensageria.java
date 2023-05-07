package com.thiagowlian.MSPRODUTO.messageBroker;

public class FilasMensageria {

    public final static String PRODUTO_REDUZIR_ESTOQUE_QUEUE = "produtos.v1.reduzir-estoque";
    public final static String VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.realizar-vendas";
    public final static String PRODUTO_DIMINUIR_ESTOQUE_ROUTING_KEY = "diminuir-estoque";
    public final static String VENDAS_FEEDBACK_CHOREOGRAPHY_QUEUE = "vendas.v1.feedback";
    public final static String VENDA_FEEDBACK_CHOREOGRAPHY_ROUTING_KEY = "feedback-realizar-venda";
    public final static String PRODUDO_NOVO_EXCHANGE = "produto.v1.novo";
    public final static String PRODUDO_UPDATE_EXCHANGE = "produto.v1.novo";
    public final static String PRODUTO_NOVO_QUERY_TABLE_QUEUE = "produto.v1.novo.querytable";
    public final static String PRODUTO_UPDATE_QUERY_TABLE_QUEUE = "produto.v1.novo.querytable";
    public final static String NULL_ROUNTING_KEY = "";
}