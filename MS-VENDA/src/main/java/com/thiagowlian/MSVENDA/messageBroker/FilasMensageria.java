package com.thiagowlian.MSVENDA.messageBroker;

public class FilasMensageria {

    public final static String PRODUTO_REDUZIR_ESTOQUE_QUEUE = "produtos.v1.reduzir-estoque";
    public final static String VENDAS_REALIZADA_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.venda-realizada";
    public final static String VENDA_REALIZADA_ROUTING_KEY = "venda-realizada";
    public final static String VENDA_FEEDBACK_CHOREOGRAPHY_QUEUE = "vendas.v1.feedback";
    public final static String VENDA_FEEDBACK_CHOREOGRAPHY_ROUTING_KEY = "feedback-venda-realizada";
}
