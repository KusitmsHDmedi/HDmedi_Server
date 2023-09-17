# HDmedi_Server

## Code Convention 🍃
[HDmedi_Server 팀의 코드 컨벤션](https://frequent-pasta-701.notion.site/Code-convention-ff5c8b5eb59548e1a8476b146fe8e1f4?pvs=4)

<br>

## Git Convention 🍃
[HDmedi_Server 팀의 깃 컨벤션](https://frequent-pasta-701.notion.site/Git-convention-c254129d9be644b8ac5bf1d011da587b?pvs=4)

<br>

## Architecture ✨
<img width="1020" alt="Architecture" src="https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/82f82596-02a0-48d5-ab0a-716d73833fca">

<br>

## Foldering 📂
```
📂 src
┣ 📂 main.java.com.kusithm.hdmedi_server
┃  ┣ 📂 domain
┃  ┃  ┣ 📂 sample
┃  ┃     ┣ 📂 controller
┃  ┃     ┣ 📂 dto
┃  ┃     ┃  ┣ 📂 request
┃  ┃     ┃  ┣ 📂 response
┃  ┃     ┣ 📂 service
┃  ┃     ┣ 📂 domain
┃  ┃     ┣ 📂 repository
┃  ┣ 📂 global
┃  ┃  ┣ 📂 common
┃  ┃  ┃  ┣ BaseResponse.class
┃  ┃  ┃  ┣ SuccessCode.class
┃  ┃  ┃  ┣ BaseEntity.class
┃  ┃  ┣ 📂 config
┃  ┃  ┃  ┣ 📂 auth
┃  ┃  ┃  ┣ 📂 jwt
┃  ┃  ┣ 📂 error
┃  ┃     ┣ 📂 dto
┃  ┃     ┃  ┣ ErrorBaseResponse.class
┃  ┃     ┣ 📂 exception
┃  ┃     ┃  ┣ BusinessException.class
┃  ┃     ┃  ┣ ConflictException.class
┃  ┃     ┃  ┣ EntityNotFoundException.class
┃  ┃     ┃  ┣ ErrorCode.class
┃  ┃     ┃  ┣ ForbiddenException.class
┃  ┃     ┃  ┣ InvalidValueException.class
┃  ┃     ┃  ┣ UnauthorizedException.class
┃  ┃     ┣ 📂 handler
┃  ┃        ┣ GlobalExceptionHandler.class
┃  ┣ 📂 infra
┃     ┣ 📂 smtp
┣ 📂 resources
┃  ┣ 📂 templates
┃  ┣ application.yml
┃  ┣ messages_ko_KR.properties
┣ HDmediServerApplication.class
```
