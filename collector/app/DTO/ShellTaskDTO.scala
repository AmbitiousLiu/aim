package DTO

import data.DO.{AlarmDO, ShellTaskDO}

/**
 * @author jleo
 * @date 2021/3/9
 */
case class ShellTaskDTO(id: String, cycle: String, shellTask: ShellTaskDO, alarmDO: AlarmDO, person: String)
