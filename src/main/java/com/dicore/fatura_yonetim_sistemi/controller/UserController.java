package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.UserRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.UserResponseDTO;
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
@RequestMapping("/api/users")
@Tag(name = "Kullanıcı Yönetimi")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Tüm kullanıcıları getir", description = "Veri tabanındaki tüm kullanıcıları getirir.")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Yeni kullanıcı kaydet.", description = "Veri tabanına yeni kullanıcı kaydeder.")
    @PostMapping
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.addNewUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @Operation(summary = "Bir kullanıcıyı sil.", description = "Veri tabanındaki id'si girilen kullanıcıyı siler.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Operation(summary = "Bir kullanıcıyı düzenle.", description = "Veri tabanındaki id'si girilen kullanıcıyı günceller.")
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserRequestDTO userRequestDTO
    ) {
        userService.updateUser(userId, userRequestDTO);
        return ResponseEntity.ok("User updated successfully");
    }

    @Operation(summary = "Bir kullanıcıyı getir.", description = "Veri tabanındaki id'si girilen kullanıcıyı getirir.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        UserResponseDTO userResponseDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userResponseDTO);
    }
}
