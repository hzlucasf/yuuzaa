# yuuzaa

Yuuzaa is a template application for basic user authentication, including sign-up, sign-in, and role management. It serves as a starting point for developers to quickly set up their own authentication system and can be easily extended or customized to fit specific needs.

## Technologies

- **HTML**
- **CSS**
- **TypeScript**
- **VueJS**
- **NGINX**

- **Kotlin**
- **Spring Boot**
- **Spring Security 6**
- **MongoDB**
- **Docker**
- **Docker Compose**
- **JWT**
- **Argon2**

## Requirements

- **Docker**
- **Docker Compose**
- **Git**
- **OpenSSL**

## Running

```sh
git clone https://github.com/hzlucasf/yuuzaa

cd yuuzaa

openssl genrsa > ./src/main/resources/private.key

openssl rsa -in ./src/main/resources/private.key -pubout -out ./src/main/resources/public.key

docker compose up -d
```
