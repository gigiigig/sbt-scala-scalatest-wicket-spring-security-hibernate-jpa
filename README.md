Description
===========

This is a skeleton for a web application wich combine some different technologies,
i needed some time to make all frameworks work together, i hope this can help someone
to save his time.

The used build system is Sbt and there are following frameworks:

Wicket 
Sprig
Sprig Mvc
Sprig Security
Hibernate Jpa 
ScalaTest 
Twitter Boostrap
Less and Coffee compiler

* Spring is used as IOC framework, and for Jpa mangment through Hibernate implementation.
  All Spring configuartions are done via XML.

* Spring Mvc is used for REST webservices.

* Wicket is used for web presentation, Wicket authroles manages Authorizarion,
  and is combined with Spring Security which manages Authentication.

* Test are written in Scala with ScalaTest.
  There is also a Sbt plugin for twitter bootstrap,
  and less and coffee plugins.

In this example there is a base implementation of a login form and an authenticated WebPage
wich shows how to use Wicket authroles with Spring Security.

There is also a exmaple Spring contorller for show ho to use Spring for webservices,
and some example tests.

All code is only for example's scope, 
and is rough and incomplete.





