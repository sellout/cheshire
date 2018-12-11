resolvers += Resolver.sonatypeRepo("releases")

lazy val core = (project in file("core"))
  .settings(
    scalaOrganization := "org.typelevel",
    scalaVersion := "2.12.4-bin-typelevel-4",
    scalacOptions ++= Seq(
      "-Ykind-polymorphism",
      "-Yno-imports"),
    addCompilerPlugin(
      "org.spire-math" %% "kind-projector" % "0.9.8"),
    wartremoverErrors ++= Warts.allBut(Wart.Any, Wart.Nothing))

lazy val laws = (project in file("laws"))
  .dependsOn(core)
  .settings(
    scalaOrganization := "org.typelevel",
    scalaVersion := "2.12.4-bin-typelevel-4",
    scalacOptions ++= Seq(
      "-Ykind-polymorphism",
      "-Yno-imports"),
    libraryDependencies := Seq(
      "org.typelevel"  %% "discipline" % "0.10.0",
      "org.scalacheck" %% "scalacheck" % "1.14.0"),
    addCompilerPlugin(
      "org.spire-math" %% "kind-projector" % "0.9.8"),
    // wartremoverErrors ++= Warts.allBut(Wart.Any, Wart.Nothing)
  )

/** A library to make it easier to use this library in concert with Cats.
  */
lazy val cats = (project in file("cats"))
  .dependsOn(core)
  .settings(
    scalaOrganization := "org.typelevel",
    scalaVersion := "2.12.4-bin-typelevel-4",
    scalacOptions ++= Seq(
      "-Ykind-polymorphism",
      "-Yno-imports"),
    libraryDependencies := Seq(
      "org.typelevel" %% "cats-core"   % "1.3.1",
      "org.typelevel" %% "cats-effect" % "1.0.0"),
    addCompilerPlugin(
      "org.spire-math" %% "kind-projector" % "0.9.8"),
    // wartremoverErrors ++= Warts.allBut(Wart.Any, Wart.Nothing)
  )

lazy val tests = (project in file("tests"))
  .dependsOn(core, laws)
  .settings(
    scalaOrganization := "org.typelevel",
    scalaVersion := "2.12.4-bin-typelevel-4",
    scalacOptions ++= Seq(
      "-Ykind-polymorphism",
      "-Yno-imports"),
    libraryDependencies := Seq(
      "org.typelevel" %% "discipline" % "0.10.0" % "test",
      "org.scalatest" %% "scalatest"  % "3.0.5"  % "test"),
    // wartremoverErrors ++= Warts.allBut(Wart.Any, Wart.Nothing)
  )
