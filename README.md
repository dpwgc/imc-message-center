# IMC Message Center 
## 即时通讯应用-消息推送中心
### `消息中台`

本项目是对 https://github.com/dpwgc/fast-im 项目的重构
（由 MVC 架构转为 DDD + CQRS 架构）

***

### 系统架构
* 基于 Spring Boot 结合 DDD 领域驱动设计及 CQRS 架构实现（代码层面实现读写分离，底层数据存储不分离）
* 数据库：MariaDB（可换成 MySQL，两者通用）
* 消息队列：待定
* 分布式WebSocket消息推送功能：基于 Redis 订阅/发布功能 + Spring WebSocket 实现

***

### 实现功能
* 群聊消息广播发送与撤回
* 群聊消息查询
* 群聊通知发布、删除与查询



