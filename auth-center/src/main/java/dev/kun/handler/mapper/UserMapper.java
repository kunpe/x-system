package dev.kun.handler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.kun.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username}")
//    @Results({
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "dev.imkun.handler.mapper.RoleMapper.findRoleByUserId")),
//            @Result(property = "email",column = "email"),
//            @Result(property = "status",column = "status"),
//            @Result(property = "status",column = "status"),
//            @Result(property = "isLocked",column = "is_locked"),
//            @Result(property = "isPasswordExpired",column = "is_password_expired"),
//            @Result(property = "isEnabled",column = "is_enabled"),
//    })
    User findUserByUsername(String username);
}
