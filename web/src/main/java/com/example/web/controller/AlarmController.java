package com.example.web.controller;

import com.example.web.DO.Alarm;
import com.example.web.VO.AlarmType;
import com.example.web.VO.AlarmVO;
import com.example.web.mapper.AlarmMapper;
import com.example.web.socket.WebSocketServer;
import com.google.gson.Gson;
import com.jleo.jcontrol.bean.VO.CodeResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author jleo
 * @date 2021/3/8
 */
@RestController
public class AlarmController {
    @Autowired
    AlarmMapper alarmMapper;
    @Autowired
    Gson gson;

    @RequestMapping("/getAlarm")
    public CodeResult getAlarm() {
        List<AlarmVO> alarms = alarmMapper.getAlarm();
        alarms.forEach(alarmVO -> {
            alarmVO.setTypeName(AlarmType.getNameById(alarmVO.getType()));
        });
        return new CodeResult(alarms);
    }

    @RequestMapping("/deleteAlarm")
    public CodeResult deleteAlarm(String id) {
        return alarmMapper.deleteById(id) == 1 ? new CodeResult("删除成功！") : new CodeResult("删除失败！");
    }

    @RequestMapping("/editAlarm")
    public CodeResult editAlarm(@Param("id") String id,
                                HttpServletRequest request) {
        Alarm alarm = gson.fromJson(request.getParameter("alarm"), Alarm.class);
        return alarmMapper.updateAlarm(id, alarm) == 1 ? new CodeResult("修改成功！") : new CodeResult("修改失败！");
    }

    @RequestMapping("/addAlarm")
    public CodeResult addAlarm(Alarm alarm) {
        return alarmMapper.insert(alarm) == 1 ? new CodeResult("新增成功！") : new CodeResult("新增失败！");
    }

    @RequestMapping("/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(@RequestBody String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }

}
