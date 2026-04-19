package com.sailpoint.datacoreactive.controller;

import com.sailpoint.datacoreactive.dto.UserAccessRequest;
import com.sailpoint.datacoreactive.dto.UserAccessResponse;
import com.sailpoint.datacoreactive.service.DataCoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data-core")
@RequiredArgsConstructor
public class DataCoreUserController {

    private final DataCoreUserService userService;

    @GetMapping("/users")
    public List<UserAccessResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public List<UserAccessResponse> createUsers(@RequestBody List<UserAccessRequest> requests) {
        return requests.stream()
                .map(userService::createUserAccess)
                .collect(Collectors.toList());
    }

    @PutMapping("/{userId}")
    public UserAccessResponse updateUser(@PathVariable Long userId,
                                         @RequestBody UserAccessRequest request) {
        return userService.updateUserAccess(userId, request);
    }
}