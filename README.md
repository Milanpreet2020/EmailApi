# SpringBoot-Restful-Email-Sending-API

This Repository contains Restful Api for sending E-mail in two different ways using `smtp.gmail.com` host.

## Download this project as :

```
Download -> Import project ->Existing Maven Projects -> Run as Spring Boot project
```
## APIs Developed for  :

```
1.sendEmail                   

2.sendEmailWithAttachment

```
## Edit sender's E-mail address and password at application.properties file.
### Directory location src/main/resource/application.properties
```
spring.mail.username = *********@gmail.com	 
spring.mail.password = *********
```
## Create a private varible in UserRegisterController and pass the reference and define email address in application.properties
```
user.setEmailAddress("Your_Email_Address");
```
## APIs are accessible at the link :

* Send Mail without Attachment :
```
http://localhost:8080/send-mail
```
* Send Mail with Attachment :
```
http://localhost:8080/send-mail-attachment
```
  
## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

## Author

**Milanpreet Kaur** -
