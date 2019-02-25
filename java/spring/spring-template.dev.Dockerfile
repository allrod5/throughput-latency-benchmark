FROM quintoandar/docker-jdk-gradle:jdk11-2019-02-10_1

WORKDIR /usr/src/app

ADD ./ .

RUN gradle clean build

CMD [ "gradle", "bootRun", "-p", "api", "--no-daemon" ]

EXPOSE 8080
