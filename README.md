
# Redis Clone

This repo contains two projects: 
- **Redis Server**: Data store application and exposed via a Rest API, built using Spring Boot.
- **Redis Client**: A console based application, built using Vaadin Framework & Spring Boot.

**Java Version 1.8**





## Run Locally

Clone the project

```bash
git clone https://github.com/NourSamman/SimpleRedisClone
```

Open both Projects via Eclipse...

Install Maven dependencies
```bash
mvn dependency:resolve 
```

Start the server then the client 

```bash
mvn spring-boot:run
```

- Redis Server Port **8080**

- Redis Client Port **8090**

## Deployment

Both Projects are spring boot applications. Thus, Spring Boot provides an embedded Apache Tomcat Server.

### **Redis Server:**

**Building**
```bash
mvn clean package
```
After that, A WAR file is generated at target directory.

**Deploying:**

The below command should be executed in the target directory.
```bash
java -jar com-iterates.simple.redis.server-0.0.1-SNAPSHOT.war
```

### **Redis Client:**

**Building**
```bash
mvn clean package -p production
```
After that, A WAR file is generated at target directory.

**Deploying:**

The below command should be executed in the target directory.
```bash
java -jar simpleredisclient-1.0-SNAPSHOT.war
```


## Screenshots

![Screenshot](https://user-images.githubusercontent.com/70534517/145725759-7bd265f7-520c-405a-9e29-3020cf15e6ff.png)


![screenshot-2](https://user-images.githubusercontent.com/70534517/145725769-d6fefe22-b0ea-488d-9fac-8f4ac593acb7.png)




