package controllers

import auth.AuthAction
import models.ToDoListItem
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Cookie, DiscardingCookie}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable
import play.api.libs.json._

@Singleton
class ToDoListController @Inject() (
     val controllerComponents: ControllerComponents,
     authAction: AuthAction
                                   ) extends BaseController{

  private var todoList = new mutable.ListBuffer[ToDoListItem]()
  todoList += ToDoListItem(1, "test", true)
  todoList += ToDoListItem(2, "some other value", false)


  //todoList json formatter to convert between json and todoListItem
  implicit val todoListJson = Json.format[ToDoListItem]

  def getAll(): Action[AnyContent] = authAction{
    if(todoList.isEmpty){
      NoContent
    }else{
      Ok(Json.toJson(todoList))
    }
  }

  def getItemById(itemId : Long): Action[AnyContent] = Action {
    val foundItem = todoList.find(_.id == itemId)
    foundItem match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound
    }
  }

  def markItemAsDone(itemId: Long): Action[AnyContent] = Action {
    todoList = todoList.map( item => {
      if(item.id == itemId) ToDoListItem(item.id, item.description, true)
      else item
    })

    Ok(Json.toJson("MMMMMMMMMMMMMMMMMMM"))

//      .size match {
//      case 1 => Ok(Json.toJson("item status updated"))
//      case _ => NotFound
//    }

  }


  def testRegexPath(id : Int) = Action{ implicit request =>
    Ok(Json.toJson(id))
  }

//  def addCookies(): Action[AnyContent] = Action{
//    OK(Json.toJson("hello world!! with cookie"))
////      .withCookies(Cookie("hello", "hi"))
////      .bakeCookies()
//  }


  def addCookies()= Action {

      Ok(Json.toJson("Hello World with cookies"))
        .withCookies(Cookie("hello" , "hi"))
        .bakeCookies()


  }

  def removeCookies() = Action {

    Ok(Json.toJson("Hello World without cookies"))
      .discardingCookies(DiscardingCookie("hello"))

  }

}
