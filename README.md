# yuuzaa

Yuuzaa is a template API for basic user authentication, including sign-up, sign-in, and role management. It serves as a starting point for developers to quickly set up their own authentication system and can be easily extended or customized to fit specific needs.

## Technologies

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

## Endpoints

- `/sign-up` (`POST`)

- `/sign-in` (`POST`)

- `/users/` (`GET`) (Only accessible by users with the `ADMINISTRATOR` role)

- `/users/{id}` (`GET`) (Only accessible by users with the `ADMINISTRATOR` role)

## The `/sign-up` endpoint

- Request Body:
```json
{
    "username": "alan_turing",
    "password": "alan_turing"
}
```

- Response Status: `201 CREATED`

## The `/sign-in` endpoint

- Authorization: `Basic {{username}:{password} (encoded using base64)}`

- Response Body: A JSON object containing a field called `jwt`, with a valid JWT token.

- Response Status: `200 OK`

## The `/sign-up` endpoint

- Request Body:
```json
{
    "username": "alan_turing",
    "password": "alan_turing"
}
```

- Response Status: `201 CREATED`

## The `/users` endpoint

- Authorization: `Bearer {jwt}`

- Response Body:

```json
[
    {
        "id": "7b9df00d-b966-42d0-9e3b-2a094a78bc73",
        "username": "ada_lovelace",
        "role": "ADMINISTRATOR"
    },
    {
        "id": "54b4aee1-f406-4fc0-97f9-373c07370171",
        "username": "alan_turing",
        "role": "USER"
    }
]
```

- Response Status: `200 OK`

## The `/users/{id}` endpoint

- Authorization: `Bearer {jwt}`

- Response Body:

```json
{
    "id": "54b4aee1-f406-4fc0-97f9-373c07370171",
    "username": "alan_turing",
    "role": "USER"
}
```

- Response Status: `200 OK`
