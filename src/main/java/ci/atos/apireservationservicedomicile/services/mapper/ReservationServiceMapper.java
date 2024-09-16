package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.ReservationService;
import ci.atos.apireservationservicedomicile.services.dto.ReservationServiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationServiceMapper extends EntityMapper<ReservationServiceDTO, ReservationService> {
}
