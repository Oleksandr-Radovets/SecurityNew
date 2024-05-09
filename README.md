## Acknowledgements

- I would like to express my gratitude to the Meta training academy, which teaches all programming skills.

## Appendix

- I present a bookstore to your attention. This project was inspired by the fact that
  now few people read books and I wish it was accessible and simple.

## Authors

- Oleksandr Radovets

## Deployment

- please install Docker https://docs.docker.com/engine/install

- write the command in the terminal

  docker build -t books-store .

  docker -p run 8081:8080 books-store

## Database

- Roles for run Docker container with MySQL:

  https://dev.mysql.com/downloads/mysql — configured MySQL

  container with apply migrations for backend.

## Tech Stack

- Java
- Spring JPA
- Spring Security
- MySQl
- Docker