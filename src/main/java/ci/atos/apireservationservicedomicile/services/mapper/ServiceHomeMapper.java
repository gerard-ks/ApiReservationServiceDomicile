package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.ServiceHome;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeDTO;
import org.mapstruct.Mapper;;

@Mapper(componentModel = "spring")
public interface ServiceHomeMapper extends EntityMapper<ServiceHomeDTO, ServiceHome> {
}
