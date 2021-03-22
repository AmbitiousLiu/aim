package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.Task;
import com.example.web.VO.TaskAlarm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jleo
 * @date 2021/3/4
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    @Select("select task.id as id, task.name as name, " +
            "task.type as type, task.person as person, " +
            "alarm.name as alarm_name, task.cycle as cycle, " +
            "task.config as config, task.url as url " +
            "from task left join alarm on task.alarm_id = alarm.id limit #{begin}, 10")
    List<TaskAlarm> getTasks(@Param("begin") int begin);

    @Select("select task.id as id, task.name as name, " +
            "task.type as type, task.person as person, " +
            "alarm.name as alarm_name, task.cycle as cycle, " +
            "task.config as config, task.url as url " +
            "from task left join alarm on task.alarm_id = alarm.id ${searchString} " +
            "limit #{begin}, 10")
    List<TaskAlarm> search(@Param("searchString") String searchString, @Param("begin") int begin);

}
