## Common

### INFO

This project is a set of validation or responses commonly used among Spring projects.

The following dependencies are required for use.

> *이 프로젝트는 Spring 프로젝트에서 일반적으로 사용되는 유효성 검사 또는 응답의 집합입니다.*
>
> *사용시에는 아래와 같은 의존성이 요구됩니다.*



**If you are using <u>spring MVC</u>**

```text
org.springframework.srping-web
javax.validation.validation-api
```

for example, pom.xml 

```text
  <dependencies>
  ...
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
 ...
    </dependencies>
```

**OR using <u>spring-boot</u>**

```text
spring-boot-starter-web
spring-boot-starter-validation
```

for example, build.gradle

```text
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-validation'
```


🔥 After adding the above total, **register our registration!** 🔥

> *위의 의존성을 모두 추가한 후, 우리의 의존성을 등록해주세요!*

In <u>pom.xml</u>

```text
<dependency>
  <groupId>io.github.doohee94</groupId>
  <artifactId>common</artifactId>
  <version>1.0</version>
</dependency>
```

In <u>build.gradle</u>

```text
implementation 'io.github.doohee94:common:1.0'
```



### Functions

Currently, the following features are included. Many features will be added in the future. 😀

> *현재는 아래와 같은 기능들이 포함되어있습니다. 앞으로 많은 기능들이 추가될 예정입니다.* 



#### Validations

- **File**

  - This validation can validate the <u>request of MultipartFile</u>.

  - You can check null values and check extensions.

  - Please refer to the code below for how to use it

    **FileDto.java**

    ```java
    public class FileDto {
    
    	@File(acceptExtensions = {"png","jpg"}, nullable = true)
    	private MultipartFile file;
    
    }
    ```

    **Controller.java**

    ```java
    @RestController
    public class Controller {
    
    	@PostMapping
    	public void test(@Valid FileDto fileDto){
    		//code..
    	}
    }
    ```

    If you upload a file with an extension other than '**png**' or '**jpg**', an error will occur.

    You can also receive null values through nullable. But, Remember that it defaults to '**false**'.

    

    > *이 validation은 MultipartFile의 Request의 유효성을 검사합니다.*
    >
    > *당신은 null을 체크할 수있고, 파일의 확장자를 체크할 수 있습니다.*
    >
    > *만약, 당신이 파일에 '**png**'나 '**jpg**'가 아닌 다른 확장자 파일을 업로드한 경우, 에러가 발생하게 될 것입니다.* 
    >
    > *nullable을 통해 null값을 받을 수도 있습니다. 하지만 그것의 기본값은 '**false**'임을 기억하십시오.* 

    

- **Enum**

  - This validation can validate the <u>request of Enum</u>

  - You can check null values and check value.

  - Please refer to the code below for how to use it.

    **Gender.java**

    ```java
    public enum Gender{
    	MALE,
    	FEMALE
    	;
    }
    ```

    **SampleDto.java**

    ```java
    public class SampleDto {
    
    	@CheckEnum(enumClass = Gender.class, message = "Please Check Gender", nullable = true)
    	private String gender;
    
    }
    ```

    **Controller.java**

    ```java
    @RestController
    public class Controller {
    
    	@GetMapping
    	public void test(@Valid SampleDto sample){
    		//code..
    	}
    }
    ```

    If you enter a value other than '**MALE**' and '**FEMALE**' in the gender value (*eg '**M**' or '**F**'*), an error will occur.

    Do not worry! It is not case sensitive.

    You can put any error text you want in the message.

    You can also receive a null value through *nullable. But remember that it defaults to '**false**'.*

    > *이 검증은 Enum의 요청을 검증할 수 있습니다.*
    >
    > *null 값을 확인하고 값을 확인할 수 있습니다.*
    >
    > *당신이 만약 gender값에 'MALE','FEMALE'을 제외한 값 (예를들어, 'M' 혹은 'F')을 입력했다면, 에러가 발생할 것입니다.* 
    >
    > *걱정하지 마세요! 대소문자는 구분하지 않습니다.*
    >
    > *message에는 당신이 원하는 에러 문구를 넣을 수 있습니다.* 
    >
    > *nullable을 통해 null값을 받을 수도 있습니다. 하지만 그것의 기본값은 '**false**'임을 기억하십시오.* 



#### Responses

- **ListResponse**

  - This response will be mainly used by the Controller.
  - When responding to a List, you will be able to send it as a common response.
  - Please refer to the code below for how to use it.

  > *이 응답은 Controller에서 주로 사용될 것입니다.* 
  >
  > *List를 응답할 때, 공통적인 응답으로 보낼 수 있을 것입니다.* 

  **Content.java**

  ```java
  public class Content {
  
  	private String gender;
  	private String age;
  
  }
  ```

  **Controller.java**

  ```java
  @RestController
  public class Controller {
  
  	@GetMapping
  	public ListResponse<Content> test2(){
          List<Content> content = new ArrayList<>();
          //...
  		return new ListResponse<>(content);
  	}
  
  }
  ```

  The response would be something like this:

  ```json
  {
      "content" :[
          {
              "gender" : "MALE",
              "age" : 18
          }
      ],
      "totalCount" : 1
  }
  ```

  

