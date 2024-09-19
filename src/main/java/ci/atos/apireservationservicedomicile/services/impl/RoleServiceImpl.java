package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Role;
import ci.atos.apireservationservicedomicile.repositories.RoleRepository;
import ci.atos.apireservationservicedomicile.services.RoleService;
import ci.atos.apireservationservicedomicile.services.dto.RoleDTO;
import ci.atos.apireservationservicedomicile.services.dto.RoleRequestDTO;
import ci.atos.apireservationservicedomicile.services.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO createRole(RoleRequestDTO roleRequestDTO) {

        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());

        Role savedRole = roleRepository.save(role);

        return roleMapper.toDto(savedRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .toList();
    }
}
