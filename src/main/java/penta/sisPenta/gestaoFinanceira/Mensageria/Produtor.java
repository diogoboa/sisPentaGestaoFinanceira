package penta.sisPenta.gestaoFinanceira.Mensageria;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import penta.sisPenta.gestaoFinanceira.Model.Empresa;

import static penta.sisPenta.gestaoFinanceira.Mensageria.RabbitMQAdmin.EXCHANGE_NAME_SISPENTA;
import static penta.sisPenta.gestaoFinanceira.Mensageria.RabbitMQAdmin.ROUTING_KEY_SISPENTA_FILA_SUGESTAO_CADASTRO_CLIENTE;


@Component
public class Produtor {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public Empresa produzirSugestaoDeCliente(Empresa empresa)
    {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME_SISPENTA, ROUTING_KEY_SISPENTA_FILA_SUGESTAO_CADASTRO_CLIENTE, empresa);

        return empresa;

    }



}
