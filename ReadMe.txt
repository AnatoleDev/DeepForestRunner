docker build -t aaglazkov/web-runner . - Сборка

docker volume create runner-db

docker run --name web-runner -v runner-db:/src/main/resources/db -p 8080:8080 aaglazkov/web-runner - запуск


