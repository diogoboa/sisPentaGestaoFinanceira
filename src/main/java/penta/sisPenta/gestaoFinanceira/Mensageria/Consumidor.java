package penta.sisPenta.gestaoFinanceira.Mensageria;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.EntityNotFoundException;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.FalhaAoSerializarMensagem;
import penta.sisPenta.gestaoFinanceira.Exceptions.Custom.UnprocessableEntity;
import penta.sisPenta.gestaoFinanceira.Model.Dto.Empresa.EmpresaSimplesPOST;

import java.io.IOException;

@Component
public class Consumidor {


    @RabbitListener(queues = RabbitMQAdmin.QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE, ackMode = "MANUAL")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void consumirMensagem(Message mensagem, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {


        try {
            EmpresaSimplesPOST empresaSimplesPOST = new ObjectMapper().readValue(mensagem.getBody(), EmpresaSimplesPOST.class);
            channel.basicAck(deliveryTag, false); //mensagem processada com sucesso

            System.out.println("OK mensagem processada");

        }catch (Exception ex_serializar)
        {
            System.out.println("ERRO AO RECEBER MENSAGEM");
            try {
                channel.basicReject(deliveryTag, false); //nao quero mais ler a mensagem,
                //channel.basicNack(deliveryTag, false, true); //nao quero mais ler a mensagem, mas quero reler-novamente

            }catch (Exception ex_leitura)
            {

            }
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly(); //forcar rollback

        }


    }






}