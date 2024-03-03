package com.yang.mapper;

import com.yang.dto.LoginDTO;
import com.yang.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 管理端登录
     * @param loginDTO 携带用户名和密码
     * @return  返回用户信息对象
     */
    User adminLogin(LoginDTO loginDTO);

    /**
     * 学生端登录
     * @param loginDTO 封装用户名、密码
     * @return 返回用户信息
     */
    User studentLogin(LoginDTO loginDTO);

    /**
     * 新增一条学生信息数据
     * @param user 封装用户信息
     * @return  返回成功插入数据的数量
     */
    int insert(User user);

    /**
     * 更新用户信息
     * @param user 封装用户信息
     * @return  返回成功更新数据的数量
     */
    int update(User user);

    /**
     * 根据id查询学生
     * @param id
     * @return 返回查询到的学生信息
     */
    User selectById(Long id);

    /**
     * 根据id删除学生
     * @param studentId
     * @return 返回成功删除学生的数量
     */
    int deleteById(Long studentId);

    List<User> selectList();
}
