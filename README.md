# hips-back
Back end for the excercise/diet tracking webapp 'Hips'.

To install and run this application:
 ##### Ensure you have installed the `jdk` at version 11 or higher.
 To do so, you can try the following command:
```sh
$ java -version
java version "11.0.4" 2018-10-16
Java(TM) SE Runtime Environment 18.9 (build 11.0.4+10-LTS)
Java HotSpot(TM) 64-Bit Server VM (build 11.0.4+10-LTS, mixed mode, sharing)
```
In this example, the user's version is `11.0.4`

##### Ensure you have installed `maven` at version 3 or higher
Verify it like this:
```sh
$ mvn -v
Apache Maven 3.6.3 (cecedd...somehash)
...
```
##### Set the `JAVA_HOME` environment variable
#
Set it to the location of your `jdk` instalation. Echo it to ensure it is set properly.
##### Clone this repository
#
```sh
$ git clone https://github.com/hips-app/hips-back
```
##### Use the maven wrapper script to install the dependencies
The option `install` is one of many available for this wrapper
```sh
$ mvnw install
```
If you need to retry this instalation for some reason, use the `clean` option before `install`, to ensure you're trying from scratch:
```sh
$ mvnw clean install
```
It will install dependencies and run tests. The output is verbose, but if everything is in order, it will end with something like:
```sh
[INFO] --------------------------------------
[INFO] BUILD SUCCESS
[INFO] --------------------------------------
[INFO] Total time:  02:02 min
[INFO] Finished at: 2020-09-27T21:24:42-05:00
[INFO] --------------------------------------
```
##### Start the application using `maven`
Use the following
```sh
$ mvn spring-boot:run
```
After the output stops, go to `localhost:8080` to be greeted by the app.
