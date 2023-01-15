package data.daos

import com.google.inject.Inject
import models.{Tables, User, UserTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

class UserDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                        (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile]{


  def insert(user: User) =
    db.run(Tables.User += user).map { _ => Created("") }


  def findUser(user: User)={
    val users = TableQuery[UserTable]
    val q = users.filter(_.username === user.username).filter(_.password === user.password).exists
    db.run(q.result)
  }

}
