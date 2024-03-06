package com.user.controllers;

import com.user.entities.User;
import com.user.service.IUserService;
import com.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/usuario")

public class UserControllers {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/find/{id}")
        public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();

            return ResponseEntity.ok(user);
        }
            return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
        public ResponseEntity<?> findAll() {
         List<User> userList  = userService.findAll();

         return ResponseEntity.ok(userList);
    }

    @PostMapping("/save")
        public ResponseEntity<?> save(@RequestBody User user) throws URISyntaxException {
        if(user.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        userService.save(User.builder().name(user.getName()).build());
        return ResponseEntity.created(new URI("user/usuario/save")).build();
    }

    @PutMapping("/update/{id}")
        public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);

        if(userOptional.isPresent()) {
            User user1 = userOptional.get();
            user1.setName(user.getName());
            userService.save(user1);
            return ResponseEntity.ok("Reguistro actualizado");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if(id !=null) {
            userService.deleteById(id);
            return ResponseEntity.ok("Reguistro elinimado");
        }
        return ResponseEntity.badRequest().build();
    }
}
