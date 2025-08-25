package com.midemo.password.password_analyzer.dto;

public class PasswordAnalysisResponse {

    private int longitud;
    private String sha256Hash;
    private String md5Hash;
    private String base64Encoded;
    private boolean tieneMayusculas;
    private boolean tieneCaracterEspecial;
    private boolean tieneNumero;
    private boolean tieneLongitudMinima;

    // Puedes usar la funcionalidad de tu IDE para generar los getters y setters automáticamente
    
    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
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

    public boolean isTieneCaracterEspecial() {
        return tieneCaracterEspecial;
    }

    public void setTieneCaracterEspecial(boolean tieneCaracterEspecial) {
        this.tieneCaracterEspecial = tieneCaracterEspecial;
    }

    public boolean isTieneNumero() {
        return tieneNumero;
    }

    public void setTieneNumero(boolean tieneNumero) {
        this.tieneNumero = tieneNumero;
    }

    public boolean isTieneLongitudMinima() {
        return tieneLongitudMinima;
    }

    public void setTieneLongitudMinima(boolean tieneLongitudMinima) {
        this.tieneLongitudMinima = tieneLongitudMinima;
    }
}
