package ci.atos.apireservationservicedomicile;

import ci.atos.apireservationservicedomicile.models.Role;
import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.repositories.RoleRepository;
import ci.atos.apireservationservicedomicile.repositories.UserRepository;
import ci.atos.apireservationservicedomicile.services.RoleService;
import ci.atos.apireservationservicedomicile.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiReservationServiceDomicileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiReservationServiceDomicileApplication.class, args);
    }
}
