package DTO

import data.DO.AlarmDO

/**
 * @author jleo
 * @date 2021/3/9
 */
case class HttpTaskDTO(id: String, cycle: String, url: String, alarmDO: AlarmDO, person: String)
