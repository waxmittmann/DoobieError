name := "DoobieError"

version := "0.1"

scalaVersion := "2.11.11"

lazy val scalazVersion  = "7.2.17"
lazy val httpsVersion   = "0.10.1"
lazy val doobieVersion  = "0.4.4"

libraryDependencies ++= Seq(
  "org.scalaz"                 %% "scalaz-core"                % scalazVersion,
  "org.scalaz"                 %% "scalaz-concurrent"          % scalazVersion,

  "org.tpolecat"             %% "doobie-core"                % doobieVersion,
  "org.tpolecat"             %% "doobie-postgres"            % doobieVersion,
  "org.tpolecat"             %% "doobie-specs2"              % doobieVersion,
  "org.tpolecat"             %% "doobie-hikari"              % doobieVersion
)

// For macro annotations
addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)