## 平台简介

- 素焉人事管理系统，使用技术：Spring Boot + Vue3。
- 登录目前使用数据库方式，未采用Redis。

### 系统功能
|     | 模块   |  功能   | 描述                                                                                                                           |
|-----|------|:-----:|------------------------------------------------------------------------------------------------------------------------------|
| 🚀  | 员工资料 | 基本资料  | 编号、姓名、性别、出生日期、身份证号、婚姻状况、民族、电子邮箱、手机号、联系地址、部门、职位、职称、分机号、直属上级、基本工资、聘用形式、最高学历、所属专业、毕业院校、入职时间、在职状态、工号、人员类型、合同期限。                  |
| 🚀  |      | 高级资料  | 工资信息：工资月份、应发工资、工资明细、备注。<br/>奖惩资料：奖惩时间、奖惩分数、奖惩原因、备注。<br/>调动资料：调动时间、调前部门、调前职位、调动原因、调后部门、调后职位、备注。<br/>调薪资料：调薪时间、调钱新增、调后薪资、调薪原因。 |
| 🚀  | 公司管理 | 职位管理  | 编号、名称、备注。                                                                                                                    |
| 🚀  |      | 职称管理  | 编号、名称、备注。                                                                                                                    |
| 🚀  |      | 部门管理  | 编号、名称、负责人、备注。                                                                                                                |
| 🚀  | 系统管理 | 成员与部门 | 部门管理：名称、上级部门、负责人。                                                                                                            |                                                                                                                  |
| 🚀  |      | 角色管理  | 编号、名称、权限、备注。                                                                                                                 |
| 🚀  |      | 公司管理  | 编号、名称、是否隐藏手机号码、是否开启水印、备注。                                                                                                    |

### 功能列表

|     | 功能   | 是否完成 |
|-----|------|:----:|
| 🚀  | 菜单管理 |  √   |
| 🚀  | 职位管理 |  √   |
| 🚀  | 职称管理 |  √   |
| 🚀  | 部门管理 |  x   |
| 🚀  | 用户管理 |  x   |
| 🚀  | 角色管理 |  x   |