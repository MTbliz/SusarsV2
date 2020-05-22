package com.susar.security.services;

import com.susar.model.User.User;
import com.susar.model.payload.response.JwtResponse;
import com.susar.model.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface RegisterService {

    ResponseEntity<MessageResponse> registerUser(User user, Set<String> strRoles);

    ResponseEntity<JwtResponse> authenticateUser(String userName, String password);
}
