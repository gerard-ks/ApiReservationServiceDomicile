package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.ProviderDTO;
import ci.atos.apireservationservicedomicile.services.dto.ProviderRequestDTO;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;

import java.util.List;

public interface ProviderService {
    ProviderDTO createProvider(ProviderRequestDTO providerDTO);
    ProviderDTO findProviderById(Long id) throws MyProviderNotFoundException;
    List<ProviderDTO> findAllProviders();
    ProviderDTO updateProvider(Long id, ProviderRequestDTO providerDTO) throws MyProviderNotFoundException;
    List<ProviderDTO> searchProviders(String category, String name);
}
