package models

import play.api.libs.json.{Json, Reads}

case class TokenModel(username: String)

object TokenModel{
  implicit val tokenModelReads: Reads[TokenModel] = Json.reads[TokenModel]
}
