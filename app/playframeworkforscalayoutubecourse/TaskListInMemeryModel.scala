package playframeworkforscalayoutubecourse

import collection.mutable

object TaskListInMemeryModel{

  private val users = Map[String, String](("hello", "world"))

  def validateUser(username: String, password: String): Boolean = {
    users.get(username).map(_ == password).getOrElse(false)
  }
  def createUser(username: String, password: String): Boolean = ???


}
