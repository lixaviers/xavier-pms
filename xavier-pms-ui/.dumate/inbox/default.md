# 开发文档


**简介**:开发文档


**HOST**:127.0.0.1:5002


**联系人**:Xavier


**Version**:v1.0


**接口路径**:/v2/api-docs


[TOC]






# 会议室管理接口


## 新增会议室


**接口地址**:`/meeting/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增会议室</p>



**请求示例**:


```javascript
{
  "id": 0,
  "isEnable": true,
  "meetingName": "",
  "position": "",
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|meetingDto|meetingDto|body|true|会议室入参|会议室入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;meetingName|会议室名称||true|string||
|&emsp;&emsp;position|位置||false|string||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除会议室


**接口地址**:`/meeting/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除会议室</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取会议室信息


**接口地址**:`/meeting/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据会议室ID获取会议室信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«会议室出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|会议室出参|会议室出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;meetingName|会议室名称|string||
|&emsp;&emsp;position|位置|string||
|&emsp;&emsp;remarks|备注|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"isEnable": true,
		"meetingName": "",
		"position": "",
		"remarks": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取会议室列表信息


**接口地址**:`/meeting/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取会议室列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "id": 0,
  "isEnable": true,
  "meetingName": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "position": "",
  "remarks": "",
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|meetingQueryDto|meetingQueryDto|body|true|会议室查询入参|会议室查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||false|boolean||
|&emsp;&emsp;meetingName|会议室名称||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;position|位置||false|string||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«会议室出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«会议室出参»|分页输出结果基类«会议室出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|会议室出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;&emsp;&emsp;meetingName|会议室名称|string||
|&emsp;&emsp;&emsp;&emsp;position|位置|string||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"isEnable": true,
				"meetingName": "",
				"position": "",
				"remarks": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑会议室


**接口地址**:`/meeting/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑会议室</p>



**请求示例**:


```javascript
{
  "id": 0,
  "isEnable": true,
  "meetingName": "",
  "position": "",
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|meetingDto|meetingDto|body|true|会议室入参|会议室入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;meetingName|会议室名称||true|string||
|&emsp;&emsp;position|位置||false|string||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 公告管理接口


## 新增公告


**接口地址**:`/announcement/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增公告</p>



**请求示例**:


```javascript
{
  "announcementTypeId": 0,
  "content": "",
  "endTime": "",
  "id": 0,
  "isEnable": true,
  "remarks": "",
  "sortNumber": 0,
  "startTime": "",
  "title": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementDto|announcementDto|body|true|公告入参|公告入参|
|&emsp;&emsp;announcementTypeId|公告类型id||true|integer(int64)||
|&emsp;&emsp;content|内容||false|string||
|&emsp;&emsp;endTime|结束时间||false|string(date-time)||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;startTime|开始时间||false|string(date-time)||
|&emsp;&emsp;title|标题||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除公告


**接口地址**:`/announcement/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除公告</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取公告信息


**接口地址**:`/announcement/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据公告ID获取公告信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«公告出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|公告出参|公告出参|
|&emsp;&emsp;announcementTypeId|公告类型id|integer(int64)||
|&emsp;&emsp;content|内容|string||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;endTime|结束时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|&emsp;&emsp;startTime|开始时间|string(date-time)||
|&emsp;&emsp;title|标题|string||
|&emsp;&emsp;typeName|类型名称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"announcementTypeId": 0,
		"content": "",
		"createTime": "",
		"endTime": "",
		"id": 0,
		"isEnable": true,
		"remarks": "",
		"sortNumber": 0,
		"startTime": "",
		"title": "",
		"typeName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取公告列表信息


**接口地址**:`/announcement/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取公告列表信息</p>



**请求示例**:


```javascript
{
  "announcementTypeId": 0,
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "returnFields": "",
  "title": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementQueryDto|announcementQueryDto|body|true|公告查询入参|公告查询入参|
|&emsp;&emsp;announcementTypeId|公告类型id||false|integer(int64)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;title|标题||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«公告出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«公告出参»|分页输出结果基类«公告出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|公告出参|
|&emsp;&emsp;&emsp;&emsp;announcementTypeId|公告类型id|integer||
|&emsp;&emsp;&emsp;&emsp;content|内容|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;endTime|结束时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;sortNumber|排序|integer||
|&emsp;&emsp;&emsp;&emsp;startTime|开始时间|string||
|&emsp;&emsp;&emsp;&emsp;title|标题|string||
|&emsp;&emsp;&emsp;&emsp;typeName|类型名称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"announcementTypeId": 0,
				"content": "",
				"createTime": "",
				"endTime": "",
				"id": 0,
				"isEnable": true,
				"remarks": "",
				"sortNumber": 0,
				"startTime": "",
				"title": "",
				"typeName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑公告


**接口地址**:`/announcement/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑公告</p>



**请求示例**:


```javascript
{
  "announcementTypeId": 0,
  "content": "",
  "endTime": "",
  "id": 0,
  "isEnable": true,
  "remarks": "",
  "sortNumber": 0,
  "startTime": "",
  "title": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementDto|announcementDto|body|true|公告入参|公告入参|
|&emsp;&emsp;announcementTypeId|公告类型id||true|integer(int64)||
|&emsp;&emsp;content|内容||false|string||
|&emsp;&emsp;endTime|结束时间||false|string(date-time)||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;startTime|开始时间||false|string(date-time)||
|&emsp;&emsp;title|标题||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 公告类型管理接口


## 新增公告类型


**接口地址**:`/announcementType/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增公告类型</p>



**请求示例**:


```javascript
{
  "id": 0,
  "remarks": "",
  "sortNumber": 0,
  "typeName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementTypeDto|announcementTypeDto|body|true|公告类型入参|公告类型入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;typeName|类型名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除公告类型


**接口地址**:`/announcementType/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除公告类型</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取公告类型信息


**接口地址**:`/announcementType/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据公告类型ID获取公告类型信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«公告类型出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|公告类型出参|公告类型出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|&emsp;&emsp;typeName|类型名称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"remarks": "",
		"sortNumber": 0,
		"typeName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取公告类型列表信息


**接口地址**:`/announcementType/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取公告类型列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "id": 0,
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "remarks": "",
  "returnFields": "",
  "sortNumber": 0,
  "typeName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementTypeQueryDto|announcementTypeQueryDto|body|true|公告类型查询入参|公告类型查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int64)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;sortNumber|排序||false|integer(int32)||
|&emsp;&emsp;typeName|类型名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«公告类型出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«公告类型出参»|分页输出结果基类«公告类型出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|公告类型出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;sortNumber|排序|integer||
|&emsp;&emsp;&emsp;&emsp;typeName|类型名称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"remarks": "",
				"sortNumber": 0,
				"typeName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑公告类型


**接口地址**:`/announcementType/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑公告类型</p>



**请求示例**:


```javascript
{
  "id": 0,
  "remarks": "",
  "sortNumber": 0,
  "typeName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|announcementTypeDto|announcementTypeDto|body|true|公告类型入参|公告类型入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;typeName|类型名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 印章管理接口


## 新增印章


**接口地址**:`/seal/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增印章</p>



**请求示例**:


```javascript
{
  "id": 0,
  "isEnable": true,
  "remarks": "",
  "sealName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sealDto|sealDto|body|true|印章入参|印章入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sealName|印章名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除印章


**接口地址**:`/seal/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除印章</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取印章信息


**接口地址**:`/seal/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据印章ID获取印章信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«印章出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|印章出参|印章出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sealName|印章名称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"isEnable": true,
		"remarks": "",
		"sealName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取印章列表信息


**接口地址**:`/seal/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取印章列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "id": 0,
  "isEnable": true,
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "remarks": "",
  "returnFields": "",
  "sealName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sealQueryDto|sealQueryDto|body|true|印章查询入参|印章查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||false|boolean||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;sealName|印章名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«印章出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«印章出参»|分页输出结果基类«印章出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|印章出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;sealName|印章名称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"isEnable": true,
				"remarks": "",
				"sealName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑印章


**接口地址**:`/seal/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑印章</p>



**请求示例**:


```javascript
{
  "id": 0,
  "isEnable": true,
  "remarks": "",
  "sealName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sealDto|sealDto|body|true|印章入参|印章入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sealName|印章名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 员工组管理接口


## 新增员工组


**接口地址**:`/userGroup/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增员工组</p>



**请求示例**:


```javascript
{
  "employeeList": [
    {
      "id": 0,
      "nickName": ""
    }
  ],
  "groupName": "",
  "id": 0,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userGroupDto|userGroupDto|body|true|员工组入参|员工组入参|
|&emsp;&emsp;employeeList|员工列表||false|array|员工组员工列表入参|
|&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;groupName|组名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除员工组


**接口地址**:`/userGroup/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除员工组</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取员工组信息


**接口地址**:`/userGroup/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据员工组ID获取员工组信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«员工组出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|员工组出参|员工组出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;employeeList|员工列表|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;id|用户id|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;groupName|组名称|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"employeeList": [
			{
				"id": 0,
				"nickName": ""
			}
		],
		"groupName": "",
		"id": 0,
		"remarks": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取员工组列表信息


**接口地址**:`/userGroup/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取员工组列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "groupName": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "remarks": "",
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userGroupQueryDto|userGroupQueryDto|body|true|员工组查询入参|员工组查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;groupName|组名称||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«员工组出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«员工组出参»|分页输出结果基类«员工组出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|员工组出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;employeeList|员工列表|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;groupName|组名称|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"employeeList": [
					{
						"id": 0,
						"nickName": ""
					}
				],
				"groupName": "",
				"id": 0,
				"remarks": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑员工组


**接口地址**:`/userGroup/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑员工组</p>



**请求示例**:


```javascript
{
  "employeeList": [
    {
      "id": 0,
      "nickName": ""
    }
  ],
  "groupName": "",
  "id": 0,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userGroupDto|userGroupDto|body|true|员工组入参|员工组入参|
|&emsp;&emsp;employeeList|员工列表||false|array|员工组员工列表入参|
|&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;groupName|组名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 审批分组管理接口


## 新增审批分组


**接口地址**:`/approvalGroup/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增审批分组</p>



**请求示例**:


```javascript
{
  "groupName": "",
  "id": 0,
  "remarks": "",
  "sortNumber": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|approvalGroupDto|approvalGroupDto|body|true|审批分组入参|审批分组入参|
|&emsp;&emsp;groupName|分组名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除审批分组


**接口地址**:`/approvalGroup/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除审批分组</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取审批分组信息


**接口地址**:`/approvalGroup/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据审批分组id获取审批分组信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«审批分组出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|审批分组出参|审批分组出参|
|&emsp;&emsp;approvalList|审批列表|array|审批出参|
|&emsp;&emsp;&emsp;&emsp;appStatus|审批状态|integer||
|&emsp;&emsp;&emsp;&emsp;approvalGroupId|审批分组id|integer||
|&emsp;&emsp;&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型|string||
|&emsp;&emsp;groupName|分组名称|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"approvalList": [
			{
				"appStatus": 0,
				"approvalGroupId": 0,
				"approvalName": "",
				"icon": "",
				"id": 0,
				"remarks": "",
				"submitType": ""
			}
		],
		"groupName": "",
		"id": 0,
		"remarks": "",
		"sortNumber": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取生效的审批列表


**接口地址**:`/approvalGroup/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取生效的审批列表，根据分组名称分组，应用于发起申请页面</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«审批分组出参»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|审批分组出参|
|&emsp;&emsp;approvalList|审批列表|array|审批出参|
|&emsp;&emsp;&emsp;&emsp;appStatus|审批状态|integer||
|&emsp;&emsp;&emsp;&emsp;approvalGroupId|审批分组id|integer||
|&emsp;&emsp;&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型|string||
|&emsp;&emsp;groupName|分组名称|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"approvalList": [
				{
					"appStatus": 0,
					"approvalGroupId": 0,
					"approvalName": "",
					"icon": "",
					"id": 0,
					"remarks": "",
					"submitType": ""
				}
			],
			"groupName": "",
			"id": 0,
			"remarks": "",
			"sortNumber": 0
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取审批分组列表信息


**接口地址**:`/approvalGroup/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取审批分组列表信息</p>



**请求示例**:


```javascript
{
  "keyword": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|approvalGroupQueryDto|approvalGroupQueryDto|body|true|审批分组查询入参|审批分组查询入参|
|&emsp;&emsp;keyword|关键字||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«审批分组出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|审批分组出参|
|&emsp;&emsp;approvalList|审批列表|array|审批出参|
|&emsp;&emsp;&emsp;&emsp;appStatus|审批状态|integer||
|&emsp;&emsp;&emsp;&emsp;approvalGroupId|审批分组id|integer||
|&emsp;&emsp;&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型|string||
|&emsp;&emsp;groupName|分组名称|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"approvalList": [
				{
					"appStatus": 0,
					"approvalGroupId": 0,
					"approvalName": "",
					"icon": "",
					"id": 0,
					"remarks": "",
					"submitType": ""
				}
			],
			"groupName": "",
			"id": 0,
			"remarks": "",
			"sortNumber": 0
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑审批分组


**接口地址**:`/approvalGroup/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑审批分组</p>



**请求示例**:


```javascript
{
  "groupName": "",
  "id": 0,
  "remarks": "",
  "sortNumber": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|approvalGroupDto|approvalGroupDto|body|true|审批分组入参|审批分组入参|
|&emsp;&emsp;groupName|分组名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 审批单管理接口


## 新增审批单


**接口地址**:`/auditForm/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增审批单</p>



**请求示例**:


```javascript
{
  "approvalId": 0,
  "formData": "",
  "id": 0,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|auditFormDto|auditFormDto|body|true|审批单入参|审批单入参|
|&emsp;&emsp;approvalId|审批id||true|integer(int64)||
|&emsp;&emsp;formData|表单数据||false|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 审批审批单


**接口地址**:`/auditForm/audit`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>审批审批单</p>



**请求示例**:


```javascript
{
  "id": 0,
  "pass": true,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|审批单审批入参|审批单审批入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;pass|是否通过||true|boolean||
|&emsp;&emsp;remarks|备注||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取审批单信息


**接口地址**:`/auditForm/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据审批单ID获取审批单信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«审批单出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|审批单出参|审批单出参|
|&emsp;&emsp;approvalId|审批id|integer(int64)||
|&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;approvalProcess|审批流程|string||
|&emsp;&emsp;auditStatus|审批状态|integer(int32)||
|&emsp;&emsp;auditStatusDesc|审批状态描述|string||
|&emsp;&emsp;button|展示操作按钮|审批单按钮出参|审批单按钮出参|
|&emsp;&emsp;&emsp;&emsp;audit|是否展示审批按钮|boolean||
|&emsp;&emsp;&emsp;&emsp;handler|是否展示办理按钮|boolean||
|&emsp;&emsp;&emsp;&emsp;revocation|是否展示撤回按钮|boolean||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;form|表单json|string||
|&emsp;&emsp;formData|表单数据|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;userId|用户id|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"approvalId": 0,
		"approvalName": "",
		"approvalProcess": "",
		"auditStatus": 0,
		"auditStatusDesc": "",
		"button": {
			"audit": true,
			"handler": true,
			"revocation": true
		},
		"createTime": "",
		"form": "",
		"formData": "",
		"id": 0,
		"nickName": "",
		"remarks": "",
		"userId": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 根据审批单id获取审批单流程信息


**接口地址**:`/auditForm/getProcess/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据审批单id获取审批单流程信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«审批单流程出参»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|审批单流程出参|
|&emsp;&emsp;approvalMode|审批方式 manual:人工审批 autoPass:自动通过|string||
|&emsp;&emsp;approvalMoreType|多人时采用方式|string||
|&emsp;&emsp;approvalType|审批类型|string||
|&emsp;&emsp;auditStatus|审批状态|integer(int32)||
|&emsp;&emsp;dealTime|处理时间|string(date-time)||
|&emsp;&emsp;detailList|审批单流程详细|array|审批单流程详细出参|
|&emsp;&emsp;&emsp;&emsp;approvalType|审批类型|string||
|&emsp;&emsp;&emsp;&emsp;auditStatus|审批状态|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;&emsp;&emsp;userId|id|integer||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;title|标题|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"approvalMode": "",
			"approvalMoreType": "",
			"approvalType": "",
			"auditStatus": 0,
			"dealTime": "",
			"detailList": [
				{
					"approvalType": "",
					"auditStatus": 0,
					"nickName": "",
					"userId": 0
				}
			],
			"id": 0,
			"remarks": "",
			"title": ""
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取审批单列表信息


**接口地址**:`/auditForm/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取审批单列表信息</p>



**请求示例**:


```javascript
{
  "auditStatus": 0,
  "createTimeFrom": "",
  "createTimeTo": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "returnFields": "",
  "type": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|auditFormQueryDto|auditFormQueryDto|body|true|审批单查询入参|审批单查询入参|
|&emsp;&emsp;auditStatus|审批状态||false|integer(int32)||
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;type|类型 self:我发起的 deal:我处理的 cc:抄送我的||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«审批单出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«审批单出参»|分页输出结果基类«审批单出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|审批单出参|
|&emsp;&emsp;&emsp;&emsp;approvalId|审批id|integer||
|&emsp;&emsp;&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;&emsp;&emsp;approvalProcess|审批流程|string||
|&emsp;&emsp;&emsp;&emsp;auditStatus|审批状态|integer||
|&emsp;&emsp;&emsp;&emsp;auditStatusDesc|审批状态描述|string||
|&emsp;&emsp;&emsp;&emsp;button|展示操作按钮|审批单按钮出参|审批单按钮出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;audit|是否展示审批按钮|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;handler|是否展示办理按钮|boolean||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;revocation|是否展示撤回按钮|boolean||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;form|表单json|string||
|&emsp;&emsp;&emsp;&emsp;formData|表单数据|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;userId|用户id|integer||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"approvalId": 0,
				"approvalName": "",
				"approvalProcess": "",
				"auditStatus": 0,
				"auditStatusDesc": "",
				"button": {
					"audit": true,
					"handler": true,
					"revocation": true
				},
				"createTime": "",
				"form": "",
				"formData": "",
				"id": 0,
				"nickName": "",
				"remarks": "",
				"userId": 0
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 撤回审批单


**接口地址**:`/auditForm/revocation`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>撤回审批单</p>



**请求示例**:


```javascript
{
  "id": 0,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|审批单撤回入参|审批单撤回入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 审批管理接口


## 新增审批


**接口地址**:`/approval/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增审批</p>



**请求示例**:


```javascript
{
  "appStatus": 0,
  "approvalGroupId": 0,
  "approvalName": "",
  "form": "",
  "icon": "",
  "id": 0,
  "processList": [
    {
      "approvalMode": "",
      "approvalMoreType": "",
      "approvalType": "",
      "ccList": [
        {
          "employeeList": [
            {
              "id": 0,
              "nickName": ""
            }
          ],
          "idList": [],
          "type": "",
          "typeDesc": ""
        }
      ],
      "employeeList": [
        {
          "id": 0,
          "nickName": ""
        }
      ],
      "emptyType": "",
      "itemList": [
        {}
      ],
      "submitType": "",
      "title": ""
    }
  ],
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|审批入参|审批入参|
|&emsp;&emsp;appStatus|图标||true|integer(int32)||
|&emsp;&emsp;approvalGroupId|审批分组id||true|integer(int64)||
|&emsp;&emsp;approvalName|审批名称||true|string||
|&emsp;&emsp;form|表单设计||false|string||
|&emsp;&emsp;icon|图标||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;processList|流程列表||false|array|审批流程json出参_1|
|&emsp;&emsp;&emsp;&emsp;approvalMode|审批方式 manual:人工审批 autoPass:自动通过||false|string||
|&emsp;&emsp;&emsp;&emsp;approvalMoreType|多人时采用方式 all:所有人 one:一名即可 order:按顺序||false|string||
|&emsp;&emsp;&emsp;&emsp;approvalType|审批类型 start:提交 approval:审批 handler:办理 cc:抄送 end:结束||false|string||
|&emsp;&emsp;&emsp;&emsp;ccList|抄送列表||false|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表||false|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述||false|string||
|&emsp;&emsp;&emsp;&emsp;employeeList|审批人为空时指定人员列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;emptyType|审批人为空时类型 autoPass:自动通过 employee:指定人员审批||false|string||
|&emsp;&emsp;&emsp;&emsp;itemList|列表||false|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表||false|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述||false|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型 all:全员 employee:指定人员||false|string||
|&emsp;&emsp;&emsp;&emsp;title|标题||false|string||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除审批


**接口地址**:`/approval/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除审批</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取审批信息


**接口地址**:`/approval/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据审批ID获取审批信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«审批详情出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|审批详情出参|审批详情出参|
|&emsp;&emsp;appStatus|审批状态|integer(int32)||
|&emsp;&emsp;approvalGroupId|审批分组id|integer(int64)||
|&emsp;&emsp;approvalName|审批名称|string||
|&emsp;&emsp;form|表单设计|string||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;processList|流程列表|array|审批流程json出参_1|
|&emsp;&emsp;&emsp;&emsp;approvalMode|审批方式 manual:人工审批 autoPass:自动通过|string||
|&emsp;&emsp;&emsp;&emsp;approvalMoreType|多人时采用方式 all:所有人 one:一名即可 order:按顺序|string||
|&emsp;&emsp;&emsp;&emsp;approvalType|审批类型 start:提交 approval:审批 handler:办理 cc:抄送 end:结束|string||
|&emsp;&emsp;&emsp;&emsp;ccList|抄送列表|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述|string||
|&emsp;&emsp;&emsp;&emsp;employeeList|审批人为空时指定人员列表|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;emptyType|审批人为空时类型 autoPass:自动通过 employee:指定人员审批|string||
|&emsp;&emsp;&emsp;&emsp;itemList|列表|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型 all:全员 employee:指定人员|string||
|&emsp;&emsp;&emsp;&emsp;title|标题|string||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;submitType|提交类型|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"appStatus": 0,
		"approvalGroupId": 0,
		"approvalName": "",
		"form": "",
		"icon": "",
		"id": 0,
		"processList": [
			{
				"approvalMode": "",
				"approvalMoreType": "",
				"approvalType": "",
				"ccList": [
					{
						"employeeList": [
							{
								"id": 0,
								"nickName": ""
							}
						],
						"idList": [],
						"type": "",
						"typeDesc": ""
					}
				],
				"employeeList": [
					{
						"id": 0,
						"nickName": ""
					}
				],
				"emptyType": "",
				"itemList": [
					{}
				],
				"submitType": "",
				"title": ""
			}
		],
		"remarks": "",
		"submitType": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑审批


**接口地址**:`/approval/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑审批</p>



**请求示例**:


```javascript
{
  "appStatus": 0,
  "approvalGroupId": 0,
  "approvalName": "",
  "form": "",
  "icon": "",
  "id": 0,
  "processList": [
    {
      "approvalMode": "",
      "approvalMoreType": "",
      "approvalType": "",
      "ccList": [
        {
          "employeeList": [
            {
              "id": 0,
              "nickName": ""
            }
          ],
          "idList": [],
          "type": "",
          "typeDesc": ""
        }
      ],
      "employeeList": [
        {
          "id": 0,
          "nickName": ""
        }
      ],
      "emptyType": "",
      "itemList": [
        {}
      ],
      "submitType": "",
      "title": ""
    }
  ],
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|审批入参|审批入参|
|&emsp;&emsp;appStatus|图标||true|integer(int32)||
|&emsp;&emsp;approvalGroupId|审批分组id||true|integer(int64)||
|&emsp;&emsp;approvalName|审批名称||true|string||
|&emsp;&emsp;form|表单设计||false|string||
|&emsp;&emsp;icon|图标||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;processList|流程列表||false|array|审批流程json出参_1|
|&emsp;&emsp;&emsp;&emsp;approvalMode|审批方式 manual:人工审批 autoPass:自动通过||false|string||
|&emsp;&emsp;&emsp;&emsp;approvalMoreType|多人时采用方式 all:所有人 one:一名即可 order:按顺序||false|string||
|&emsp;&emsp;&emsp;&emsp;approvalType|审批类型 start:提交 approval:审批 handler:办理 cc:抄送 end:结束||false|string||
|&emsp;&emsp;&emsp;&emsp;ccList|抄送列表||false|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表||false|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述||false|string||
|&emsp;&emsp;&emsp;&emsp;employeeList|审批人为空时指定人员列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;emptyType|审批人为空时类型 autoPass:自动通过 employee:指定人员审批||false|string||
|&emsp;&emsp;&emsp;&emsp;itemList|列表||false|array|审批流程json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;employeeList|员工列表||false|array|员工json出参|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|用户id||true|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickName|昵称||true|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;idList|id列表||false|array|integer|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;type|类型 parent:直属上级 dept:部门负责人 userGroup:员工组 employee:指定员工 submitter:提交人||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;typeDesc|类型描述||false|string||
|&emsp;&emsp;&emsp;&emsp;submitType|提交类型 all:全员 employee:指定人员||false|string||
|&emsp;&emsp;&emsp;&emsp;title|标题||false|string||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 修改状态


**接口地址**:`/approval/updateStatus`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改状态</p>



**请求示例**:


```javascript
{
  "appStatus": 0,
  "id": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|审批状态入参|审批状态入参|
|&emsp;&emsp;appStatus|图标||true|integer(int32)||
|&emsp;&emsp;id|id||true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 操作日志接口


## 分页获取操作日志列表


**接口地址**:`/operateLog/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取操作日志列表</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "operateName": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "requestMethod": "",
  "requestUrl": "",
  "resultCode": 0,
  "returnFields": "",
  "traceId": "",
  "userIp": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|queryDTO|queryDTO|body|true|操作日志查询入参|操作日志查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;operateName|操作名||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;requestMethod|请求方法||false|string||
|&emsp;&emsp;requestUrl|请求地址||false|string||
|&emsp;&emsp;resultCode|结果码||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;traceId|链路追踪ID||false|string||
|&emsp;&emsp;userIp|用户IP||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«操作日志出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«操作日志出参»|分页输出结果基类«操作日志出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|操作日志出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;duration|执行时长，单位：毫秒|integer||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;javaMethod|Java方法名|string||
|&emsp;&emsp;&emsp;&emsp;javaMethodArgs|Java方法的参数|string||
|&emsp;&emsp;&emsp;&emsp;operateName|操作名|string||
|&emsp;&emsp;&emsp;&emsp;requestMethod|请求方法名|string||
|&emsp;&emsp;&emsp;&emsp;requestUrl|请求地址|string||
|&emsp;&emsp;&emsp;&emsp;resultCode|结果码|integer||
|&emsp;&emsp;&emsp;&emsp;resultMsg|结果提示|string||
|&emsp;&emsp;&emsp;&emsp;startTime|开始时间|string||
|&emsp;&emsp;&emsp;&emsp;traceId|链路追踪id|string||
|&emsp;&emsp;&emsp;&emsp;userAgent|浏览器UserAgent|string||
|&emsp;&emsp;&emsp;&emsp;userIp|用户IP|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"duration": 0,
				"id": 0,
				"javaMethod": "",
				"javaMethodArgs": "",
				"operateName": "",
				"requestMethod": "",
				"requestUrl": "",
				"resultCode": 0,
				"resultMsg": "",
				"startTime": "",
				"traceId": "",
				"userAgent": "",
				"userIp": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 用户生成管理接口


## 生成员工


**接口地址**:`/genUser/generate`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>生成员工</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 用户管理接口


## 员工录入


**接口地址**:`/user/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>员工录入</p>



**请求示例**:


```javascript
{
  "alias": "",
  "bankCardNumber": "",
  "birthDate": "",
  "departmentId": 0,
  "depositBank": "",
  "directLeaderId": 0,
  "documentNumber": "",
  "documentType": "",
  "domicileAddress": "",
  "email": "",
  "emergencyContactList": [
    {
      "mobile": "",
      "nickName": "",
      "relation": ""
    }
  ],
  "entryDate": "",
  "estimatedConversionDate": "",
  "familyInfoList": [
    {
      "mobile": "",
      "nickName": "",
      "relation": ""
    }
  ],
  "firstEmploymentDate": "",
  "gender": "",
  "graduateSchool": "",
  "graduationTime": "",
  "highestDegree": "",
  "householdRegistrationType": "",
  "major": "",
  "maritalStatus": "",
  "mobile": "",
  "nationality": "",
  "nickName": "",
  "politicalStatus": "",
  "postId": 0,
  "probationPeriod": 0,
  "probationaryWage": 0,
  "professionalTitleId": 0,
  "providentFundAccount": "",
  "residentialAddress": "",
  "salary": 0,
  "socialSecurityAccountNumber": "",
  "workExperienceList": [
    {
      "company": "",
      "department": "",
      "entryDate": "",
      "leaveOfficeDate": "",
      "position": ""
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDto|userDto|body|true|员工录入入参|员工录入入参|
|&emsp;&emsp;alias|别名||false|string||
|&emsp;&emsp;bankCardNumber|银行卡号||false|string||
|&emsp;&emsp;birthDate|出生日期||false|string(date)||
|&emsp;&emsp;departmentId|部门id||true|integer(int64)||
|&emsp;&emsp;depositBank|开户银行||false|string||
|&emsp;&emsp;directLeaderId|直属领导id||false|integer(int64)||
|&emsp;&emsp;documentNumber|证件号||false|string||
|&emsp;&emsp;documentType|证件类型||false|string||
|&emsp;&emsp;domicileAddress|户籍地址||false|string||
|&emsp;&emsp;email|邮箱||false|string||
|&emsp;&emsp;emergencyContactList|紧急联系人列表||false|array|员工信息入参|
|&emsp;&emsp;&emsp;&emsp;mobile|联系方式||false|string||
|&emsp;&emsp;&emsp;&emsp;nickName|姓名||true|string||
|&emsp;&emsp;&emsp;&emsp;relation|关系||true|string||
|&emsp;&emsp;entryDate|入职日期||false|string(date)||
|&emsp;&emsp;estimatedConversionDate|预计转正日期||false|string(date)||
|&emsp;&emsp;familyInfoList|家庭信息列表||false|array|员工信息入参|
|&emsp;&emsp;&emsp;&emsp;mobile|联系方式||false|string||
|&emsp;&emsp;&emsp;&emsp;nickName|姓名||true|string||
|&emsp;&emsp;&emsp;&emsp;relation|关系||true|string||
|&emsp;&emsp;firstEmploymentDate|首次参加工作日期||false|string(date)||
|&emsp;&emsp;gender|性别||false|string||
|&emsp;&emsp;graduateSchool|毕业院校||false|string||
|&emsp;&emsp;graduationTime|毕业时间||false|string||
|&emsp;&emsp;highestDegree|最高学历||false|string||
|&emsp;&emsp;householdRegistrationType|户籍类型||false|string||
|&emsp;&emsp;major|所学专业||false|string||
|&emsp;&emsp;maritalStatus|婚姻状况||false|string||
|&emsp;&emsp;mobile|手机号||false|string||
|&emsp;&emsp;nationality|民族||false|string||
|&emsp;&emsp;nickName|姓名||true|string||
|&emsp;&emsp;politicalStatus|政治面貌||false|string||
|&emsp;&emsp;postId|职位id||true|integer(int64)||
|&emsp;&emsp;probationPeriod|试用期(月)||false|integer(int32)||
|&emsp;&emsp;probationaryWage|试用期薪酬(月)||false|number||
|&emsp;&emsp;professionalTitleId|职称id||false|integer(int64)||
|&emsp;&emsp;providentFundAccount|公积金账号||false|string||
|&emsp;&emsp;residentialAddress|居住地址||false|string||
|&emsp;&emsp;salary|转正薪酬(月)||false|number||
|&emsp;&emsp;socialSecurityAccountNumber|社保账号||false|string||
|&emsp;&emsp;workExperienceList|工作经历列表||false|array|员工工作经历入参|
|&emsp;&emsp;&emsp;&emsp;company|单位名称||true|string||
|&emsp;&emsp;&emsp;&emsp;department|部门||true|string||
|&emsp;&emsp;&emsp;&emsp;entryDate|入职日期||true|string||
|&emsp;&emsp;&emsp;&emsp;leaveOfficeDate|离职日期||true|string||
|&emsp;&emsp;&emsp;&emsp;position|职位||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 根据部门id查询员工列表信息


**接口地址**:`/user/getByDepartmentId/{departmentId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据部门id查询员工列表信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|departmentId|部门id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«员工列表出参»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|员工列表出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;email|邮箱|string||
|&emsp;&emsp;employeeNumber|工号|string||
|&emsp;&emsp;gender|性别|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;mobile|手机号|string||
|&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;postName|职位名称|string||
|&emsp;&emsp;titleName|职称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"createTime": "",
			"deptName": "",
			"email": "",
			"employeeNumber": "",
			"gender": "",
			"id": 0,
			"mobile": "",
			"nickName": "",
			"postName": "",
			"titleName": ""
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 根据id查询员工卡片信息


**接口地址**:`/user/getCard/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据id查询员工卡片信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«员工卡片信息出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|员工卡片信息出参|员工卡片信息出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;directLeader|直属领导|string||
|&emsp;&emsp;directLeaderId|直属领导id|integer(int64)||
|&emsp;&emsp;email|邮箱|string||
|&emsp;&emsp;employeeNumber|工号|string||
|&emsp;&emsp;gender|性别|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;mobile|手机号|string||
|&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;postName|职位名称|string||
|&emsp;&emsp;titleName|职称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"deptName": "",
		"directLeader": "",
		"directLeaderId": 0,
		"email": "",
		"employeeNumber": "",
		"gender": "",
		"id": 0,
		"mobile": "",
		"nickName": "",
		"postName": "",
		"titleName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取预计转正日期


**接口地址**:`/user/getEstimatedConversionDate`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取预计转正日期</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|entryDate|entryDate|query|true|string(date)||
|probationPeriod|probationPeriod|query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«LocalDate»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|string(date)|string(date)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取员工列表信息


**接口地址**:`/user/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取员工列表信息</p>



**请求示例**:


```javascript
{
  "departmentId": "",
  "email": "",
  "employeeNumber": "",
  "mobile": "",
  "nickName": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "py": "",
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|员工查询入参|员工查询入参|
|&emsp;&emsp;departmentId|部门id||false|string||
|&emsp;&emsp;email|邮箱||false|string||
|&emsp;&emsp;employeeNumber|工号||false|string||
|&emsp;&emsp;mobile|手机号||false|string||
|&emsp;&emsp;nickName|姓名||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;py|拼音首字母||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«员工列表出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«员工列表出参»|分页输出结果基类«员工列表出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|员工列表出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;&emsp;&emsp;email|邮箱|string||
|&emsp;&emsp;&emsp;&emsp;employeeNumber|工号|string||
|&emsp;&emsp;&emsp;&emsp;gender|性别|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;mobile|手机号|string||
|&emsp;&emsp;&emsp;&emsp;nickName|姓名|string||
|&emsp;&emsp;&emsp;&emsp;postName|职位名称|string||
|&emsp;&emsp;&emsp;&emsp;titleName|职称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"deptName": "",
				"email": "",
				"employeeNumber": "",
				"gender": "",
				"id": 0,
				"mobile": "",
				"nickName": "",
				"postName": "",
				"titleName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 登录管理接口


## 获取图形验证码


**接口地址**:`/getCaptchaImage`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>获取图形验证码</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«Map«string,string»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取登录用户信息


**接口地址**:`/getInfo`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取登录用户信息</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«Map«string,object»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 用户登录


**接口地址**:`/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户登录</p>



**请求示例**:


```javascript
{
  "userName": "",
  "userPwd": "",
  "uuid": "",
  "verCode": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|dto|dto|body|true|用户登录入参|用户登录入参|
|&emsp;&emsp;userName|用户名||true|string||
|&emsp;&emsp;userPwd|密码||true|string||
|&emsp;&emsp;uuid|验证码uuid||true|string||
|&emsp;&emsp;verCode|验证码||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 用户退出


**接口地址**:`/logout`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户退出</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 职位管理接口


## 新增职位


**接口地址**:`/post/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增职位</p>



**请求示例**:


```javascript
{
  "id": 0,
  "postName": "",
  "remarks": "",
  "roleIdList": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postDto|postDto|body|true|职位入参|职位入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;postName|职位名称||true|string||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;roleIdList|角色id列表||false|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除职位


**接口地址**:`/post/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除职位</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«int»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|integer(int32)|integer(int32)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取职位信息


**接口地址**:`/post/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据职位ID获取职位信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«职位出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|职位出参|职位出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;postName|职位名称|string||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;roleIdList|角色id列表|array|integer(int64)|
|&emsp;&emsp;updateTime|修改时间|string(date-time)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"postName": "",
		"remarks": "",
		"roleIdList": [],
		"updateTime": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取职位列表信息


**接口地址**:`/post/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取职位列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "postName": "",
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postQueryDto|postQueryDto|body|true|职位查询入参|职位查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;postName|职位名称||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«职位出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«职位出参»|分页输出结果基类«职位出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|职位出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;postName|职位名称|string||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;roleIdList|角色id列表|array|integer|
|&emsp;&emsp;&emsp;&emsp;updateTime|修改时间|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"postName": "",
				"remarks": "",
				"roleIdList": [],
				"updateTime": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑职位


**接口地址**:`/post/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑职位</p>



**请求示例**:


```javascript
{
  "id": 0,
  "postName": "",
  "remarks": "",
  "roleIdList": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postDto|postDto|body|true|职位入参|职位入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;postName|职位名称||true|string||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;roleIdList|角色id列表||false|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 职称管理接口


## 新增职称


**接口地址**:`/professionalTitle/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增职称</p>



**请求示例**:


```javascript
{
  "id": 0,
  "remarks": "",
  "titleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|professionalTitleDto|professionalTitleDto|body|true|职称入参|职称入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;titleName|职称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除职称


**接口地址**:`/professionalTitle/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除职称</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«int»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|integer(int32)|integer(int32)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取职称信息


**接口地址**:`/professionalTitle/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据职称ID获取职称信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«职称出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|职称出参|职称出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;titleName|职称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"remarks": "",
		"titleName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取职称列表信息


**接口地址**:`/professionalTitle/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取职称列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "returnFields": "",
  "titleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|professionalTitleQueryDto|professionalTitleQueryDto|body|true|职称查询入参|职称查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;titleName|职称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«职称出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«职称出参»|分页输出结果基类«职称出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|职称出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;titleName|职称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"remarks": "",
				"titleName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑职称


**接口地址**:`/professionalTitle/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑职称</p>



**请求示例**:


```javascript
{
  "id": 0,
  "remarks": "",
  "titleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|professionalTitleDto|professionalTitleDto|body|true|职称入参|职称入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;titleName|职称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 菜单管理接口


## 新增菜单


**接口地址**:`/menu/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增菜单</p>



**请求示例**:


```javascript
{
  "component": "",
  "icon": "",
  "id": 0,
  "isCache": true,
  "isEnable": true,
  "isFrame": true,
  "isShow": true,
  "menuCode": "",
  "menuName": "",
  "menuType": "",
  "parentId": 0,
  "query": "",
  "sortNumber": 0,
  "url": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|menuDto|menuDto|body|true|菜单入参|菜单入参|
|&emsp;&emsp;component|组件路径||false|string||
|&emsp;&emsp;icon|图标||false|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isCache|是否缓存||true|boolean||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;isFrame|是否外链||true|boolean||
|&emsp;&emsp;isShow|是否显示||true|boolean||
|&emsp;&emsp;menuCode|菜单编码||true|string||
|&emsp;&emsp;menuName|菜单名称||true|string||
|&emsp;&emsp;menuType|菜单类型 M.目录 C.菜单 I.接口||true|string||
|&emsp;&emsp;parentId|父id||true|integer(int64)||
|&emsp;&emsp;query|路由参数||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;url|地址||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除菜单


**接口地址**:`/menu/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除菜单</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«int»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|integer(int32)|integer(int32)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取菜单信息


**接口地址**:`/menu/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据菜单ID获取菜单信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«菜单出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|菜单出参|菜单出参|
|&emsp;&emsp;children|子菜单集合|array|菜单出参|
|&emsp;&emsp;component|组件路径|string||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isCache|是否缓存|boolean||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;isFrame|是否外链|boolean||
|&emsp;&emsp;isShow|是否显示|boolean||
|&emsp;&emsp;menuCode|菜单编码|string||
|&emsp;&emsp;menuName|菜单名称|string||
|&emsp;&emsp;menuType|菜单类型 M.目录 C.菜单 I.接口|string||
|&emsp;&emsp;parentId|父id|integer(int64)||
|&emsp;&emsp;query|路由参数|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|&emsp;&emsp;updateTime|修改时间|string(date-time)||
|&emsp;&emsp;url|地址|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"children": [
			{
				"children": [
					{}
				],
				"component": "",
				"createTime": "",
				"icon": "",
				"id": 0,
				"isCache": true,
				"isEnable": true,
				"isFrame": true,
				"isShow": true,
				"menuCode": "",
				"menuName": "",
				"menuType": "",
				"parentId": 0,
				"query": "",
				"sortNumber": 0,
				"updateTime": "",
				"url": ""
			}
		],
		"component": "",
		"createTime": "",
		"icon": "",
		"id": 0,
		"isCache": true,
		"isEnable": true,
		"isFrame": true,
		"isShow": true,
		"menuCode": "",
		"menuName": "",
		"menuType": "",
		"parentId": 0,
		"query": "",
		"sortNumber": 0,
		"updateTime": "",
		"url": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取所有菜单树


**接口地址**:`/menu/getTree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有菜单树</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«菜单出参»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|菜单出参|
|&emsp;&emsp;children|子菜单集合|array|菜单出参|
|&emsp;&emsp;component|组件路径|string||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;icon|图标|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isCache|是否缓存|boolean||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;isFrame|是否外链|boolean||
|&emsp;&emsp;isShow|是否显示|boolean||
|&emsp;&emsp;menuCode|菜单编码|string||
|&emsp;&emsp;menuName|菜单名称|string||
|&emsp;&emsp;menuType|菜单类型 M.目录 C.菜单 I.接口|string||
|&emsp;&emsp;parentId|父id|integer(int64)||
|&emsp;&emsp;query|路由参数|string||
|&emsp;&emsp;sortNumber|排序|integer(int32)||
|&emsp;&emsp;updateTime|修改时间|string(date-time)||
|&emsp;&emsp;url|地址|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"children": [
				{
					"children": [
						{}
					],
					"component": "",
					"createTime": "",
					"icon": "",
					"id": 0,
					"isCache": true,
					"isEnable": true,
					"isFrame": true,
					"isShow": true,
					"menuCode": "",
					"menuName": "",
					"menuType": "",
					"parentId": 0,
					"query": "",
					"sortNumber": 0,
					"updateTime": "",
					"url": ""
				}
			],
			"component": "",
			"createTime": "",
			"icon": "",
			"id": 0,
			"isCache": true,
			"isEnable": true,
			"isFrame": true,
			"isShow": true,
			"menuCode": "",
			"menuName": "",
			"menuType": "",
			"parentId": 0,
			"query": "",
			"sortNumber": 0,
			"updateTime": "",
			"url": ""
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取当前登录用户的菜单


**接口地址**:`/menu/getUserMenu`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前登录用户的菜单</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«List«路由配置信息出参»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|array|路由配置信息出参|
|&emsp;&emsp;alwaysShow||boolean||
|&emsp;&emsp;children||array|路由配置信息出参|
|&emsp;&emsp;component||string||
|&emsp;&emsp;hidden||boolean||
|&emsp;&emsp;meta||路由显示信息出参|路由显示信息出参|
|&emsp;&emsp;&emsp;&emsp;icon||string||
|&emsp;&emsp;&emsp;&emsp;link||string||
|&emsp;&emsp;&emsp;&emsp;noCache||boolean||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;name||string||
|&emsp;&emsp;path||string||
|&emsp;&emsp;query||string||
|&emsp;&emsp;redirect||string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"alwaysShow": true,
			"children": [
				{
					"alwaysShow": true,
					"children": [
						{}
					],
					"component": "",
					"hidden": true,
					"meta": {
						"icon": "",
						"link": "",
						"noCache": true,
						"title": ""
					},
					"name": "",
					"path": "",
					"query": "",
					"redirect": ""
				}
			],
			"component": "",
			"hidden": true,
			"meta": {
				"icon": "",
				"link": "",
				"noCache": true,
				"title": ""
			},
			"name": "",
			"path": "",
			"query": "",
			"redirect": ""
		}
	],
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑菜单


**接口地址**:`/menu/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑菜单</p>



**请求示例**:


```javascript
{
  "component": "",
  "icon": "",
  "id": 0,
  "isCache": true,
  "isEnable": true,
  "isFrame": true,
  "isShow": true,
  "menuCode": "",
  "menuName": "",
  "menuType": "",
  "parentId": 0,
  "query": "",
  "sortNumber": 0,
  "url": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|menuDto|menuDto|body|true|菜单入参|菜单入参|
|&emsp;&emsp;component|组件路径||false|string||
|&emsp;&emsp;icon|图标||false|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isCache|是否缓存||true|boolean||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;isFrame|是否外链||true|boolean||
|&emsp;&emsp;isShow|是否显示||true|boolean||
|&emsp;&emsp;menuCode|菜单编码||true|string||
|&emsp;&emsp;menuName|菜单名称||true|string||
|&emsp;&emsp;menuType|菜单类型 M.目录 C.菜单 I.接口||true|string||
|&emsp;&emsp;parentId|父id||true|integer(int64)||
|&emsp;&emsp;query|路由参数||false|string||
|&emsp;&emsp;sortNumber|排序||true|integer(int32)||
|&emsp;&emsp;url|地址||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 角色管理接口


## 新增角色


**接口地址**:`/role/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增角色</p>



**请求示例**:


```javascript
{
  "id": 0,
  "menuIdList": [],
  "remarks": "",
  "roleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleDto|roleDto|body|true|角色入参|角色入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;menuIdList|菜单id列表||false|array|integer(int64)|
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;roleName|角色名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除角色


**接口地址**:`/role/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除角色</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«int»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|integer(int32)|integer(int32)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取角色信息


**接口地址**:`/role/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据角色ID获取角色信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«角色出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|角色出参|角色出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;menuIdList|菜单id列表|array|integer(int64)|
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;roleName|角色名称|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"id": 0,
		"menuIdList": [],
		"remarks": "",
		"roleName": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取角色列表信息


**接口地址**:`/role/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取角色列表信息</p>



**请求示例**:


```javascript
{
  "createTimeFrom": "",
  "createTimeTo": "",
  "id": 0,
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "remarks": "",
  "returnFields": "",
  "roleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleQueryDto|roleQueryDto|body|true|角色查询入参|角色查询入参|
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int64)||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||
|&emsp;&emsp;roleName|角色名称||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«角色出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«角色出参»|分页输出结果基类«角色出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|角色出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;menuIdList|菜单id列表|array|integer|
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;roleName|角色名称|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"id": 0,
				"menuIdList": [],
				"remarks": "",
				"roleName": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑角色


**接口地址**:`/role/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑角色</p>



**请求示例**:


```javascript
{
  "id": 0,
  "menuIdList": [],
  "remarks": "",
  "roleName": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleDto|roleDto|body|true|角色入参|角色入参|
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;menuIdList|菜单id列表||false|array|integer(int64)|
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;roleName|角色名称||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 车辆管理接口


## 新增车辆


**接口地址**:`/car/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增车辆</p>



**请求示例**:


```javascript
{
  "carName": "",
  "carNumber": "",
  "id": 0,
  "isEnable": true,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|carDto|carDto|body|true|车辆入参|车辆入参|
|&emsp;&emsp;carName|车辆名称||true|string||
|&emsp;&emsp;carNumber|车牌号码||false|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除车辆


**接口地址**:`/car/delete`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除车辆</p>



**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|idList|idList|body|true|array|integer(int64)|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«boolean»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|boolean||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": true,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取车辆信息


**接口地址**:`/car/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据车辆ID获取车辆信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«车辆出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|车辆出参|车辆出参|
|&emsp;&emsp;carName|车辆名称|string||
|&emsp;&emsp;carNumber|车牌号码|string||
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;remarks|备注|string||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"carName": "",
		"carNumber": "",
		"createTime": "",
		"id": 0,
		"isEnable": true,
		"remarks": ""
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取车辆列表信息


**接口地址**:`/car/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取车辆列表信息</p>



**请求示例**:


```javascript
{
  "carName": "",
  "carNumber": "",
  "createTimeFrom": "",
  "createTimeTo": "",
  "id": 0,
  "isEnable": true,
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "remarks": "",
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|carQueryDto|carQueryDto|body|true|车辆查询入参|车辆查询入参|
|&emsp;&emsp;carName|车辆名称||false|string||
|&emsp;&emsp;carNumber|车牌号码||false|string||
|&emsp;&emsp;createTimeFrom|创建时间起||false|string(date-time)||
|&emsp;&emsp;createTimeTo|创建时间止||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||false|boolean||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«车辆出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«车辆出参»|分页输出结果基类«车辆出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|车辆出参|
|&emsp;&emsp;&emsp;&emsp;carName|车辆名称|string||
|&emsp;&emsp;&emsp;&emsp;carNumber|车牌号码|string||
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;isEnable|是否启用|boolean||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"carName": "",
				"carNumber": "",
				"createTime": "",
				"id": 0,
				"isEnable": true,
				"remarks": ""
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑车辆


**接口地址**:`/car/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑车辆</p>



**请求示例**:


```javascript
{
  "carName": "",
  "carNumber": "",
  "id": 0,
  "isEnable": true,
  "remarks": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|carDto|carDto|body|true|车辆入参|车辆入参|
|&emsp;&emsp;carName|车辆名称||true|string||
|&emsp;&emsp;carNumber|车牌号码||false|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;isEnable|是否启用||true|boolean||
|&emsp;&emsp;remarks|备注||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


# 部门管理接口


## 新增部门


**接口地址**:`/department/add`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新增部门</p>



**请求示例**:


```javascript
{
  "deptName": "",
  "id": 0,
  "parentId": 0,
  "remarks": "",
  "userId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|departmentDto|departmentDto|body|true|部门入参|部门入参|
|&emsp;&emsp;deptName|部门名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;parentId|父id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;userId|负责人id||true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 删除部门


**接口地址**:`/department/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除部门</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«int»|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|integer(int32)|integer(int32)|
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": 0,
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 获取部门信息


**接口地址**:`/department/get/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据部门ID获取部门信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«部门出参»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|部门出参|部门出参|
|&emsp;&emsp;createTime|创建时间|string(date-time)||
|&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;nickName|负责人|string||
|&emsp;&emsp;parentId|父id|integer(int64)||
|&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;userId|负责人id|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"createTime": "",
		"deptName": "",
		"id": 0,
		"nickName": "",
		"parentId": 0,
		"remarks": "",
		"userId": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 分页获取部门列表信息


**接口地址**:`/department/query`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取部门列表信息</p>



**请求示例**:


```javascript
{
  "deptName": "",
  "orderBy": "",
  "pageNo": 0,
  "pageSize": 0,
  "returnFields": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|departmentQueryDto|departmentQueryDto|body|true|部门查询入参|部门查询入参|
|&emsp;&emsp;deptName|部门名称||false|string||
|&emsp;&emsp;orderBy|排序||false|string||
|&emsp;&emsp;pageNo|第几页||false|integer(int32)||
|&emsp;&emsp;pageSize|每页多少数据||false|integer(int32)||
|&emsp;&emsp;returnFields|指定返回哪些字段||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result«分页输出结果基类«部门出参»»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|分页输出结果基类«部门出参»|分页输出结果基类«部门出参»|
|&emsp;&emsp;hasNextPage||boolean||
|&emsp;&emsp;pageNo|第几页|integer(int64)||
|&emsp;&emsp;pageSize|每页多少数据|integer(int64)||
|&emsp;&emsp;pages|总页数|integer(int64)||
|&emsp;&emsp;records|返回记录列表|array|部门出参|
|&emsp;&emsp;&emsp;&emsp;createTime|创建时间|string||
|&emsp;&emsp;&emsp;&emsp;deptName|部门名称|string||
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;nickName|负责人|string||
|&emsp;&emsp;&emsp;&emsp;parentId|父id|integer||
|&emsp;&emsp;&emsp;&emsp;remarks|备注|string||
|&emsp;&emsp;&emsp;&emsp;userId|负责人id|integer||
|&emsp;&emsp;total|总记录数|integer(int64)||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"hasNextPage": true,
		"pageNo": 0,
		"pageSize": 0,
		"pages": 0,
		"records": [
			{
				"createTime": "",
				"deptName": "",
				"id": 0,
				"nickName": "",
				"parentId": 0,
				"remarks": "",
				"userId": 0
			}
		],
		"total": 0
	},
	"message": "",
	"success": true,
	"timestamp": 0
}
```


## 编辑部门


**接口地址**:`/department/update`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>编辑部门</p>



**请求示例**:


```javascript
{
  "deptName": "",
  "id": 0,
  "parentId": 0,
  "remarks": "",
  "userId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|departmentDto|departmentDto|body|true|部门入参|部门入参|
|&emsp;&emsp;deptName|部门名称||true|string||
|&emsp;&emsp;id|id||true|integer(int64)||
|&emsp;&emsp;parentId|父id||true|integer(int64)||
|&emsp;&emsp;remarks|备注||false|string||
|&emsp;&emsp;userId|负责人id||true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Rest result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code|结果代码|integer(int32)|integer(int32)|
|data|结果对象|object||
|message|错误说明|string||
|success|操作标识|boolean||
|timestamp|当前时间戳|integer(int64)|integer(int64)|


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"success": true,
	"timestamp": 0
}
```