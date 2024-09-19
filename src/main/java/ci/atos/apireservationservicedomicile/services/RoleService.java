package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.RoleDTO;
import ci.atos.apireservationservicedomicile.services.dto.RoleRequestDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleRequestDTO role);
    List<RoleDTO> getAllRoles();
}
