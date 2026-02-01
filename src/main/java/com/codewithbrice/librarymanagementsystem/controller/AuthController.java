package com.codewithbrice.librarymanagementsystem.controller;

import com.codewithbrice.librarymanagementsystem.exception.UserException;
import com.codewithbrice.librarymanagementsystem.payload.dto.UserDTO;
import com.codewithbrice.librarymanagementsystem.payload.request.ForgotPasswordRequest;
import com.codewithbrice.librarymanagementsystem.payload.request.LoginRequest;
import com.codewithbrice.librarymanagementsystem.payload.request.ResetPasswordRequest;
import com.codewithbrice.librarymanagementsystem.payload.response.ApiResponse;
import com.codewithbrice.librarymanagementsystem.payload.response.AuthResponse;
import com.codewithbrice.librarymanagementsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler (
            @RequestBody @Valid UserDTO req
            ) throws UserException
    {
        AuthResponse res = authService.signup(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody @Valid LoginRequest req
            ) throws UserException
    {
        AuthResponse res = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(res);
    }

@PostMapping("/forgot-password")
public ResponseEntity<ApiResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    try {
        // Essaie de créer le token et d'envoyer l'email
        authService.createdPasswordResetToken(request.getEmail());

        // Si on arrive ici, aucun exception n'a été lancée => email envoyé
        ApiResponse res = new ApiResponse(
                "A reset link was sent to your email.", true
        );
        return ResponseEntity.ok(res);

    } catch (UserException e) {
        // Utilisateur introuvable ou autre problème lié à l'utilisateur
        ApiResponse res = new ApiResponse(e.getMessage(), false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);

    } catch (MailSendException e) {
        // Problème lors de l'envoi de l'email
        ApiResponse res = new ApiResponse("Failed to send reset email. Please try again later.", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}


    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword (
        @RequestBody ResetPasswordRequest request
    ) throws Exception {
        authService.resetPassword(request.getToken(), request.getPassword());
        ApiResponse res = new ApiResponse(
                "Password reset successful", true
        );

        return ResponseEntity.ok(res);
    }



}
