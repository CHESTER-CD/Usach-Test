package com.midemo.password.password_analyzer.controller;

import com.midemo.password.password_analyzer.dto.ErrorResponse;
import com.midemo.password.password_analyzer.dto.PasswordAnalysisResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @PostMapping("/analyze")
    public ResponseEntity<Object> analyzePassword(@RequestBody String password) throws NoSuchAlgorithmException {

        List<String> errors = new ArrayList<>();
        if (password.length() < 8) {
            errors.add("La contraseña debe tener al menos 8 caracteres.");
        }
        if (password.equals(password.toLowerCase())) {
            errors.add("La contraseña debe tener al menos una letra mayúscula.");
        }
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = specialCharPatten.matcher(password);
        if (!matcher.find()) {
            errors.add("La contraseña debe tener al menos un carácter especial.");
        }
        Pattern numberPatten = Pattern.compile("[0-9]");
        Matcher numberMatcher = numberPatten.matcher(password);
        if (!numberMatcher.find()) {
            errors.add("La contraseña debe tener al menos un número.");
        }

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
        }

        PasswordAnalysisResponse response = new PasswordAnalysisResponse();

        // Lógica de análisis y conversiones
        response.setLongitud(password.length());

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = sha256.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        response.setSha256Hash(hexString.toString());

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md5.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder md5Hex = new StringBuilder();
        for (byte b : md5Bytes) {
            md5Hex.append(String.format("%02x", b));
        }
        response.setMd5Hash(md5Hex.toString());

        String encodedString = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        response.setBase64Encoded(encodedString);

        response.setTieneMayusculas(true);
        response.setTieneCaracterEspecial(true);
        response.setTieneNumero(true);
        response.setTieneLongitudMinima(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}