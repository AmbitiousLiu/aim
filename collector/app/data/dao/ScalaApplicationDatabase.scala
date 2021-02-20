package data.dao

import play.api.db.Database

import javax.inject.Inject
import scala.concurrent.Future

/**
 * @author jleo
 * @date 2021/2/8
 */
class ScalaApplicationDatabase @Inject() (db: Database, databaseExecutionContext: DatabaseExecutionContext) {
  def updateSomething(): Unit = {
    Future {
      db.withConnection { conn =>
        // do whatever you need with the db connection
        conn.prepareStatement("select * from user").executeQuery()
      }
    }(databaseExecutionContext)
  }
}
