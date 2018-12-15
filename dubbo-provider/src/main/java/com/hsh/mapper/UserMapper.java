package com.hsh.mapper;

import com.hsh.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO getUserById(@Param("userId") Integer userId);

    List<UserDTO> getUsersByProvince(@Param("province") String province);
}
