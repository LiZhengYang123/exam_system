package com.yang.service.student_service;

import com.yang.dto.LoginDTO;
import com.yang.vo.Result;

public interface StudentLoginLogoutService {
    Result login(LoginDTO loginDTO) ;

    Result logout();
}
