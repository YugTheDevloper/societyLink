package org.yug.societylink.controller;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.yug.societylink.dto.JwtResponse;
import org.yug.societylink.dto.LoginRequest;
import org.yug.societylink.dto.ResidentDTO;
import org.yug.societylink.model.Resident;
import org.yug.societylink.security.JwtUtils;
import org.yug.societylink.service.ComplaintService;
import org.yug.societylink.service.ResidentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private ResidentService residentService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // 1. Hand the unverified email and password to the "Engine"
        //A Synchronous call to authentication manager
        //That will bring the authentication object created by Provider and sanitized by Manager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // 2. If the code reaches this line, the user is 100% verified.
        // We now ask our factory to print the 3-part Token.
        String jwt = jwtUtils.generateJwtToken(authentication);

        // 3. Return the token inside a JSON response
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResidentDTO> registerResident(@RequestBody ResidentDTO residentDTO){

        return new ResponseEntity<>(residentService.registerResident(residentDTO), HttpStatus.CREATED);
    }
}