##springspark

###Build
```
mvn clean package
```

###Run
```
spark3-submit \
  --master yarn \
  --deploy-mode client \
  --class com.example.springspark.Application \
  target/spring-spark-app-1.0.0.jar
```
