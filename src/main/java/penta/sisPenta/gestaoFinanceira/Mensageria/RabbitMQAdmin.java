package penta.sisPenta.gestaoFinanceira.Mensageria;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQAdmin {

    private static final String host = "127.0.0.1";
    private static final  Integer port = 5672;
    private static final  String login = "super";
    private static final  String password = "super";
    public static final  String EXCHANGE_NAME_SISPENTA = "sispenta.v1";


    public static final  String ROUTING_KEY_SISPENTA_FILA_SUGESTAO_CADASTRO_CLIENTE = "fila.v1.sugestao.cadastro.cliente";
    public static final  String QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE = "queue_v1_sugestao_cadastro_cliente";


    public static final  String ROUTING_KEY_SISPENTA_FILA_VENDAS_ENTRADA = "fila.v1.vendas.entrada";
    public static final  String QUEUE_NAME_VENDAS_ENTRADA = "queue_v1_vendas_entrada";



    public static final  String ROUTING_KEY_SISPENTA_FILA_VENDAS_CONCILIACAO = "fila.v1.vendas.conciliacao";
    public static final  String QUEUE_NAME_VENDAS_CONCILIACAO = "queue_v1_vendas_conciliacao";




    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(login);
        connectionFactory.setPassword(password);


        return connectionFactory;
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory)
    {
        RabbitAdmin rabbitAdmin =  new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE_NAME_SISPENTA));

        return rabbitAdmin;
    }


    @Bean
    public Queue queueSugerirCadastroDeCliente()
    {
        Queue q = new Queue(QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE, true);
        return q;
    }

    @Bean
    public Queue queueVendasEntrada()
    {
        Queue q = new Queue(QUEUE_NAME_VENDAS_ENTRADA, true);

        return q;
    }


    @Bean
    public Queue queueVendasConciliacao()
    {
        Queue q = new Queue(QUEUE_NAME_VENDAS_CONCILIACAO, true);
        return q;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());

        //criar exchanges!!!
        rabbitTemplate.setExchange(EXCHANGE_NAME_SISPENTA);

        return rabbitTemplate;
    }



    @Bean
    public Binding bindingSugerirCadastroDeCliente()
    {
        Exchange exchange = new FanoutExchange(EXCHANGE_NAME_SISPENTA);
        Queue queue = new Queue(QUEUE_NAME_SUGESTAO_CADASTRO_CLIENTE, true, false, false);

        return BindingBuilder.bind(queueSugerirCadastroDeCliente()).to(exchange).with(ROUTING_KEY_SISPENTA_FILA_SUGESTAO_CADASTRO_CLIENTE).noargs();
    }


    @Bean
    public Binding bindingVendasEntrada()
    {
        Exchange exchange = new FanoutExchange(EXCHANGE_NAME_SISPENTA);

        return BindingBuilder.bind(queueVendasEntrada()).to(exchange).with(ROUTING_KEY_SISPENTA_FILA_VENDAS_ENTRADA).noargs();
    }


    @Bean
    public Binding bindingVendasConciliacao()
    {
        Exchange exchange = new FanoutExchange(EXCHANGE_NAME_SISPENTA);
        return BindingBuilder.bind(queueVendasConciliacao()).to(exchange).with(ROUTING_KEY_SISPENTA_FILA_VENDAS_CONCILIACAO).noargs();
    }



}
