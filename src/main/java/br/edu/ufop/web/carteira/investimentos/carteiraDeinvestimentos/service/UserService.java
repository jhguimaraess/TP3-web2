package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateUserDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.Role;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.User;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.RoleRepository;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void newUser(CreateUserDTO createUserDTO){
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        var usernameFromDb = userRepository.findByUsername(createUserDTO.username());
        var userEmailFromDb = userRepository.findByEmail(createUserDTO.email());

        if(usernameFromDb.isPresent() || userEmailFromDb.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(createUserDTO.username());
        user.setEmail(createUserDTO.email());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDTO.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);
    }

}
