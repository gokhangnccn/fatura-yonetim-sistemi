package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Kullanıcı Yönetimi")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Tüm kullanıcıları getir", description = "Veri tabanındaki tüm kullanıcıları getirir.")
    @GetMapping("get-users")
    public List<User> getUsers(){
        return userService.getALlUsers();
    }

    @Operation(summary = "Yeni kullanıcı kaydet.", description = "Veri tabanına yeni kullanıcı kaydeder.")
    @PostMapping("register-user")
    public void registerNewUser(@Valid @RequestBody User user){
        userService.addNewUser(user);
    }

    @Operation(summary = "Bir kullanıcıyı sil.", description = "Veri tabanındaki id'si girilen kullanıcıyı siler.")
    @DeleteMapping("delete-user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @Operation(summary = "Bir kullanıcıyı düzenle.", description = "Veri tabanındaki id'si girilen kullanıcıyı günceller.")
    @PutMapping("update-user/{userId}")
    public void updateUser(
            @Valid
            @PathVariable Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String address

    ){
        userService.updateUser(userId,name,email,phoneNumber,address);
    }
}
