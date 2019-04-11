package empresa.software.tiendaonline;

import empresa.software.tiendaonline.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class TiendaonlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaonlineApplication.class, args);
	}

}
