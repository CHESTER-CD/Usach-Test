package com.midemo.password.password_analyzer.dto;

public class PasswordAnalysisResponse {

    private int longitud;
    private int cantidadPalabras;
    private String sha256Hash;
    private String md5Hash;
    private String base64Encoded;
    private boolean tieneMayusculas;

    // Puedes usar la funcionalidad de tu IDE para generar los getters y setters automáticamente
    
    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getCantidadPalabras() {
        return cantidadPalabras;
    }

    public void setCantidadPalabras(int cantidadPalabras) {
        this.cantidadPalabras = cantidadPalabras;
    }

    public String getSha256Hash() {
        return sha256Hash;
    }

    public void setSha256Hash(String sha256Hash) {
        this.sha256Hash = sha256Hash;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    public String getBase64Encoded() {
        return base64Encoded;
    }

    public void setBase64Encoded(String base64Encoded) {
        this.base64Encoded = base64Encoded;
    }

    public boolean isTieneMayusculas() {
        return tieneMayusculas;
    }

    public void setTieneMayusculas(boolean tieneMayusculas) {
        this.tieneMayusculas = tieneMayusculas;
    }
}