addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")

// we cannot use -Xfatal-warnings here since switching from Build.scala
// to build.sbt is blocked by sbt/sbt#2532, so we get a deprecation
// warning
scalacOptions ++= Seq("-feature", "-deprecation")
