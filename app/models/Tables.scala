package models

import slick.jdbc.PostgresProfile.api._

object Tables {
  val User = TableQuery[UserTable]
}
