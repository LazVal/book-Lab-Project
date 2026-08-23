# Test Automation Project for [BookLabProject](https://book-club.qa.guru/api/v1/docs/swagger/)

## **Contents:** ##
* <a href="#tools">Technologies and tools</a>
* <a href="#cases">Examples of automated test cases</a>
* <a href="#jenkins">Build in Jenkins</a>
* <a href="#console">Run from Terminal</a>
* <a href="#allure">Allure report</a>
* <a href="#telegram">Telegram notification with bot</a>

-----
<a id="tools"></a>
## <a name="Technologies and tools">**Technologies and tools:**</a>

<p align="center">
<a href="https://www.w3schools.com/java/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" title="Java" alt="Java" width="40" height="40"/> </a> 
<a href="https://www.jetbrains.com/idea/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/intellij/intellij-original.svg" title="IntelliJ Idea" alt="IntelliJ Idea" width="40" height="40"/> </a> 
<a href="https://git-scm.com/"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/git/git-original.svg" title="Git" alt="Git" width="40" height="40"/> </a> 
<a href="https://junit.org/junit5"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" title="JUnit5" alt="JUnit5" width="40" height="40"/> </a>
<a href="https://rest-assured.io/"> <img src="image/rest_assured.png" title="REST-assured" alt="REST-assured" width="40" height="40"/> </a>
<a href="https://gradle.org"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg" title="Gradle" alt="Gradle" width="40" height="40"/> </a>
<a href="https://allurereport.org/"> <img src="image/allure_report.png" title="Allure report" alt="Allure report" width="40" height="40"/> </a>
<a href="https://www.jenkins.io"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" title="Jenkins" alt="Jenkins" width="40" height="40"/> </a>
</p>

- The API autotests were written in **Java**.
- **Gradle** was used as the builder.
- **JUnit 5** and **REST-assured** were used as test frameworks.
- **Allure report** generation and result sending to **Telegram** using a bot has been implemented.

----
<a id="cases"></a>
## **Examples of automated test cases:**

**Endpoint `POST /users/register/`**
- ✅ Successful user registration
- ✅ Getting an error 'User already exists'
- ✅ Getting an error 404 Not Found

**Endpoint `POST /auth/token/`**
- ✅ Successful user authorization
- ✅ Getting an error 'Invalid username or password'
- ✅ Getting an error 'Field cannot be empty'
- ✅ Getting an error '405'

**Endpoint `POST /auth/logout/`**
- ✅ Successful user logout
- ✅ Getting an error 'Field cannot be empty'

**Endpoint `/clubs/`**
- ✅ Successful club creation (POST /clubs/)
- ✅ Getting detailed club information (GET /clubs/)
- ✅ Changing the book title and author (PATCH /clubs/)
- ✅ Deleting a club (DELETE /clubs/)

**Endpoint `/users/me/`**
- ✅ Getting user information (GET /users/me/)
- ✅ Updating user data (PATCH /users/me/)
- ✅ Getting an authorization error (GET /users/me/)

----
<a id="jenkins"></a>
## Build in Jenkins ([link](https://jenkins.qa.guru/job/BookLabProjectLazVal/))

<p align="center">  
<a href="https://jenkins.qa.guru/job/BookLabProjectLazVal/"><img src="image/Jenkins.png" alt="Jenkins build" width="950"/></a>  
</p>

----
<a id="console"></a>
## Run from Terminal

**Local launch**
```bash  
gradle clean test
```
----
<a id="allure"></a>
## Allure report ([link](https://jenkins.qa.guru/job/BookLabProjectLazVal/4/allure/))

**Allure report overview**
<p align="center">  
<a href="https://jenkins.qa.guru/job/BookLabProjectLazVal/4/allure/"><img src="image/Allure Result.png" alt="Allure Report overview" width="950"/></a>  
</p>

**Suites – test cases**
<p align="center">  
<a href="https://jenkins.qa.guru/job/BookLabProjectLazVal/4/allure/"><img src="image/Suites.png" alt="Allure Suites" width="950"/></a>  
</p>

----
<a id="telegram"></a>
## Telegram notification with bot
<p align="center">  
<img src="images/screen/tg_bot_report.png" width="350"/> 
</p>
