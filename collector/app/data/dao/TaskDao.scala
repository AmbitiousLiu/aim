package data.dao

import DTO.{HttpTaskDTO, ShellTaskDTO}
import com.google.gson.Gson
import data.DO.ShellTaskDO
import play.api.db.Database

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

/**
 * @author jleo
 * @date 2021/2/8
 */
@Singleton
class TaskDao @Inject()(db: Database, databaseExecutionContext: ExecutionContext, gson: Gson) {

  var shellTaskList: Seq[ShellTaskDTO] = List[ShellTaskDTO]()
  var httpTaskList: Seq[HttpTaskDTO] = List[HttpTaskDTO]()

  def getTask: (Seq[ShellTaskDTO], Seq[HttpTaskDTO]) = {
      db.withConnection { conn =>
        // do whatever you need with the db connection
        val re = conn.prepareStatement("select * from task").executeQuery()
        while (re.next()) {
          val typeId = re.getInt(6)
          if (typeId == 1) {
            shellTaskList = shellTaskList :+ ShellTaskDTO(re.getString(1), re.getString(4), gson.fromJson(re.getString(7), classOf[ShellTaskDO]))
          }
          if (typeId == 2) {
            httpTaskList = httpTaskList :+ HttpTaskDTO(re.getString(1), re.getString(4), re.getString(8))
          }
        }
        conn.close()
        (shellTaskList, httpTaskList)
      }
  }
}
