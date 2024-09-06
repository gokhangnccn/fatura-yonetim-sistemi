package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.UserDTO;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.exception.CustomException;
import com.dicore.fatura_yonetim_sistemi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@Tag(name = "Kullanıcı Yönetimi")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Tüm kullanıcıları getir", description = "Veri tabanındaki tüm kullanıcıları getirir.")
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getALlUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Yeni kullanıcı kaydet.", description = "Veri tabanına yeni kullanıcı kaydeder.")
    @PostMapping
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody User user) {
        try {
            userService.addNewUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Bir kullanıcıyı sil.", description = "Veri tabanındaki id'si girilen kullanıcıyı siler.")
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Bir kullanıcıyı düzenle.", description = "Veri tabanındaki id'si girilen kullanıcıyı günceller.")
    @PutMapping("{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserDTO userDTO
    ) {
        try {
            userService.updateUser(userId, userDTO);
            return ResponseEntity.ok("User updated successfully");
        } catch (CustomException e) {
            throw new CustomException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
