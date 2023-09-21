# HDmedi_Server

![1  표지](https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/8ac68158-aa44-443f-afe3-a78a1a7afd59)
<img width="1920" alt="시연영상" src="https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/f4dac7c0-97f7-4389-822c-9056f2d2a5c3">
<img width="1920" alt="시연영상 (1)" src="https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/d5e892dc-2a7a-4c6f-a443-ae4bb95f4b1a">
<img width="1920" alt="시연영상_2" src="https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/e9673aa4-ed2c-4363-bbdb-d9125a8a8ebb">
<img width="1920" alt="시연영상_3" src="https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/8c327c9f-cae8-4da1-9d1a-0270a5193774">
![백엔드 내용](https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/fbf089df-28bf-40a4-9ba3-d985f7dfa98b)
![백엔드 내용 (1)](https://github.com/KusitmsHDmedi/HDmedi_Server/assets/97783148/6cf739a1-a273-4b65-b47a-5cfcebeb4b00)


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
