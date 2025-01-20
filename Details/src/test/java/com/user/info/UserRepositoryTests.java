package com.user.info;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("tannut692@gmail.com");
		user.setPassword("TANNUTYAGI");
		user.setFirstName("Tannu");
		user.setLastName("Tyagi");
		
		 User savedUser = userRepo.save(user);
		 
		 User existUser = entityManager.find(User.class,savedUser.getId());
		 
		 assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindUserByEmail() {
	    String email = "tannut692@gmail.com";
	    User user = userRepo.findByEmail(email);
	    assertThat(user).isNotNull();
	    assertThat(user.getEmail()).isEqualTo(email);
	}

}
