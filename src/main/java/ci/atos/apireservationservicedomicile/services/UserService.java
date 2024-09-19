package ci.atos.apireservationservicedomicile.services;

import ci.atos.apireservationservicedomicile.services.dto.UserDTO;
import ci.atos.apireservationservicedomicile.services.dto.UserRequestDTO;
import ci.atos.apireservationservicedomicile.services.dto.UserRequestUpdateDTO;
import ci.atos.apireservationservicedomicile.web.exception.MyRoleNotFoundException;
import ci.atos.apireservationservicedomicile.web.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserRequestDTO userDTO) throws MyRoleNotFoundException;
    UserDTO updateUser(String username, UserRequestUpdateDTO userDTO) throws UserNotFoundException;
    UserDTO getUserByUsername(String username) throws UserNotFoundException;
    UserDTO getUserByEmail(String email) throws UserNotFoundException;
    List<UserDTO> getAllUsers();
}
