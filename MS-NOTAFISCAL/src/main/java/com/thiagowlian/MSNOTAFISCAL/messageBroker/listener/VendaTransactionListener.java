package com.thiagowlian.MSNOTAFISCAL.messageBroker.listener;

import com.thiagowlian.MSNOTAFISCAL.csv.GerarCsvVenda;
import com.thiagowlian.MSNOTAFISCAL.dto.ProdutoNfDto;
import com.thiagowlian.MSNOTAFISCAL.dto.ReducaoEstoqueTransactionEventDto;
import com.thiagowlian.MSNOTAFISCAL.dto.VendaFeedbackErrorDto;
import com.thiagowlian.MSNOTAFISCAL.dto.VendaFeedbackSuccessDto;
import com.thiagowlian.MSNOTAFISCAL.messageBroker.producer.VendaTransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.thiagowlian.MSNOTAFISCAL.messageBroker.FilasMensageria.REALIZAR_VENDA_REDUCAO_ESTOQUE_TRANSACTION;

@Slf4j
@Component
public class VendaTransactionListener {

    @Autowired
    private GerarCsvVenda gerarCsvVenda;

    @Autowired
    private VendaTransactionProducer vendaTransactionProducer;

    @RabbitListener(queues = REALIZAR_VENDA_REDUCAO_ESTOQUE_TRANSACTION)
    public void onEstoqueListenerCreated(ReducaoEstoqueTransactionEventDto reducaoEstoqueTransactionEventDto) {
        try {
            gerarCsvVenda.gerarArquivoCsv(String.format("nfe_venda_%s.csv", reducaoEstoqueTransactionEventDto.vendaId()), new HashSet<>(reducaoEstoqueTransactionEventDto.produtoDtos()));
            vendaTransactionProducer.producerVendaFeedbackSuccess(new VendaFeedbackSuccessDto(reducaoEstoqueTransactionEventDto.vendaId(), reducaoEstoqueTransactionEventDto.produtoDtos()));
        } catch (IOException ex) {
            log.error(String.format("Erro na transação da venda %s. Erro: %s", reducaoEstoqueTransactionEventDto.vendaId(), ex.getMessage()));
            vendaTransactionProducer.producerVendaFeedbackError(new VendaFeedbackErrorDto(reducaoEstoqueTransactionEventDto.vendaId()));
        }
    }
}
