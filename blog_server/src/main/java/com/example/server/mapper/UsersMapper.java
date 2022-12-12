package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Bo.UserNameAndEmail;
import com.example.server.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    Users getUserByUserName(String username);

    Integer getIdByUserName(String username);

    Integer updateUserNameAndEmail(UserNameAndEmail userNameAndEmail);

    Integer updatePassword(String password);
}
