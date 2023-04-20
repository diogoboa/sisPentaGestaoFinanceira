package grupo.penta;



@SpringBootApplication
@EntityScan({"penta.sisPenta"})
@EnableJpaRepositories({"penta.sisPenta"})
@ComponentScan({"penta.sisPenta", "penta.sisPenta.Controller", "penta.sisPenta.Configuration", "penta.sisPenta.Reports"})
@EnableCaching
@Configuration
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

    }
}