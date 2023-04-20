package penta.sisPenta.gestaoFinanceira.Mensageria;



import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controlador {




  /*  @Autowired
    private RabbitTemplate rabbitTemplate;

*/

    @PostMapping("/enviarMensagem")
    public String enviarMensagem() {


        return "ok";

    }




}
