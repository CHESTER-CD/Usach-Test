package com.midemo.password.password_analyzer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PasswordController.class)
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAnalyzePassword_Success() throws Exception {
        String password = "Password123!";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.longitud").value(12))
                .andExpect(jsonPath("$.tieneMayusculas").value(true))
                .andExpect(jsonPath("$.tieneCaracterEspecial").value(true))
                .andExpect(jsonPath("$.tieneNumero").value(true))
                .andExpect(jsonPath("$.tieneLongitudMinima").value(true));
    }

    @Test
    public void testAnalyzePassword_NoUpperCase() throws Exception {
        String password = "password123!";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("La contraseña debe tener al menos una letra mayúscula."));
    }

    @Test
    public void testAnalyzePassword_NoSpecialChar() throws Exception {
        String password = "Password123";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("La contraseña debe tener al menos un carácter especial."));
    }

    @Test
    public void testAnalyzePassword_NoNumber() throws Exception {
        String password = "Password!";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("La contraseña debe tener al menos un número."));
    }

    @Test
    public void testAnalyzePassword_TooShort() throws Exception {
        String password = "Pass1!";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("La contraseña debe tener al menos 8 caracteres."));
    }

    @Test
    public void testAnalyzePassword_MultipleErrors() throws Exception {
        String password = "pass";
        mockMvc.perform(post("/api/password/analyze")
                .contentType("application/json")
                .content(password))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("La contraseña debe tener al menos 8 caracteres."))
                .andExpect(jsonPath("$.errors[1]").value("La contraseña debe tener al menos una letra mayúscula."))
                .andExpect(jsonPath("$.errors[2]").value("La contraseña debe tener al menos un carácter especial."))
                .andExpect(jsonPath("$.errors[3]").value("La contraseña debe tener al menos un número."));
    }
}
