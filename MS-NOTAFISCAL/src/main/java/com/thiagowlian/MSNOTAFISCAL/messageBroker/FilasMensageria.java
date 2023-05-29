package com.thiagowlian.MSNOTAFISCAL.messageBroker;

public class FilasMensageria {
    public final static String VENDA_FEEDBACK_QUEUE = "nota-fiscal.v1.realizar-venda.transaction.erro";
    public final static String VENDA_FEEDBACK_EXCHANGE = "venda.v1.venda-feedback";
    public final static String VENDA_FEEDBACK_ERRO_ROUTING_KEY = "venda.v1.venda-transaction.erro";
    public final static String VENDA_FEEDBACK_SUCESSO_ROUTING_KEY = "venda.v1.venda-transaction.sucesso";
    public final static String REALIZAR_VENDA_REDUCAO_ESTOQUE_TRANSACTION = "nota-fiscal.v1.venda-transaction.reducao-estoque";
    public final static String VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_EXCHANGE = "produto.v1.venda-realizada.estoque-reduzido";
    public final static String VENDA_REALIZADA_PRODUDO_REDUCAO_ESTOQUE_ROUTING_KEY = "nota-fiscal.v1.venda-realizada.estoque-reduzido";
    public final static String VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.realizar-vendas";
}
