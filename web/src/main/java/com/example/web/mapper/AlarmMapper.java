package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.Alarm;
import com.example.web.DO.Menu;
import com.example.web.VO.AlarmType;
import com.example.web.VO.AlarmVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author jleo
 * @date 2021/3/4
 */
@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {

    @Update("update alarm set id = #{alarm.id}, name = #{alarm.name}, type = #{alarm.type}, threshold = #{alarm.threshold} " +
            "where id = #{id}")
    int updateAlarm(@Param("id") String id, @Param("alarm") Alarm alarm);

    @Select("select * from alarm")
    List<AlarmVO> getAlarm();

}
