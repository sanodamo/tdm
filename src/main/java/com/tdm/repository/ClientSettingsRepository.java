package com.preauth.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.preauth.shared.model.Client;
import com.preauth.shared.model.IClientSettingsRepository;

@Repository
public class ClientSettingsRepository extends BaseRepository implements IClientSettingsRepository{

RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> getClients() {		
		String clientQuery = "select distinct client_id, full_name  from rad_framework..client_addon";
		List<Client> activeClients = jdbc.query(clientQuery, rowMapper); 	
		return activeClients;		
	}
	
}
