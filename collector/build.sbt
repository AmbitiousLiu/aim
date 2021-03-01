name := "collector"
 
version := "1.0" 
      
lazy val `collector` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice ,
  "org.apache.kafka" %% "kafka" % "0.11.0.0" ,
  "mysql" % "mysql-connector-java" % "8.0.22",
  "com.google.code.gson" % "gson" % "2.8.6",
  "com.velopayments" % "java-spring-resttemplate" % "2.14.87"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      