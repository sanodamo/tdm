package com.preauth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.preauth.service.ClientSettingsService;
import com.preauth.shared.model.Client;

@RestController
public class ClientSettingsController {

	@Autowired
	private ClientSettingsService clientSettingsService;

	@RequestMapping(value = "/api/clients", method = RequestMethod.GET)
	public Map<String, Object> getclients() {
		Map<String, Object> clients = new HashMap<String, Object>();
		List<Client> clientList = clientSettingsService.GetClients();
		if(clientList != null) {
			clients.put("Clients", clientList);
			return clients;
		}
			
		return null;
	}
}
