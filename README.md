Chat bot (discord) ud open AI API, build with microservices architecture.

NOTE: 
Replace API key in AI-Service with open ai API key.
Replace the discord app token with your token.

Add this dependency:

      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          <version>4.0.2</version>
      </dependency>

Build projects with:  -Dskiptest docker

Run:
docker compose up

Command:
type ai!ask <question> in discord chat and wait for the response!
