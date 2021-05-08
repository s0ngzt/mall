# mall

forked form [mall](https://www.baidu.com)

## usage

setup database

use docker

1. start MySQL

    ```shell
    $ docker run --name mysql-mall -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql
    ```

2. start redis

