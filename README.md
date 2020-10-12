# hips-back
Back end for the exercise/diet tracking webapp 'Hips'.

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

Set it to the location of your `jdk` installation. Echo it to ensure it is set properly.
##### Ensure you have PostgreSQL installed 
```sh
$ psql --version
psql (PostgreSQL) 10.14
```
##### Create the database and credentials for the app

Enter the PostgreSQL shell using the default superuser
```sh
$ psql -U postgres
```
Use the password you set during the installation, and don't forget to end your statements with semicolons.
The following commands should be followed exactly to use the configuration for local environments present in the project without modification.
```sh
Password for the user postgres:
postgres#= CREATE USER username;
CREATE ROLE
postgres#= alter user username with encrypted password 'password';
ALTER ROLE
postgres#= create database hips;
CREATE DATABASE
postgres#= grant all privileges on database hips to username;
GRANT
postgres#= \q
$
```

Using the values given here will allow seamless usage while using the local profile of the application.

##### Clone this repository

```sh
$ git clone https://github.com/hips-app/hips-back
```
##### Use the maven wrapper script to install the dependencies
The option `install` is one of many available for this wrapper
```sh
$ mvn install -Plocal
```
If you need to retry this installation for some reason, use the `clean` option before `install`, to ensure you're trying from scratch:
```sh
$ mvn clean install -Plocal
```
It will install dependencies and run tests under the local profile, meaning that it depends on the database being configured as shown above.

The `-Plocal` parameter specifies a `maven` profile, not a `spring-boot` one, so to use this option with different profiles it would be necessary to edit the `pom.xml` file of the project to include a new profile.

The output is verbose, but if everything is in order, it will end with something like:
```sh
[INFO] --------------------------------------
[INFO] BUILD SUCCESS
[INFO] --------------------------------------
[INFO] Total time:  02:02 min
[INFO] Finished at: 2020-09-27T21:24:42-05:00
[INFO] --------------------------------------
```
##### Start the application using `maven`
To start the application in the local profile, use the following command:
```sh
$ mvn spring-boot:run -Dspring-boot.run.profiles=local
```
This `spring-boot` profile specifies the name of the `application-{profile name}.properties` file to be loaded.

Alternatively, the shorter command for the existing `maven` profile can be used:
```sh
mvn spring-boot:run -Plocal
```
As mentioned above this shorter version doesn't require just an appropriate `properties` file, but also a `maven` profile specified in the `pom.xml` file. The `local` profile is already provided but others must be created before being used.

After the output stops, go to `localhost:8008` you can start testing the API, using the following endpoints:

- `POST` to `/signup` => Expects JSON object with fields `firstname`, `lastname`, `email`, `password` (all `string`s). Given that the email hasn't been used to create a user before, one will be created. For a `200 OK` response, the body is a JWT for authentication.