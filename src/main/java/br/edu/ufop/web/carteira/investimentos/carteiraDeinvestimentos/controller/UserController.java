package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.controller;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateUserDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.User;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO createUserDTO){
            userService.newUser(createUserDTO);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUser(){
        return ResponseEntity.ok(userService.listUser());
    }

}
