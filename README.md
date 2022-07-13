# IMC Message Center 
## 通讯中台-消息推送中心
### `消息中台系统`

本项目是对 https://github.com/dpwgc/fast-im 项目的重构
（由 MVC 架构转为 DDD + CQRS 架构）

***

### 项目架构
* 基于 Spring Boot 结合 DDD 领域驱动设计及 CQRS 架构实现（代码层面实现读写分离，底层数据存储不分离）
* 数据库：MariaDB（可换成 MySQL，两者通用）
* 分布式WebSocket消息推送功能：基于 Redis 订阅/发布功能 + Spring WebSocket 实现

***

### 实现功能
* 群聊消息广播发送与撤回
* 群聊消息查询
* 群聊通知发布、删除与查询

***

### 项目结构

* message-center-sdk `存放一些ui层要用到的实体类（例如DTO对象、Command对象）`
* message-center-ui `用户接口层（表示层，包括接口服务及配置服务）`
* message-center-app `应用层（对下层传来的对象进行业务处理，再传给上层）`
* message-center-domain `领域层（负责组装基础服务层传来的数据对象）`
* message-center-infrastructure `基础服务层（包括数据库交互、缓存交互以及一些工具类）`


