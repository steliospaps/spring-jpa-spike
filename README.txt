mvn clean spring-boot:run
mysql -uroot -proot test -e 'select * from ITEM;'
 mvn spring-boot:run -Dspring.profiles.active=mysql
mvn flyway:migrate -Dflyway.url=jdbc:mysql://localhost/test -Dflyway.user=root -Dflyway.password=root
