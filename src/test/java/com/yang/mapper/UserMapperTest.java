package com.yang.mapper;

import com.yang.dto.LoginDTO;
import com.yang.pojo.User;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void adminLogin() {
        LoginDTO loginDTO = LoginDTO.builder()
                                .username("20221041154")
                                .password("123456")
                                .build();
        User user = userMapper.adminLogin(loginDTO);
        System.out.println(user);
    }

    @Test
    void insert(){
        User user = User.builder()
                .name("王五")
                .username("20221041108")
                .password("123456")
                .isAdmin(false)
                .gender(2)
                .build();

        int count = userMapper.insert(user);

        System.out.println(count);
    }

}