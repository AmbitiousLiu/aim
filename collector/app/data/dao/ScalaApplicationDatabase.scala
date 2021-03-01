package data.dao

import play.api.db.Database

import java.sql.ResultSet
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

/**
 * @author jleo
 * @date 2021/2/8
 */
class ScalaApplicationDatabase @Inject() (db: Database, databaseExecutionContext: ExecutionContext) {
  def updateSomething(): Unit = {
      db.withConnection { conn =>
        // do whatever you need with the db connection
        val re = conn.prepareStatement("select * from user").executeQuery()
        if (re.next()) {
          re.getString(1)
        }
        conn.close()
      }
  }
}
