package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jleo
 * @date 2021/2/16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
