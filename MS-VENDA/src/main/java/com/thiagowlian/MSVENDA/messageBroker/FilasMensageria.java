package com.thiagowlian.MSVENDA.messageBroker;

public class FilasMensageria {
    public final static String VENDA_REALIZADA_EXCHANGE = "venda.v1.venda-realizada";
    public final static String VENDAS_REALIZAR_VENDA_CHOREOGRAPHY_EXCHANGE = "vendas.v1.realizar-vendas";
    public final static String VENDA_REALIZADA_ROUTING_KEY = "venda-realizada";
    public final static String VENDA_FEEDBACK_ERRO_QUEUE = "vendas.v1.feedback.erro";
    public final static String VENDA_FEEDBACK_SUCESSO_QUEUE = "vendas.v1.feedback.sucesso";
    public final static String VENDA_FEEDBACK_ERRO_ROUTING_KEY = "venda.v1.venda-transaction.erro";
    public final static String VENDA_FEEDBACK_SUCESSO_ROUTING_KEY = "venda.v1.venda-transaction.sucesso";
    public final static String NULL_ROUNTING_KEY = "";
}
