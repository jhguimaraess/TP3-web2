package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.config;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.Role;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.User;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.RoleRepository;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {System.out.println("admin ja existe");},
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setEmail("admin@gmail.com");
                    user.setPassword(passwordEncoder.encode("1234"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );

    }
}
