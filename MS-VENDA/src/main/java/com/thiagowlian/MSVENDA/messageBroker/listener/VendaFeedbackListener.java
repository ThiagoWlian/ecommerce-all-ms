package com.thiagowlian.MSVENDA.messageBroker.listener;

import com.thiagowlian.MSVENDA.dto.VendaFeedbackDto;
import com.thiagowlian.MSVENDA.model.StatusVenda;
import com.thiagowlian.MSVENDA.service.VendaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.thiagowlian.MSVENDA.messageBroker.FilasMensageria.VENDA_FEEDBACK_QUEUE;

@Component
public class VendaFeedbackListener {

    @Autowired
    private VendaService vendaService;

    @RabbitListener(queues = VENDA_FEEDBACK_QUEUE)
    public void onVendaFeedbackCreated(VendaFeedbackDto vendaFeedbackDto) {
        StatusVenda statusVenda = vendaFeedbackDto.revert() ? StatusVenda.ERRO : StatusVenda.CONCLUIDA;
        vendaService.atualizarStatusVenda(vendaFeedbackDto.vendaId(), statusVenda);
    }
}
