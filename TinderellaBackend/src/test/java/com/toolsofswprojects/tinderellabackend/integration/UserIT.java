package com.toolsofswprojects.tinderellabackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.toolsofswprojects.tinderellabackend.model.User_t;
import com.toolsofswprojects.tinderellabackend.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class UserIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepo userRepo;

    private final Faker faker = new Faker();

    @Test
    void canRegisterNewStudent() throws Exception {

        // given
        String name = String.format(
                "%s %s",
                faker.name().firstName(),
                faker.name().lastName()
        );
        String email = String.format("%s@gmail.com",
                StringUtils.trimAllWhitespace(name.trim().toLowerCase()));

        User_t user = new User_t(
                12L,
                name,
                email,
                "256",
                "dsadas",
                "1"
        );

        // when
        ResultActions resultActions =
                mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        //then
        resultActions.andExpect(status().isCreated());
        List<User_t> users = userRepo.findAll();
        assertThat(users)
                .usingElementComparatorIgnoringFields("id", "userCode")
                .contains(user);
    }
}
