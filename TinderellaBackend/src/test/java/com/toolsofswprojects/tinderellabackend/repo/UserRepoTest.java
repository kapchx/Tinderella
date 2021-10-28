package com.toolsofswprojects.tinderellabackend.repo;

import com.toolsofswprojects.tinderellabackend.model.User_t;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepoTest {
    @Autowired
    private UserRepo underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    @Disabled
    void deleteEmployeeById() {
        User_t user = new User_t(
                12L,
                "name",
                "email",
                "256",
                "sadas",
                "1"
        );
        User_t savedUser = underTest.save(user);
        underTest.deleteUserById(savedUser.getId());
        assertThat(underTest.findUserById(savedUser.getId()).isEmpty()).isTrue();
    }

    @Test
    void findEmployeeById() {

    }
}