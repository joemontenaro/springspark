# springspark

### Build
```
keytool -genkeypair \
  -alias springspark \
  -keyalg RSA \
  -keysize 2048 \
  -storetype PKCS12 \
  -keystore springspark.p12 \
  -validity 365 \
  -dname "CN=localhost" \
  -storepass changeit \
  -keypass changeit

mv springspark.p12 src/main/resources

mvn clean package
```

### Run
```
spark3-submit \
  --master yarn \
  --deploy-mode client \
  --class com.example.springspark.Application \
  target/spring-spark-app-1.0.0.jar
```
