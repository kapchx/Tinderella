package com.toolsofswprojects.tinderellabackend.service;

import com.toolsofswprojects.tinderellabackend.exception.BadRequestException;
import com.toolsofswprojects.tinderellabackend.model.User_t;
import com.toolsofswprojects.tinderellabackend.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    private UserService underTest;


    @BeforeEach
    @Disabled
    void setUp(){
        //sunderTest = new UserService(userRepo);
    }

    @Test
    @Disabled
    void addUser() {
        //given
        User_t user = new User_t(
                12L,
                "name",
                "email",
                "256",
                "sadas",
                "1",
                "password",
                User_t.UserRole.ROLE_USER
        );
        //when
        underTest.addUser(user);

        //then
        ArgumentCaptor<User_t> userArgumentCaptor =
                ArgumentCaptor.forClass(User_t.class);

        verify(userRepo).save(userArgumentCaptor.capture());

        User_t capturedUser =userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThroWhenEmailIsTaken() {
        //given
        User_t user = new User_t(
                12L,
                "name",
                "email",
                "256",
                "sadas",
                "1",
                "password",
                User_t.UserRole.ROLE_USER
        );
        //when

        given(userRepo.selectExistsEmail(user.getEmail())).willReturn(true);

        //then
       assertThatThrownBy(()-> underTest.addUser(user))
               .isInstanceOf(BadRequestException.class)
               .hasMessageContaining("Eamil" + user.getEmail() + " taken");
       verify(userRepo, never()).save(any());
    }

    @Test
    void findAllUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void findUserById() {
    }

    @Test
    @Disabled
    void deleteUser() {
        underTest.deleteUser(10L);
        verify(userRepo).deleteUserById(10L);
    }
}