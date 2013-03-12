name := "Template"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

{
  val wicketVersion = "6.5.0"
  val springVersion = "3.2.1.RELEASE"
  val springSecurityVersion = "3.1.3.RELEASE"
  val hibernateVersion = "4.1.9.Final"
  libraryDependencies ++= Seq(
    "org.apache.wicket" % "wicket-core" % wicketVersion,
    "org.apache.wicket" % "wicket-spring" % wicketVersion,
    "org.apache.wicket" % "wicket-auth-roles" % wicketVersion,
    "org.springframework" % "spring-context" % springVersion,
    "org.springframework" % "spring-context-support" % springVersion,
    "org.springframework" % "spring-webmvc" % springVersion,
    "org.springframework" % "spring-web" % springVersion,
    "org.springframework" % "spring-orm" % springVersion,
    "org.springframework" % "spring-test" % springVersion,
    "org.springframework.security" % "spring-security-core" % springSecurityVersion,
    "org.springframework.security" % "spring-security-web" % springSecurityVersion,
    "org.springframework.security" % "spring-security-config" % springSecurityVersion,
    "org.springframework.security" % "spring-security-taglibs" % springSecurityVersion,
    "org.hibernate" % "hibernate-core" % hibernateVersion ,
    "org.hibernate" % "hibernate-entitymanager" % hibernateVersion ,
    "org.hibernate" % "hibernate-c3p0" % hibernateVersion ,
    "junit" % "junit" % "4.8.1" % "test",
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
    "commons-fileupload" % "commons-fileupload" % "1.2.2" ,
    "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
    "javax.transaction" % "jta" % "1.1" % "provided",
    "org.slf4j" % "slf4j-api" % "1.7.2",
    "org.slf4j" % "slf4j-log4j12" % "1.7.2",
    "mysql" % "mysql-connector-java" % "5.1.23",
    "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.5",
    "de.agilecoders.wicket" % "bootstrap" % "0.7.6",
    "com.novocode" % "junit-interface" % "0.10-M2" % "test"
  )
}
           
resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

net.virtualvoid.sbt.graph.Plugin.graphSettings

seq(webSettings :_*)

seq(lessSettings : _*)  

(webappResources in Compile) <+= (resourceManaged in Compile)

(sourceDirectory in (Compile, LessKeys.less)) <<= (sourceDirectory in Compile)(_ / "webapp" / "less" )

//(resourceManaged in (Compile, LessKeys.less)) <<= (sourceDirectory in Compile)(_ / "webapp" / "css")

(compile in Compile) <<= compile in Compile dependsOn (LessKeys.less in Compile)

(LessKeys.prettyPrint in (Compile, LessKeys.less)) := true

seq(jsSettings : _*)
                  
(sourceDirectory in (Compile, JsKeys.js)) <<= (sourceDirectory in Compile)(_ / "webapp" / "coffee" )

//(resourceManaged in (Compile, JsKeys.js)) <<= (sourceDirectory in Compile)(_ / "webapp" / "js")

(compile in Compile) <<= compile in Compile dependsOn (JsKeys.js in Compile)

(JsKeys.prettyPrint in (Compile, JsKeys.js)) := true

(JsKeys.variableRenamingPolicy in (Compile, JsKeys.js)) := VariableRenamingPolicy.OFF
