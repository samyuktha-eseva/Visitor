package com.virtusa.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.virtusa.visitor.entities.Role;
import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTests {
 
    @Autowired
    private UserRepository repo;
     
    // test methods go below
    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("ravik@gmail.com");
        user.setPassword("ravik2020");
        user.setUsername("Ravik");
        user.setPhno("1234567890");
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        repo.save(user);
        User existUser = repo.findByUsername("Ravik");
        assertThat(existUser).isNotNull();
         
    }
}