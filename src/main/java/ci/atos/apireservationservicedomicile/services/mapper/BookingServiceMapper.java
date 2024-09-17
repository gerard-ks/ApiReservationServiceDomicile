package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.BookingServiceModel;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingServiceMapper extends EntityMapper<BookingServiceDTO, BookingServiceModel> {
}
