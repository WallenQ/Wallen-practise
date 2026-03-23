# 笔记

## 1.软件工具

virtualBox

### 1.1.Vagrant  

vagrant和root密码都是vagrant

初始化CentOS系统：vagrant init centos/7

启动虚拟机(要在虚拟机所在目录下执行，同级目录包含Vagrantfile)：vagrant up

连接虚拟机：vagrant ssh

重启虚拟机：vagrant reload

修改ip：Vagrantfile中打开以下注释，其中ip要查看当前VirtualBox的ip地址，要在同一网段

```
config.vm.network "private_network", ip: "192.168.33.10"
```



### 1.2.安装docker（CentOS）

#### 1.删除老版本

```shell
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

#### 2.安装工具包、设置存储库

```
sudo yum install -y yum-utils

sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

遇到的问题：虚拟机空间满了，检查虚拟机的/vagrant目录，有可能是本地的用户下（C:\Users\XXX）文件被复制到这个目录下，直接删除该目录即可

#### 3.安装docker引擎

```
sudo yum install docker-ce docker-ce-cli containerd.io
```

#### 4.启动docker

```
sudo systemctl start docker
```

#### 5.设置开机自启动

```
sudo systemctl enable docker
```

#### 6.设置国内镜像仓库

[阿里云镜像加速器]: https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

```
sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://sbajtwdj.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload

sudo systemctl restart docker
```

### 1.3安装MySQL

#### 1.docker安装mysql

```
sudo docker pull mysql:5.7
```

#### 2. docker启动mysql

```
sudo docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```

参数：

- -p 3306:3306：将容器的3306端口映射到主机的3306端口
- --name：给容器命名
- -v /mydata/mysql/log:/var/log/mysql：将配置文件挂载到主机/mydata/..
- -e MYSQL_ROOT_PASSWORD=root：初始化root用户的密码为root

查看docker启动的容器:

```
docker ps
```

#### 3.配置mysql

```
cd /mydata/mysql/conf
```

修改配置文件 `my.cnf`

```
vi my.cnf
```

```
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve

# Esc
# :wq
```

docker重启mysql使配置生效

```
docker restart mysql
```

### 1.4安装Redis

#### 1.docker拉取redis镜像

```
docker pull redis
```

#### 2.docker启动redis

创建redis配置文件目录

```
mkdir -p /mydata/redis/conf

touch /mydata/redis/conf/redis.conf
```

启动redis容器

```
docker run -p 6379:6379 --name redis \
-v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

进入Redis

```
docker exec -it redis redis-cli
```



#### 3. 配置redis持久化

```
echo "appendonly yes"  >> /mydata/redis/conf/redis.conf

# 重启生效
docker restart redis
```



#### 容器随docker启动自动运行

```
# mysql
docker update mysql --restart=always

# redis
docker update redis --restart=always
```

### 1.5安装VSCode和前端插件

Auto Close Tag

Auto Rename Tag

Chinese

ESLInt

HTML CSS Support

HTML Snippets

JavaScript(ES6)

open in browser

Vetur

### 1.6配置gitee的ssh（百度查一下即可）

###1.7安装node.js，
npm配置淘宝镜像：
```
npm config set registry http://registry.npm.taobao.org/
```
第一次启动项目前，执行：
```
npm install
```
运行项目：
```
npm run dev
```




覆盖到Lesson16



目前整个项目基本结构搭完了，es我们两个重新看了一下，我负责45-58三级分类，另外一个朋友59-69 品牌管理



## 链接笔记

[https://www.yuque.com/zhangshuaiyin/guli-mall](https://gitee.com/link?target=https%3A%2F%2Fwww.yuque.com%2Fzhangshuaiyin%2Fguli-mall)
谷粒商城篇章1：[https://blog.csdn.net/unique_perfect/article/details/111392634](https://gitee.com/link?target=https%3A%2F%2Fblog.csdn.net%2Funique_perfect%2Farticle%2Fdetails%2F111392634)
谷粒商城篇章2：[https://blog.csdn.net/unique_perfect/article/details/113824202](https://gitee.com/link?target=https%3A%2F%2Fblog.csdn.net%2Funique_perfect%2Farticle%2Fdetails%2F113824202)
谷粒商城篇章3：[https://blog.csdn.net/unique_perfect/article/details/114035775](https://gitee.com/link?target=https%3A%2F%2Fblog.csdn.net%2Funique_perfect%2Farticle%2Fdetails%2F114035775)