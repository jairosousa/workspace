#Medium url for Code walkthrough
https://veeevek.medium.com/upload-file-feature-in-springboot-9e4ed8474b82

#prerequisites before starting the app
Requires Java18 and mvn 3.8.4
In application.yml file, configure these properties :
datasource:
url:
username: 
password: 

Example of above properties :
datasource:
url:
jdbc:mysql://vivek-abc.929299jsjs.us-west-2.rds.amazonaws.com:3306/schema?useSSL=false
username: vivek
password: vivek

#setup
To build the jar : mvn clean install
To start the app : mvn spring-boot:run


#To change Java version
pom.xml file --  line 17  -- <java.version>18</java.version>

#Usecase 
This application lets you upload a file based on userId and get the file based on emailId. 
There are two endpoints :
/users/upload/post - To upload the image.
Takes userId, emailId and profilePicImageFile as the request parameter. If you are using postman use form attributes 
to pass these properties.

/users/upload/get - To get the image.
Takes only emailId as the request parameter. If you are using postman, use params key value pair to pass the email

#localhost url
localhost:8080

#Sample urls
localhost:8080/users/upload/get?emailId="vivek.sinless@gmail.com"

