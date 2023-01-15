package controllers

import data.daos.UserDao
import models.User
import pdi.jwt.{Jwt, JwtAlgorithm}
import play.api.Configuration
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{BaseController, ControllerComponents, Result}
import playframeworkforscalayoutubecourse.TaskListInMemeryModel.validateUser

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject() (val controllerComponents: ControllerComponents,
                                configuration: Configuration,
                                implicit val executionContext: ExecutionContext,
                                userDao: UserDao) extends BaseController{

  def login() = Action.async(parse.json) { request =>

    Json.fromJson[User](request.body) match{
      case JsSuccess(user, _) =>
        userDao.findUser(user).map {
          case true =>
            val token = Jwt.encode(s"""{"username": ${user.username}""",
              configuration.get[String]("my.key"),
              JwtAlgorithm.HS256)

            Ok("logged in successfully")
              .withHeaders(("Authorization", token))
//              .withSession(("session", "cookie"))


          case false =>
            Ok("invalid username or password")
        }

      case JsError(errors) => Future.successful(BadRequest)
    }
  }


  def addNewUser() = Action{
    userDao.insert(User(0,"new", "test"))
    Ok
  }


}
