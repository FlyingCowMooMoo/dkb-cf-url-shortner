# Getting Started


### Running the Tests
```shell
./gradlew clean check
```

### Build the Application
```shell
./gradlew clean build
docker-compose build --no-cache
```

### Running the Application
```shell
docker-compose run --service-ports service
```


### API Docs
View the OpenAPI docs at: http://localhost:8080/api/swagger-ui/index.html


###Notes
* I was advised by the recruiter this should take about 2 hours so time boxed myself to that time frame
* I would have liked to spend some more time on the API docs, however I am on holidays so decided to stay within my time box
* I decided to go with Postgres rather than a NoSQL database for simplicity, both options are good, however these such decisions should be explored more
* I'm a bit rusty with Spring/Kotlin, so it was fun to play around with it again
* I put everything in one commit since the task was so small
* As I am on an overseas holiday, was stuck on Windows, so hope the line endings do not cause any issues
* Happy New Year!

