package ci.atos.apireservationservicedomicile.web.resource;

import ci.atos.apireservationservicedomicile.services.ProviderService;
import ci.atos.apireservationservicedomicile.services.dto.ProviderDTO;
import ci.atos.apireservationservicedomicile.services.dto.ProviderRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderResource {

    private final ProviderService providerService;

    public ProviderResource(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> createProvider(@Valid @RequestBody ProviderRequestDTO providerDTO) throws UserNotFoundException {
        ProviderDTO createdProvider = providerService.createProvider(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProvider);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable Long id) throws MyProviderNotFoundException {
            ProviderDTO provider = providerService.findProviderById(id);
            return ResponseEntity.ok(provider);
    }

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> getAllProviders() {
        List<ProviderDTO> providers = providerService.findAllProviders();
        return ResponseEntity.ok(providers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> updateProvider(
            @PathVariable Long id, @RequestBody ProviderRequestDTO providerDTO) {
        try {
            ProviderDTO updatedProvider = providerService.updateProvider(id, providerDTO);
            return ResponseEntity.ok(updatedProvider);
        } catch (MyProviderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProviderDTO>> searchProviders(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name) {
        List<ProviderDTO> providers = providerService.searchProviders(category, name);
        return ResponseEntity.ok(providers);
    }
}
