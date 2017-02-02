# lilicurroad

`mvn hpi:run -Djetty.port=9090 -Pjenkins`

... or ...

`MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=5005,suspend=n" mvn hpi:run -Djetty.port=9090 -Pjenkins`

... then ...

`open http://localhost:8080/jenkins`