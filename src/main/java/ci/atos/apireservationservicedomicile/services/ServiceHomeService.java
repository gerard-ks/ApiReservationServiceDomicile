package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeCategoryNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeNotFoundException;

import java.util.List;

public interface ServiceHomeService {
    ServiceHomeDTO findServiceById(Long id) throws ServiceHomeNotFoundException;
    ServiceHomeDTO createService(ServiceHomeRequestDTO serviceDTO) throws MyProviderNotFoundException, ServiceHomeCategoryNotFoundException;
    ServiceHomeDTO updateService(Long id, ServiceHomeRequestDTO serviceDTO) throws ServiceHomeNotFoundException, MyProviderNotFoundException;
    void deleteService(Long id) throws ServiceHomeNotFoundException;
    List<ServiceHomeDTO> findAllServices();
    ServiceHomeCategoryDTO createServiceCategory(ServiceHomeCategoryRequestDTO serviceHomeCategoryRequestDTO);
    void deleteServiceCategory(Long id) throws ServiceHomeException;
    List<ServiceHomeCategoryDTO> findAllServiceCategory();
}
