package com.susar.controler;

import java.util.Set;
import javax.validation.Valid;

import com.susar.model.User;
import com.susar.model.payload.request.LoginRequest;
import com.susar.model.payload.request.SignupRequest;
import com.susar.model.payload.response.JwtResponse;
import com.susar.model.payload.response.MessageResponse;
import com.susar.security.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private PasswordEncoder encoder;

    private RegisterService registerService;

    @Autowired
    public AuthController(PasswordEncoder encoder, RegisterService registerService) {
        this.encoder = encoder;
        this.registerService = registerService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return registerService.authenticateUser(username, password);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        ResponseEntity<MessageResponse> responseEntity = registerService.registerUser(user, strRoles);
        return responseEntity;
    }
}
