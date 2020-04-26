# 在线辅助学习系统

## 系统结构图
![](https://img-blog.csdnimg.cn/2020042613185594.png "系统结构图")



## 方案设计

### 开发工具
* **Intellij IDEA**
* **Navicat**
### 数据库
* **mysql** 5.7.25

创建数据库表  
![123](https://img-blog.csdnimg.cn/20200426135712503.png "创建数据库")

# 说明
## 1.tomcat中文乱码
Server > VM options 设置为 `-Dfile.encoding=UTF-8` 重启tomcat
## 2.mybatis generator生成
运行maven命令 `mvn mybatis-generator:generate`
## 3.设置自动导入包
file > setting > Editor > General > Auto Import, 按照下图勾选，点击Apply
![](https://img-blog.csdnimg.cn/20200426112654891.png "设置自动导入包")