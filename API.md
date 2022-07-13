# HTTP接口文档

## /chat 聊天模块
## /chat/message 聊天消息服务
## /chat/message/recall 撤回消息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/recall

#### 请求方式
> POST

#### Content-Type
> json

#### 请求Body参数
```javascript
{
	"messageId": "418467071509266432",
	"recallCause": "管理员撤回了一条消息"
}
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": "1"
}
```
## /chat/message/findByGroupId 根据群组id查询消息列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/findByGroupId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657608740901 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 9,
		"pageMessageList": [
			{
				"messageId": "418452400609820672",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "",
				"createTime": 1657592478869,
				"recallTime": 1657596161352,
				"recallCause": "管理员撤回了一条消息",
				"status": 0,
				"type": 1
			},
			{
				"messageId": "418467071509266432",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "hi",
				"createTime": 1657595976684,
				"recallTime": 0,
				"recallCause": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```
## /chat/message/findByUserId 根据用户id查询消息列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/findByUserId

#### 请求方式
> GET

#### Content-Type
> multipart/form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
userId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657608740901 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 请求Body参数
```javascript

```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 9,
		"pageMessageList": [
			{
				"messageId": "418452400609820672",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "",
				"createTime": 1657592478869,
				"recallTime": 1657596161352,
				"recallCause": "管理员撤回了一条消息",
				"status": 0,
				"type": 1
			},
			{
				"messageId": "418467071509266432",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "hi",
				"createTime": 1657595976684,
				"recallTime": 0,
				"recallCause": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```
## /chat/message/findByGroupIdAndUserId 根据群组id与用户id查询消息列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/findByGroupIdAndUserId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
userId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657608811303 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 13,
		"pageMessageList": [
			{
				"messageId": "418452400609820672",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "",
				"createTime": 1657592478869,
				"recallTime": 1657596161352,
				"recallCause": "管理员撤回了一条消息",
				"status": 0,
				"type": 1
			},
			{
				"messageId": "418467071509266432",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"content": "hi",
				"createTime": 1657595976684,
				"recallTime": 0,
				"recallCause": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```
## /chat/message/create 发送消息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/create

#### 请求方式
> POST

#### Content-Type
> json

#### 请求Body参数
```javascript
{
	"appId": "1",
	"groupId": "1",
	"userId": "1",
	"content": "http_test",
	"type": "1"
}
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": ""
}
```
## /chat/message/findBeforeByMessageId 查询指定消息之前的消息列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/findBeforeByMessageId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
messageId | 418520880302391296 | Text | 是 | -
pageSize | 4 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": [
		{
			"messageId": "418520888640667648",
			"appId": "1",
			"groupId": "1",
			"userId": "1",
			"content": "hahaha",
			"createTime": 1657608807688,
			"recallTime": 0,
			"recallCause": null,
			"status": 1,
			"type": 1
		},
		{
			"messageId": "418520896605650944",
			"appId": "1",
			"groupId": "1",
			"userId": "1",
			"content": "hahaha",
			"createTime": 1657608809587,
			"recallTime": 0,
			"recallCause": null,
			"status": 1,
			"type": 1
		}
	]
}
```
## /chat/message/findAfterByMessageId 查询指定消息之后的消息列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/chat/message/findAfterByMessageId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
messageId | 418520880302391296 | Text | 是 | -
pageSize | 3 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": [
		{
			"messageId": "418452400609820672",
			"appId": "1",
			"groupId": "1",
			"userId": "1",
			"content": "",
			"createTime": 1657592478869,
			"recallTime": 1657596161352,
			"recallCause": "管理员撤回了一条消息",
			"status": 0,
			"type": 1
		},
		{
			"messageId": "418467071509266432",
			"appId": "1",
			"groupId": "1",
			"userId": "1",
			"content": "hi",
			"createTime": 1657595976684,
			"recallTime": 0,
			"recallCause": null,
			"status": 1,
			"type": 1
		},
		{
			"messageId": "418519505065607168",
			"appId": "1",
			"groupId": "1",
			"userId": "1",
			"content": "hahaha",
			"createTime": 1657608477818,
			"recallTime": 0,
			"recallCause": null,
			"status": 1,
			"type": 1
		}
	]
}
```
## /notice 通知模块
## /notice/information 通知信息服务
## /notice/information/create 发布群组通知
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/notice/information/create

#### 请求方式
> POST

#### Content-Type
> json

#### 请求Body参数
```javascript
{
	"appId": "1",
	"groupId": "1",
	"userId": "1",
	"title": "hi",
	"content": "hello",
	"jumpUrl": "http://xxxxxx/xxxxx",
	"type": "1"
}
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": ""
}
```
## /notice/information/delete 删除群组通知
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/notice/information/delete

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
informationId | 418542784237142016 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": ""
}
```
## /notice/information/findByGroupId 根据群组id查询群组通知列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/notice/information/findByGroupId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657615600166 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 11,
		"pageInformationList": [
			{
				"informationId": "418542784237142016",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657614028005,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			},
			{
				"informationId": "418549346104115200",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657615592476,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```
## /notice/information/findByUserId 根据用户id查询用户发布的通知列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/notice/information/findByUserId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
userId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657615600166 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 11,
		"pageInformationList": [
			{
				"informationId": "418542784237142016",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657614028005,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			},
			{
				"informationId": "418549346104115200",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657615592476,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```
## /notice/information/findByGroupIdAndUserId 根据群组id与用户id查询群组通知列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://127.0.0.1:8000/notice/information/findByGroupIdAndUserId

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
appId | 1 | Text | 是 | -
groupId | 1 | Text | 是 | -
userId | 1 | Text | 是 | -
startTime | 1657592478860 | Text | 是 | -
endTime | 1657615600166 | Text | 是 | -
pageNum | 0 | Text | 是 | -
pageSize | 2 | Text | 是 | -
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"success": true,
	"code": 0,
	"message": "success",
	"data": {
		"total": 11,
		"pageInformationList": [
			{
				"informationId": "418542784237142016",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657614028005,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			},
			{
				"informationId": "418549346104115200",
				"appId": "1",
				"groupId": "1",
				"userId": "1",
				"title": "hi",
				"content": "hello",
				"createTime": 1657615592476,
				"deleteTime": 0,
				"jumpURL": null,
				"status": 1,
				"type": 1
			}
		]
	}
}
```