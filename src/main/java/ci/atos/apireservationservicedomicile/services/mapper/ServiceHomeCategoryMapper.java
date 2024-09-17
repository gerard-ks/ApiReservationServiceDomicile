package ci.atos.apireservationservicedomicile.services.mapper;

import ci.atos.apireservationservicedomicile.models.ServiceHomeCategory;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceHomeCategoryMapper extends EntityMapper<ServiceHomeCategoryDTO, ServiceHomeCategory> {
}
