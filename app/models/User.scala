package models

import play.api.libs.json.{Json, Reads}

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape


case class User(id: Long, username: String, password: String)

object User{
  implicit val userReads: Reads[User] = Json.reads[User]
}


class UserTable(tag: Tag) extends Table[User](tag, Some("public"), "user") {
  def id = column[Long]("id", O.AutoInc,O.PrimaryKey)
  def username = column[String]("username", O.Length(25))
  def password = column[String]("password")

  def pk = primaryKey("pk_user_group", (username, password))
  override def * : ProvenShape[User] = (id, username, password) <> ((User.apply _).tupled, User.unapply)
}

