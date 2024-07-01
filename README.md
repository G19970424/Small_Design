# 系统说明

## 系统运行环境
+ java 8+
+ mysql 8+
+ ubuntu 

## 版本
+ 1.0  更新时间 2024年7月1日22:07:16

### 版本库


# 系统功能介绍
## 前后端统一口径
### 统一返回接口数据  
`cn.com.small_design.common.response.RestResponse<T>`
 
 注释 | 字段 | 数据类型

 状态码 | code | int

 响应消息 | message | String

 响应数据 | data | T

 数据数量 | count | int

 访问是否成功 | success | boolean

 响应时间 | dateTime | Date

### 前后端接口一致
* 系统整体异常由后端统一处理，其他系统异常交由前端处理，请求成功状态码统一 200，是否访问成功字段为true，请求失败状态码不唯一，响应消息交由后端返回，是否访问成功字段为false *

## 用户管理
### 登录

### 注册

### 注销

### 登出


### 