package com.preauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.preauth.repository.ClientSettingsRepository;
import com.preauth.shared.model.Client;

@Service
public class ClientSettingsService {

	@Autowired
	private ClientSettingsRepository clientSettingsRepository;
		
	@Bean
	public List<Client> GetClients() {
		return clientSettingsRepository.getClients();		
	}
	
}
