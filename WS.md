# WebSocket连接文档

## 网关与imc-message-center的WebSocket连接URL
> ws://127.0.0.1:8000/chat/message/broadcast/{appId}/{gatewayId}

* appId：应用id（一个中台`imc-message-center`给多个即时通讯应用提供服务，每个即时通讯应用对应一个appId）
* gatewayId：网关id（一个即时通讯应用下有多个网关与imc-message-center建立ws连接）

***

### 建立连接
网关与imc-message-center成功建立websocket后，imc-message-center返回成功连接提示：
```json
{
  "success":true,
  "code":2000,
  "message":"success",
  "data":"connection successful"
}
```

***

### 发送消息
用户客户端发送消息到网关，网关再将消息发送至imc-message-center
#### 消息发送格式
* JSON格式，使用WebSocket发送（也可用HTTP发送消息）
```json
{
  "appId": "test_app",
  "groupId": "test_group",
  "userId": "test_user",
  "content": "ws_test",
  "type": 1
}
```

***

### 消息广播推送
imc-message-center收到一个网关发来的消息后，会通过Redis订阅发布管道向所有与该消息appId相同的网关推送消息，网关再将该消息广播给用户客户端
#### 推送消息的格式
* JSON格式，使用WebSocket推送
* code:2001 表示这是新发布的消息
```json
{
  "success":true,
  "code":2001,
  "message":"success",
  "data":
  {
    "messageId":"418884968857600000",
    "appId":"test_app",
    "groupId":"test_group",
    "userId":"test_user",
    "content":"ws_test",
    "createTime":1657695611176,
    "recallTime":0,
    "recallCause":null,
    "status":1,
    "type":1
  }
}
```

***

### 消息撤回
用户调用HTTP撤回消息接口`/chat/message/recall`后，imc-message-center会将该消息广播给所有与该消息appId相同的网关，网关再将该消息广播给用户客户端，告知客户端删除该消息
#### 推送消息的格式
* JSON格式，使用WebSocket推送
* code:2002 表示这是要撤回的消息
```json
{
  "success":true,
  "code":2002,
  "message":"success",
  "data":
  {
    "messageId":"418884968857600000",
    "appId":"test_app",
    "groupId":"test_group",
    "userId":"test_user",
    "content":"ws_test",
    "createTime":1657695611176,
    "recallTime":1657596161352,
    "recallCause":"管理员撤回了一条消息",
    "status":0,
    "type":1
  }
}
```