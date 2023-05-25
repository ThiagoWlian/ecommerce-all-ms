package com.thiagowlian.MSVENDA.messageBroker.listener;

import com.thiagowlian.MSVENDA.dto.VendaFeedbackDto;
import com.thiagowlian.MSVENDA.dto.VendaFeedbackErrorDto;
import com.thiagowlian.MSVENDA.dto.VendaFeedbackSuccessDto;
import com.thiagowlian.MSVENDA.model.StatusVenda;
import com.thiagowlian.MSVENDA.service.VendaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSVENDA.messageBroker.FilasMensageria.*;

@Component
public class VendaFeedbackListener {

    @Autowired
    private VendaService vendaService;

    @RabbitListener(queues = VENDA_FEEDBACK_ERRO_QUEUE)
    public void onVendaFeedbackCreated(VendaFeedbackErrorDto vendaFeedbackDto) {
        vendaService.atualizarStatusVenda(vendaFeedbackDto.vendaId(), StatusVenda.ERRO);
    }

    @RabbitListener(queues = VENDA_FEEDBACK_SUCESSO_QUEUE)
    public void onVendaFeedbackCreated(VendaFeedbackSuccessDto vendaFeedbackDto) {
        vendaService.atualizarStatusVenda(vendaFeedbackDto.vendaId(), StatusVenda.CONCLUIDA);
    }
}
