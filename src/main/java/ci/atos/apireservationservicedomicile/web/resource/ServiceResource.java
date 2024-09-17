package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.ServiceHomeService;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeCategoryRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeDTO;
import ci.atos.apireservationservicedomicile.services.dto.ServiceHomeRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeCategoryNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeException;
import ci.atos.apireservationservicedomicile.web.exception.ServiceHomeNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceResource {

    private final ServiceHomeService serviceHomeService;

    public ServiceResource(ServiceHomeService serviceHomeService) {
        this.serviceHomeService = serviceHomeService;
    }

    @PostMapping
    public ResponseEntity<ServiceHomeDTO> createService(@Valid @RequestBody ServiceHomeRequestDTO serviceDTO) throws ServiceHomeCategoryNotFoundException, MyProviderNotFoundException {
            ServiceHomeDTO createdService = serviceHomeService.createService(serviceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceHomeDTO> getServiceById(@PathVariable Long id) throws ServiceHomeNotFoundException {
            ServiceHomeDTO service = serviceHomeService.findServiceById(id);
            return ResponseEntity.ok(service);
    }

    @GetMapping
    public ResponseEntity<List<ServiceHomeDTO>> getAllServices() {
        List<ServiceHomeDTO> services = serviceHomeService.findAllServices();
        return ResponseEntity.ok(services);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceHomeDTO> updateService(
            @PathVariable Long id, @RequestBody ServiceHomeRequestDTO serviceDTO) throws MyProviderNotFoundException, ServiceHomeNotFoundException {
            ServiceHomeDTO updatedService = serviceHomeService.updateService(id, serviceDTO);
            return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        try {
            serviceHomeService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (ServiceHomeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<ServiceHomeCategoryDTO> createServiceCategory(
            @Valid @RequestBody ServiceHomeCategoryRequestDTO categoryDTO) {
        ServiceHomeCategoryDTO createdCategory = serviceHomeService.createServiceCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Long id) throws ServiceHomeException {
            serviceHomeService.deleteServiceCategory(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ServiceHomeCategoryDTO>> getAllServiceCategories() {
        List<ServiceHomeCategoryDTO> categories = serviceHomeService.findAllServiceCategory();
        return ResponseEntity.ok(categories);
    }
}
