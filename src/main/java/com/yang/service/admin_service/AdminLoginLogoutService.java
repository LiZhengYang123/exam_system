package com.yang.service.admin_service;

import com.yang.dto.LoginDTO;
import com.yang.vo.Result;

public interface AdminLoginLogoutService {

    Result login(LoginDTO loginDTO);

    Result logout();
}
