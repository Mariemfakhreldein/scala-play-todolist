package controllers

import models.ToDoListItem
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable
import play.api.libs.json._

@Singleton
class ToDoListController @Inject() (val controllerComponents: ControllerComponents) extends BaseController{

  private val todoList = new mutable.ListBuffer[ToDoListItem]()
  todoList += ToDoListItem(1, "test", true)
  todoList += ToDoListItem(2, "some other value", false)


  //todoList json formatter to convert between json and todoListItem
  implicit val todoListJson = Json.format[ToDoListItem]

  def getAll(): Action[AnyContent] = Action{
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
}
