package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Provider;
import ci.atos.apireservationservicedomicile.models.ServiceHome;
import ci.atos.apireservationservicedomicile.models.ServiceHomeCategory;
import ci.atos.apireservationservicedomicile.repositories.ProviderRepository;
import ci.atos.apireservationservicedomicile.repositories.ServiceHomeCategoryRepository;
import ci.atos.apireservationservicedomicile.repositories.ServiceHomeRepository;
import ci.atos.apireservationservicedomicile.services.ServiceHomeService;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.ServiceHomeCategoryMapper;
import ci.atos.apireservationservicedomicile.services.mapper.ServiceHomeMapper;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeCategoryNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ServiceHomeServiceImpl implements ServiceHomeService {

    private final ServiceHomeRepository serviceHomeRepository;
    private final ServiceHomeMapper serviceHomeMapper;
    private final ServiceHomeCategoryRepository serviceHomeCategoryRepository;
    private final ServiceHomeCategoryMapper serviceHomeCategoryMapper;
    private final ProviderRepository providerRepository;

    public ServiceHomeServiceImpl(ServiceHomeRepository serviceHomeRepository, ServiceHomeMapper serviceHomeMapper, ServiceHomeCategoryRepository serviceHomeCategoryRepository, ServiceHomeCategoryMapper serviceHomeCategoryMapper, ProviderRepository providerRepository) {
        this.serviceHomeRepository = serviceHomeRepository;
        this.serviceHomeMapper = serviceHomeMapper;
        this.serviceHomeCategoryRepository = serviceHomeCategoryRepository;
        this.serviceHomeCategoryMapper = serviceHomeCategoryMapper;
        this.providerRepository = providerRepository;
    }

    @Override
    public ServiceHomeDTO findServiceById(Long id) throws ServiceHomeNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        return serviceHomeRepository.findById(id).map(serviceHomeMapper::toDto).orElseThrow(()-> new ServiceHomeNotFoundException("Service " + id + " not found"));
    }

    @Override
    public ServiceHomeDTO createService(ServiceHomeRequestDTO serviceDTO) throws MyProviderNotFoundException, ServiceHomeCategoryNotFoundException {

        Provider existProvider = providerRepository.findById(serviceDTO.getProviderId()).orElseThrow(()-> new MyProviderNotFoundException("Le prestataire n'existe pas " + serviceDTO.getProviderId()));
        ServiceHomeCategory existServiceHomeCategory = serviceHomeCategoryRepository.findById(serviceDTO.getServiceCategoryId()).orElseThrow(()-> new ServiceHomeCategoryNotFoundException("La categorie n'existe pas " + serviceDTO.getServiceCategoryId()));

        ServiceHome serviceHome = getServiceHome(serviceDTO, existServiceHomeCategory, existProvider);

        ServiceHome savedServiceHome = serviceHomeRepository.save(serviceHome);

        return serviceHomeMapper.toDto(savedServiceHome);
    }

    private static ServiceHome getServiceHome(ServiceHomeRequestDTO serviceDTO, ServiceHomeCategory existServiceHomeCategory, Provider existProvider) {
        ServiceHome serviceHome = new ServiceHome();
        serviceHome.setName(serviceDTO.getName());
        serviceHome.setDescription(serviceDTO.getDescription());
        serviceHome.setPrice(serviceDTO.getPrice());
        serviceHome.setCategory(existServiceHomeCategory);
        serviceHome.setProvider(existProvider);
        return serviceHome;
    }

    @Override
    public ServiceHomeDTO updateService(Long id, ServiceHomeRequestDTO serviceDTO) throws ServiceHomeNotFoundException, MyProviderNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        ServiceHome serviceHome = serviceHomeRepository.findById(id).orElseThrow(()-> new ServiceHomeNotFoundException("Service " + id + " not found"));

        if (serviceDTO.getName() != null) {
            serviceHome.setName(serviceDTO.getName());
        }

        if (serviceDTO.getDescription() != null) {
            serviceHome.setDescription(serviceDTO.getDescription());
        }

        if (serviceDTO.getPrice() != null) {
            serviceHome.setPrice(serviceDTO.getPrice());
        }

        if (serviceDTO.getProviderId() != null) {
            Provider existProvider = providerRepository.findById(serviceDTO.getProviderId()).orElseThrow(()-> new MyProviderNotFoundException("Le prestataire n'existe pas " + serviceDTO.getProviderId()));
            serviceHome.setProvider(existProvider);
        }

        ServiceHome updated = serviceHomeRepository.save(serviceHome);

        return serviceHomeMapper.toDto(updated);
    }

    @Override
    public void deleteService(Long id) throws ServiceHomeNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Optional<ServiceHome> serviceHomeOptional = serviceHomeRepository.findById(id);

        if (serviceHomeOptional.isEmpty()) {
            throw new ServiceHomeNotFoundException("Service " + id + " not found");
        }

        serviceHomeRepository.deleteById(id);
    }

    @Override
    public List<ServiceHomeDTO> findAllServices() {
        return serviceHomeRepository.findAll()
                .stream()
                .map(serviceHomeMapper::toDto)
                .toList();
    }

    @Override
    public ServiceHomeCategoryDTO createServiceCategory(ServiceHomeCategoryRequestDTO serviceHomeCategoryRequestDTO) {
        ServiceHomeCategory serviceHomeCategory = new ServiceHomeCategory();
        serviceHomeCategory.setName(serviceHomeCategoryRequestDTO.getName());
        serviceHomeCategory.setDescription(serviceHomeCategoryRequestDTO.getDescription());
        ServiceHomeCategory saved = serviceHomeCategoryRepository.save(serviceHomeCategory);
        return serviceHomeCategoryMapper.toDto(saved);
    }

    @Override
    public void deleteServiceCategory(Long id) throws ServiceHomeException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        List<ServiceHome> serviceHomes = serviceHomeRepository.findByCategoryId(id);

        if(!serviceHomes.isEmpty()) throw new ServiceHomeException("Impossible de supprimer une catégorie avec les services associés.");

        serviceHomeCategoryRepository.deleteById(id);
    }

    @Override
    public List<ServiceHomeCategoryDTO> findAllServiceCategory() {
        return serviceHomeCategoryRepository.findAll()
                .stream()
                .map(serviceHomeCategoryMapper::toDto)
                .toList();
    }
}
