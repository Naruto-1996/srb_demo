### srb_demo 尚融宝项目介绍

##

[哔哩哔哩视频地址](https://www.bilibili.com/video/BV1VV411n7nR?p=274&spm_id_from=pageDriver)

### 1、技术栈
* SpringBoot
* SpringCloud
* MybatisPlus
* Nacos
* Gateway
* Node
* Vue
* Nuxt

### 2、项目目录介绍

* srb-site 前端尚荣宝项目
* srb 微服务部分
* vue-admin-template 尚融宝后台在饿了么提供的基础上开发，简洁版饿了么提供的后台架子
* vue-element-admin 饿了么提供的后台架子 完整版

### 3、环境

java-> 1.8
springBoot-> 2.3.4
springCloud-> Hoxton.SR8
node-> v16.3.0
nacos-> v2.0.3
redis-> v5.0.5

### 4、运行方式

#### 1、srb-site

```

npm install
npm run dev

```

#### 2、vue-admin-template

```

npm install
npm run dev

```

#### 3、nacos 需要自行安装

安装完成后进入到`bin`目录

```

cd nacos/bin
# 单机模式下启动
./startup.sh -m standalone

```

#### 4、redis启动 需自行安装

安装完成之后 进入到`src`目录下

```

cd redis/src
# 启动redis
./redis-server

```

### 5、项目截图

项目未完成，只完成了一部分

前端
![](https://cdn.jsdelivr.net/gh/Naruto-1996/picture/images/20211123110322.png)


后台
![](https://cdn.jsdelivr.net/gh/Naruto-1996/picture/images/20211123110416.png)

接口文档
![](https://cdn.jsdelivr.net/gh/Naruto-1996/picture/images/20211123110522.png)

nacos注册中心
![](https://cdn.jsdelivr.net/gh/Naruto-1996/picture/images/20211123110620.png)


