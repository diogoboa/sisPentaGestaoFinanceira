package penta.sisPenta.gestaoFinanceira.Mensageria;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Empresa.EmpresaSimplesPOST;

@Component
public class Consumidor {


    @RabbitListener(queues = RabbitMQAdmin.QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE)
    public void consumirMensagem(@Payload EmpresaSimplesPOST mensagem) {
        // Lógica para processar a mensagem recebida da fila
        System.out.println("Mensagem recebida: " + mensagem);
    }


}