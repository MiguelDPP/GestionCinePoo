package com.miguecode.gestioncine.domain;

public class Client {
    private long documentoId;
    private String name;

    public Client(String name, long documentoId) {
        this.name = name;
        this.documentoId = documentoId;
    }

    public boolean verifyDocument(int documentoId) {
        return this.documentoId == documentoId;
    }

    public long getDocumentoId() {
        return documentoId;
    }

    public String getName() {
        return name;
    }
}
