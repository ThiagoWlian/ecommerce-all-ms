package com.thiagowlian.MSVENDA.messageProducer;

public class FilasMensageria {

    public final static String PRODUTO_REDUZIR_ESTOQUE_QUEUE = "produtos.v1.reduzir-estoque";
    public final static String VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.realizar-vendas";
    public final static String PRODUTO_DIMINUIR_ESTOQUE_ROUTING_KEY = "diminuir-estoque";

    public final static String VENDAS_FEEDBACK_CHOREOGRAPHY_QUEUE = "vendas.v1.feedback";
    public final static String VENDA_FEEDBACK_CHOREOGRAPHY_ROUTING_KEY = "feedback-realizar-venda";
}
