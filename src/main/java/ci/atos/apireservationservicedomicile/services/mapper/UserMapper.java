package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.services.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
