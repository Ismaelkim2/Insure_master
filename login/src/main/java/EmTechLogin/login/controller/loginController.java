package EmTechLogin.login.controller;

import EmTechLogin.login.DTO.LoginRequest;
import EmTechLogin.login.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class loginController {

    private final AuthenticationManager authenticationManager;
    private final loginService loginService;

    @Autowired
    public loginController(AuthenticationManager authenticationManager, loginService loginService) {
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {

        // Prepare the response object
        Map<String, String> response = new HashMap<>();

        System.out.println("Username received: " + loginRequest.getUsername());
        System.out.println("Password received: " + loginRequest.getPassword());
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Set a success message in the response
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); // Return the response as JSON
        } catch (Exception e) {
            // Set an error message in the response for invalid credentials
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return as JSON
        }
    }
}
