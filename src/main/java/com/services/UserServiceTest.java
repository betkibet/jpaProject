package com.services;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.Assert.assertEquals;
import com.models.User;
import com.repositories.RoleRepository;
import com.repositories.UserRepository;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
	@Mock
	private UserRepository mockUserRepo;
	@Mock
	private RoleRepository mockRoleRepo;
	@Mock
	private BCryptPasswordEncoder mockBCryptPassEncode;
	
	private UserService userServiceTest;
	private User user;
	
	@Before
	public void setUp() {
		initMocks(this);
		userServiceTest  = new UserService(mockUserRepo, mockRoleRepo, mockBCryptPassEncode);
		user = User.builder()
				.id(1)
				.name("Gustavo")
				.lastName("Prince")
				.email("test@test.com")
				.build();
		
		Mockito.when(mockUserRepo.save(any()))
				.thenReturn(user);
		Mockito.when(mockUserRepo.findByEmail(anyString()))
				.thenReturn(user);
	}
}
