package dev.kun.handler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.kun.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id,role_name, role_desc from Role r JOIN user_role ur on r.id = ur.rid WHERE ur.uid = #{userId}")
    List<Role> findRoleByUserId(Long userId);
}
