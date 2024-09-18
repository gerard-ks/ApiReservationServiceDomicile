package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.BookingServiceModel;
import ci.atos.apireservationservicedomicile.models.Provider;
import ci.atos.apireservationservicedomicile.repositories.BookingServiceRepository;
import ci.atos.apireservationservicedomicile.repositories.ProviderRepository;
import ci.atos.apireservationservicedomicile.services.ProviderService;
import ci.atos.apireservationservicedomicile.services.dto.BookingServiceDTO;
import ci.atos.apireservationservicedomicile.services.dto.ProviderDTO;
import ci.atos.apireservationservicedomicile.services.dto.ProviderRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.BookingServiceMapper;
import ci.atos.apireservationservicedomicile.services.mapper.ProviderMapper;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final BookingServiceRepository bookingServiceRepository;
    private final BookingServiceMapper bookingServiceMapper;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper, BookingServiceRepository bookingServiceRepository, BookingServiceMapper bookingServiceMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.bookingServiceRepository = bookingServiceRepository;
        this.bookingServiceMapper = bookingServiceMapper;
    }

    @Override
    public ProviderDTO createProvider(ProviderRequestDTO providerDTO) {

        Provider provider = new Provider();
        provider.setEmail(providerDTO.getEmail());
        provider.setPhone(providerDTO.getPhone());
        provider.setName(providerDTO.getName());
        provider.setAddress(providerDTO.getAddress());
        providerRepository.save(provider);

        return providerMapper.toDto(provider);
    }

    @Override
    public ProviderDTO findProviderById(Long id) throws MyProviderNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Provider existingProvider = providerRepository.findById(id).orElseThrow(()-> new MyProviderNotFoundException("provider " + id + " not found"));

        return providerMapper.toDto(existingProvider);
    }

    @Override
    public List<ProviderDTO> findAllProviders() {
        return providerRepository.findAll()
                .stream()
                .map(providerMapper::toDto)
                .toList();
    }

    @Override
    public ProviderDTO updateProvider(Long id, ProviderRequestDTO providerDTO) throws MyProviderNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        Provider existingProvider = providerRepository.findById(id).orElseThrow(()-> new MyProviderNotFoundException("provider " + id + " not found"));

        if(providerDTO.getName() != null) {
            existingProvider.setName(providerDTO.getName());
        }

        if(providerDTO.getPhone() != null) {
            existingProvider.setPhone(providerDTO.getPhone());
        }

        if(providerDTO.getAddress() != null) {
            existingProvider.setAddress(providerDTO.getAddress());
        }

        if(providerDTO.getEmail() != null) {
            existingProvider.setEmail(providerDTO.getEmail());
        }

       Provider saveProvider = providerRepository.save(existingProvider);

        return providerMapper.toDto(saveProvider);
    }

    @Override
    public List<ProviderDTO> searchProviders(String categoryServiceName, String serviceName) {

        if(categoryServiceName != null) {
            return providerRepository.findProvidersByCategoryName(categoryServiceName)
                    .stream()
                    .map(providerMapper::toDto)
                    .toList();
        }

        return providerRepository.findProvidersByServiceName(serviceName)
                .stream()
                .map(providerMapper::toDto)
                .toList();
    }
}
