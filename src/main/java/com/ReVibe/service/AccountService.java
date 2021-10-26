package com.ReVibe.service;

import java.security.Key;
import java.sql.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service @Slf4j
public class AccountService {
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account findByUserId(int id) {
		log.info("find user id {} in Database", id);
		return this.accountRepository.findByUserId(id);
	}


	public List<Account> findAll(){
		log.info("find all users");
		return this.accountRepository.findAll();
	}

	public Account findByName(String name) {
		log.info("find {} in Database", name);
		return this.accountRepository.findByName(name);
	}
	

	public void merge(Account account) {
		log.info("Merge {} ", account);
		this.accountRepository.setAccountInfoByUserId(account.getName(),account.getPassword(),account.getUsername(),account.getProfilePic(),account.getEmail());
	}


	public List<Account> findBySearchName(String name) {
		log.info("Search {} in Database", name);
		return this.accountRepository.findByNameContaining(name);
	}

	public Account saveAccount(Account account) {
		log.info("saving account ({}) in Database", account);
		return this.accountRepository.saveAccount(account);
	}

	public Account findByUsernameAndPassword(String username, String password) {
		log.info("find user ({}) and password ({})", username, password);
		Account user = this.accountRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			return null; 
		}
		else
			return user;
	}
	
	  public Account findById(int id) { 
		  return this.accountRepository.getById(id);
	  
	  }

	public Account findByEmail(String email) {
		log.info("find user by {} in Database", email);
		return this.accountRepository.findByEmail(email);
	}
	
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void save(Account account) {
		log.info("Saving new user ({}) to the database", account.getName());
		this.accountRepository.save(account);
	}

	// TODO: FOR KWAME TO FIX
//	public Account findByUsername(String username) {
//		log.info("{} found in the database", username);
//		return this.accountRepository.findByUsername(username);
//	}

}

