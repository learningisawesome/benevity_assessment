package com.benevity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.benevity.model.Role;
import com.benevity.model.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    @Order(1)
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("test2021");
        user.setGivenName("Alex");
        user.setFamilyName("John");
        user.setBirthDate("11/11/1990");
        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    @Order(2)
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");

        User user = new User();
        user.setEmail("mdmaaz@gmail.com");
        user.setPassword("maaz2021");
        user.setGivenName("Md");
        user.setFamilyName("Maaz");
        user.setBirthDate("03/04/1992");
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void testFindByEmail() {
        String email = "mdmaaz@gmail.com";
        User user = userRepo.findByEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

//    @Test
//    @Order(4)
//    public void testFindByFamilyName() {
//        String familyName = "Maaz";
//        User user = userRepo.findUserByFamilyName(familyName);
//
//        assertThat(user.getFamilyName()).isEqualTo(familyName);
//    }
}

