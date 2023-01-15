name := """playtest"""
organization := "innov.orange"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

//libraryDependencies ++= Seq(
////  "com.github.jwt-scala" %% "jwt-core" % "9.1.2",
//  "com.github.jwt-scala" %% "jwt-play" % "9.1.2",
//"com.auth0" % "jwks-rsa" % "0.21.2"
//)

//libraryDependencies += "com.pauldijou" %% "jwt-play-json" % "5.0.0"
libraryDependencies += "com.pauldijou" %% "jwt-core" % "5.0.0"
//libraryDependencies += "com.auth0" % "jwks-rsa" % "0.21.2"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.postgresql" % "postgresql" % "42.3.4",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.github.tminglei" %% "slick-pg" % "0.20.3",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.20.3"
)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "innov.orange.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "innov.orange.binders._"
