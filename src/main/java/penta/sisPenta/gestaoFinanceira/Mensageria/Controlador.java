package penta.sisPenta.gestaoFinanceira.Mensageria;



import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controlador {




  /*  @Autowired
    private RabbitTemplate rabbitTemplate;

*/

    @GetMapping("/api/enviarMensagem")

    public String enviarMensagem() {


        return "ok";

    }




}
