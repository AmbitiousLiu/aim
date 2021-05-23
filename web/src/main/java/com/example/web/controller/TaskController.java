package com.example.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.DO.Alarm;
import com.example.web.DO.Task;
import com.example.web.VO.TaskAlarm;
import com.example.web.VO.TaskType;
import com.example.web.mapper.AlarmMapper;
import com.example.web.mapper.TaskMapper;
import com.example.web.mapper.UserMapper;
import com.example.web.util.AimWebConstant;
import com.google.gson.*;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author jleo
 * @date 2021/3/4
 */
@RestController
public class TaskController {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Gson gson;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getTasks")
    public CodeResult getTasks(@RequestParam("page") int page) {
        List<TaskAlarm> taskPage = taskMapper.getTasks((page - 1) * 10);
        taskPage.forEach(taskAlarm -> {
            taskAlarm.setTypeName(TaskType.getByValue(taskAlarm.getType()));
        });
        return new CodeResult(taskPage);
    }

    @RequestMapping("/getTotalTask")
    public CodeResult getTotalTask() {
        return new CodeResult(taskMapper.selectCount(null));
    }

    @RequestMapping("/getPerson")
    public CodeResult getPerson() {
        return new CodeResult(userMapper.selectList(null));
    }

    @RequestMapping("/addTask")
    public CodeResult addTask(Task task, HttpServletRequest request) {
        return taskMapper.insert(task) == 1 ? new CodeResult("添加成功！") : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "添加失败！");
    }

    @RequestMapping("/deleteTask")
    public CodeResult deleteTask(String id) {
        return taskMapper.deleteById(id) == 1 ? new CodeResult("删除成功！") : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "删除失败！");
    }

    @RequestMapping("/getSearchPageNumber")
    public CodeResult getSearchPageNumber(String searchId, String searchPerson) {
        QueryWrapper<Task> objectQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(searchId)) {
            objectQueryWrapper.eq("id", searchId);
        }
        if (!StringUtils.isEmpty(searchPerson)) {
            objectQueryWrapper.eq("person", searchPerson);
        }
        return new CodeResult(taskMapper.selectCount(objectQueryWrapper));
    }

    @RequestMapping("/search")
    public CodeResult search(String searchId, String searchPerson, Integer page) {
        StringBuilder searchString = new StringBuilder(" where 1 = 1 ");
        if (!StringUtils.isEmpty(searchId)) {
            searchString.append(" and task.id = '").append(searchId).append("' ");
        }
        if (!StringUtils.isEmpty(searchPerson)) {
            searchString.append(" and task.person = '").append(searchPerson).append("' ");
        }
        List<TaskAlarm> search = taskMapper.search(searchString.toString(), (page - 1) * 10);
        search.forEach(taskAlarm -> {
            taskAlarm.setTypeName(TaskType.getByValue(taskAlarm.getType()));
        });
        return new CodeResult(search);
    }

    @RequestMapping("/getCollectedData")
    public CodeResult getCollectedData(String id, String begin, String end) throws URISyntaxException {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("begin", begin);
        json.addProperty("end", end);
        URI uri = new URI(AimWebConstant.IP_DATA + "/getCollectedData");
        Object body = restTemplate.postForEntity(uri, gson.toJson(json), Object.class).getBody();
        return body == null ? new CodeResult(JControlConstant.CODE_RESULT_ERROR, "查询出错") : new CodeResult(body);
    }
}
