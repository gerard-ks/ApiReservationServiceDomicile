package ci.atos.apireservationservicedomicile.services.impl;

import ci.atos.apireservationservicedomicile.models.Role;
import ci.atos.apireservationservicedomicile.models.User;
import ci.atos.apireservationservicedomicile.repositories.RoleRepository;
import ci.atos.apireservationservicedomicile.repositories.UserRepository;
import ci.atos.apireservationservicedomicile.services.UserService;
import ci.atos.apireservationservicedomicile.services.dto.UserDTO;
import ci.atos.apireservationservicedomicile.services.dto.UserRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.UserRequestUpdateDTO;
import ci.atos.apireservationservicedomicile.services.mapper.UserMapper;
import ci.atos.apireservationservicedomicile.web.exception.MyRoleNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserRequestDTO userDTO) throws MyRoleNotFoundException {

        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(()-> new MyRoleNotFoundException(userDTO.getRoleId()));

        User savedUser = getUser(userDTO, role);

        return userMapper.toDto(savedUser);
    }

    private User getUser(UserRequestDTO userDTO, Role role) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public UserDTO updateUser(String username, UserRequestUpdateDTO userDTO) throws UserNotFoundException {

        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow(()-> new UserNotFoundException("User " + userDTO.getUsername() + " not found"));

        user.setPassword(userDTO.getPassword());

        User updateUser = userRepository.save(user);
        return userMapper.toDto(updateUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User " + username + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByUsername(email).orElseThrow(()-> new UserNotFoundException("User " + email + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
