package ci.atos.apireservationservicedomicile.services.impl;


import ci.atos.apireservationservicedomicile.models.Provider;
import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.repositories.BookingServiceRepository;
import ci.atos.apireservationservicedomicile.repositories.ProviderRepository;
import ci.atos.apireservationservicedomicile.repositories.UserRepository;
import ci.atos.apireservationservicedomicile.services.ProviderService;
import ci.atos.apireservationservicedomicile.services.dto.ProviderDTO;
import ci.atos.apireservationservicedomicile.services.dto.ProviderRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.BookingServiceMapper;
import ci.atos.apireservationservicedomicile.services.mapper.ProviderMapper;
import ci.atos.apireservationservicedomicile.web.exception.MyProviderNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final ProviderMapper providerMapper;
    private final BookingServiceRepository bookingServiceRepository;
    private final BookingServiceMapper bookingServiceMapper;

    public ProviderServiceImpl(ProviderRepository providerRepository, UserRepository userRepository, ProviderMapper providerMapper, BookingServiceRepository bookingServiceRepository, BookingServiceMapper bookingServiceMapper) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.providerMapper = providerMapper;
        this.bookingServiceRepository = bookingServiceRepository;
        this.bookingServiceMapper = bookingServiceMapper;
    }

    @Override
    public ProviderDTO createProvider(ProviderRequestDTO providerDTO) throws UserNotFoundException {
        // Vérifier l'existence de l'utilisateur lié au prestataire
        User user = userRepository.findById(providerDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User " + providerDTO.getUserId() + " not found"));

        Provider savedProvider = getSavedProvider(providerDTO, user);

        // Retourner le DTO du fournisseur sauvegardé
        return providerMapper.toDto(savedProvider);
    }

    private Provider getSavedProvider(ProviderRequestDTO providerDTO, User user) {
        // Créer une nouvelle instance de Provider avec les informations du DTO
        Provider provider = new Provider();
        provider.setFirstName(providerDTO.getFirstName());
        provider.setLastName(providerDTO.getLastName());
        provider.setPhone(providerDTO.getPhone());
        provider.setDateOfBirth(providerDTO.getDateOfBirth());
        provider.setAddress(providerDTO.getAddress());
        provider.setGender(providerDTO.getGender());
        provider.setUser(user); // Associer l'utilisateur au prestataire

        // Sauvegarder le fournisseur en base de données
        Provider savedProvider = providerRepository.save(provider);
        return savedProvider;
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

        // Mettre à jour les champs du fournisseur avec les données du DTO si disponibles
        if (providerDTO.getFirstName() != null) {
            existingProvider.setFirstName(providerDTO.getFirstName());
        }

        if (providerDTO.getLastName() != null) {
            existingProvider.setLastName(providerDTO.getLastName());
        }

        if (providerDTO.getPhone() != null) {
            existingProvider.setPhone(providerDTO.getPhone());
        }

        if (providerDTO.getAddress() != null) {
            existingProvider.setAddress(providerDTO.getAddress());
        }

        if (providerDTO.getDateOfBirth() != null) {
            existingProvider.setDateOfBirth(providerDTO.getDateOfBirth());
        }

        if (providerDTO.getGender() != null) {
            existingProvider.setGender(providerDTO.getGender());
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
