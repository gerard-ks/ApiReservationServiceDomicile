package ci.atos.apireservationservicedomicile.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private RoleDTO role;
}
