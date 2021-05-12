# mall

## usage

### setup database

use docker

1. start MySQL

   ```shell
   $ docker pull mysql:5.7
   $ docker run --name mysql-mall -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql:5.7
   ```

2. start Redis
   ```shell
   $ docker pull redis:6.2
   $ docker run --name redis-mall -d -p 6379:6379 redis:6.2
   ```
   
3. start Elasticsearch
   ```shell
   $ docker pull elasticsearch:7.12.1
   # to be added
   ```

3. start MongoDB
   ```shell
   $ docker pull mongo:4.4
   $ docker run -itd --name mongo-mall -p 27017:27017 mongo:4.4
   ```

4. start RabbitMQ
   ```shell
   $ docker pull rabbitmq:3.8-management
   $ docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.8-management
   ```
