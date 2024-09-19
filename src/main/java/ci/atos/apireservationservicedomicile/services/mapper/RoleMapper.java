package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.Role;
import ci.atos.apireservationservicedomicile.services.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
