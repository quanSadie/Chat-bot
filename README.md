Chat bot (discord) ud open AI API, build with microservices architecture.

NOTE: 
REPLACE THE OpenAI API key in AI-Service with your API key.
REPLACE THE discord app token with your token.

BEFORE ATTEMPTING TO RUN THE PROJECT, MAKE SURE TO ADD THIS DEPENDENCY:

      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
          <version>4.0.2</version>
      </dependency>

AND BUILD EACH PROJECT WITH Dskiptest docker

Run:
docker compose up

HOW TO USE?:
type ai!ask <question> in discord chat and wait for the response!
