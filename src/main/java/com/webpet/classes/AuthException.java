package com.webpet.classes;

public class AuthException extends RuntimeException {
    public AuthException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public AuthException(String errorMessage) {
        super(errorMessage);
    }

    public AuthException() {
        super("Erro: Não Autorizado");
    }
    
    public RespostaHttp getResposta() {
        return new RespostaHttp("Erro: Não Autorizado").code(401).setHeader("Set-Cookie", "Auth=none; Max-Age=0");
    }
}
