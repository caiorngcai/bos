# bos
一.bos概述
====
此项目为本人练习javaweb和ssh框架所做的java后台项目，是一个物流管理系统,具有最基本的bos系统功能。架构为Tomcat+mysql+eclipse+ssh框架，是一个完整的项目。
旨在熟悉java后台的开发工作流程。
整个项目分为domain数据层，service服务层，action前端控制层，dao数据库交互层，util通用方法层。

二.说明
======
>>此项目原先是在idea中完成的，使用maven构建项目，因为本人不熟悉idea，故遇到许多坑无法解决，后来把此项目的编码工作移到eclipse中，
>>前期已经完成了最基本用户登录，登出，修改密码等功能。


三.项目进度时间线
======前期准备部分：包括系统的通用要求，登陆，登出，修改密码等
>>17/10/30
 完成从idea到eclipse的项目构建，完成maven的依赖构建，包名划分等工作。并在eclipse中安装Tomcat，把项目发布到Tomcat中。
 完成ssh框架配置文件dtd文件的引入和具体内容的书写。包括struts的请求和转发的处理，spring框架的主配置文件，确定使用注解开发模式。
 使用easyUI搭建主页面和登录页面，用ztree框架做出了导航栏。
>>17/10/31
完成daoc层通用操作的抽取，包括基础的增删改查操作。action层的抽取，使得在在获取model对象时具有通用性，减少了重复的代码。
完成用户登录和登出的功能。完成用户修改密码的功能，使用ajax请求，无刷新修改密码。
自定义了一个登陆的拦截器，对所有请求进行拦截，确保未登陆用户不能进行操作
======物流系统基础功能部分：主要涉及取派员，区域，定区，分区四个部分。
>>17/11/01
1.为整个bos项目的基础部分设计了数据库表，其中包括取派员表，区域表，管理分区，和管理定区表，其中包括设计表与表之间的关系，使用powerdesiner
描述表的关系，并生产创建表的sql语句，使用myeclipse的插件反转生产hibernate的实体类和映射表。
2.实现了取派员的添加前端页面，使用easyui的校验规则，并自定义了一个手机号码的校验规则。
 完成取派员添加功能的服务器端实现。
 >>17/11/02
 1.使用easyui的datagrid功能来实现取派员的列表展示。使用ajax请求获得取派员信息，并在easyui的datagrid的列表中展示。
 2.在util层的基础类中封装了一个pagebean，用来表示通用的分页数据的信息。
 3.在basedao层扩展一个通用的分页查询方法，并在action层定义一个类来接受取派员的列表展示请求。
 
a. 问题：struts2用属性驱动时无法正确接受到参数，报java.lang.NoSuchFieldException: resourceEntries异常错误，上网查找到的时候说
 Tomcat版本问题，后来仔细检查发现没有问题。
 解决：在action中在定义属性时，没有对应的set和get方法，导致无法接受参数。
 b.问题：在使用eclipse的github插件上传代码时出现rejected non fast forward错误。
   解决：因为代码没有合并，需要pull一下同步，然后在push.
 
>>17/11/03
1.完成取派员基本功能的批量删除功能
2.完成取派员的基本功能的修改功能，涉及到easyUI的双击触发事件
>>17/11/04
 1.主要完成基础功能模块区域部分的添加功能和展示功能。
 2.使用Apache的POI，POI包能够使得java对微软的办公软件数据进行读写操作，本项目主要使用的是execl表的操作，
 从execl表单中批量读取数据并保存到数据库相应的表中，并使用pinyin4j包完成城市名称和拼音码的转化，主要是为了定区模块的
 搜索使用。
 3.完成了区域模块的分页展示功能，并在此完成分页查询代码的重构工作，由于action层中有多个部分需要分页查询的操作，
 故把相关的重复代码抽取到baseaction中，具体包括分页bean的创建更具套用性，并抽取了一个函数，实现json和java对象的互相转化。

 
