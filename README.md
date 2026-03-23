java -Xms2g -Xmx4g -jar target/Task1-0.0.1-SNAPSHOT.jar - default porfile
java -Xms2g -Xmx4g -Dspring.profiles.active=test1 -jar target/Task1-0.0.1-SNAPSHOT.jar - test1 profile
java -Xms2g -Xmx4g -Dspring.profiles.active=test2 -jar target/Task1-0.0.1-SNAPSHOT.jar - test2 profile
In PowerShell, you might need to quote the -D argument:
java -Xms2g -Xmx4g "-Dspring.profiles.active=test1" -jar target/Task1-0.0.1-SNAPSHOT.jar
