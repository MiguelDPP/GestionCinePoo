package com.miguecode.gestioncine.service;

import com.miguecode.gestioncine.domain.Client;
import com.miguecode.gestioncine.exception.DuplicateEntityException;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final List<Client> clients;

    private static final ClientService INSTANCE =  new ClientService();

    private ClientService () {
        clients = new ArrayList<>();
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Client getClient(long documentId) {
        for (Client client : clients) {
            if (client.getDocumentoId() == documentId) {
                return client;
            }
        }
        return null;
    }

    public Client createClient(long documentId, String name) {
        if (this.getClient(documentId) != null) {
            throw new DuplicateEntityException("Cliente existente");
        }

        Client client = new Client(name, documentId);
        this.clients.add(client);

        return client;
    }
}
