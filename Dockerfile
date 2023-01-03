FROM eclipse-temurin:19 as base

RUN mkdir -p /opt/url-app
WORKDIR /opt/url-app

RUN apt-get update && apt-get install -y unzip

COPY ./build/distributions/dkb-cf-url-shortner-0.0.1-SNAPSHOT.zip /opt/url-app/urls.zip
RUN unzip urls.zip && rm urls.zip

EXPOSE 80

CMD ["java", "-classpath", "/opt/url-app/dkb-cf-url-shortner-0.0.1-SNAPSHOT/lib/*", "-Dspring.profiles.active=production", "io.github.flyingcowmoomoo.dkbcfurlshortner.DkbCfUrlShortnerApplicationKt"]