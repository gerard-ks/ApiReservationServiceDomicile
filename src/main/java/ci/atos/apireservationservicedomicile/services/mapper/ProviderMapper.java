package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.Provider;
import ci.atos.apireservationservicedomicile.services.dto.ProviderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {
}
