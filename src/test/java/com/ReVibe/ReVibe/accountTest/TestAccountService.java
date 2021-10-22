package com.ReVibe.accountTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ReVibe.model.Account;
import com.ReVibe.repository.AccountRepository;
import com.ReVibe.service.AccountService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class TestAccountService {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountService).build();
	}
	
	@Test
	public void testSave() {
		Mockito.when(accountRepository.saveAccount(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo")))
		.thenReturn(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo"));
		
		Account account = accountService.saveAccount(new Account(1, "KCastillo", "p4ssword", "Kevin Castillo"));
		
		assertThat(account).isNotNull();
		verify(accountRepository, times(1)).save(account);
	}
}