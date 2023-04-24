package penta.sisPenta.gestaoFinanceira.Mensageria;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;

import java.nio.channels.Channel;

@Component
public class Consumidor {


   @RabbitListener(queues = RabbitMQAdmin.QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE)
    public void clienteSugerido(Empresa empresa, Channel channel, Message message) {

        System.out.println("opa leitura: " + empresa.getRazaoSocial());


        //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);



    }


}