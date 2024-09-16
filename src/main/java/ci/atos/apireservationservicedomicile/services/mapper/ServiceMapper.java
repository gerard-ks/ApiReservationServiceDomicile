package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.services.dto.ServiceDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends EntityMapper<ServiceDTO, Service> {
}
