# WebSocket连接文档

## 网关与imc-message-center的WebSocket连接URL
> ws://127.0.0.1:8000/chat/message/broadcast/{appId}/{gatewayId}

* appId：应用id（每个即时通讯应用对应一个id）
* gatewayId：网关id（一个即时通讯应用下有多个网关与imc-message-center建立ws连接）

### 网关发送聊天消息至imc-message-center
#### 消息发送格式（JSON格式，用WebSocket传入）
```json
{
	"appId": "1",
	"groupId": "1",
	"userId": "1",
	"content": "http_test",
	"type": "1"
}
```