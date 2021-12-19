package com.example.myShop.services;

import com.example.myShop.models.Client;
import com.example.myShop.models.Role;
import com.example.myShop.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client getClientByPrincipal(Principal principal){
        if(principal == null) return new Client();
        return clientRepository.findByEmail(principal.getName());
    }

    public boolean createClient(Client client) {
        String clientEmail = client.getEmail();
        if (clientRepository.findByEmail(clientEmail) != null) return false;
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.getRoles().add(Role.ROLE_USER);
        clientRepository.save(client);
        return true;
    }

    public List<Client> clientList(){
        return clientRepository.findAll();
    }

    public void deleteClientById(Integer id){
        clientRepository.deleteById(id);
    }

    public Client getClientById(Integer id){
        return clientRepository.findById(id).orElse(null);
    }
}
