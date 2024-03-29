package controllers

import data.dao.TaskDao
import play.api.http.Writeable

import javax.inject._
import play.api.mvc._
import service.TaskService

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, taskService: TaskService) extends AbstractController(cc) {

  val task: Future[Unit] = taskService.initTask()
  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index: Action[AnyContent] = Action {
    Ok(views.html.index())
  }
}
