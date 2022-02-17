package com.example.accounts.controller;

import com.example.accounts.model.Account;
import com.example.accounts.model.JwtRequest;
import com.example.accounts.model.JwtResponse;
import com.example.accounts.service.AccountService;
import com.example.accounts.service.UserService;
import com.example.accounts.utility.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    Logger log = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/")
    public String home() {
        return "Health check!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account accountPayload) {
        try {
            return new ResponseEntity<>(accountService.insertAccount(accountPayload), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage() + "Exception encountered on account create: ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

}
