# bos
一.bos概述
======
此项目为本人练习javaweb和ssh框架所做的java后台项目，是一个物流管理系统,具有最基本的bos系统功能。架构为Tomcat+mysql+eclipse+ssh框架，是一个完整的项目。
旨在熟悉java后台的开发工作流程。
整个项目分为domain数据层，service服务层，action前端控制层，dao数据库交互层，util通用方法层。

二.说明
=======
此项目原先是在idea中完成的，使用maven构建项目，因为本人不熟悉idea，故遇到许多坑无法解决，后来把此项目的编码工作移到eclipse中，
前期已经完成了最基本用户登录，登出，修改密码等功能。


三.项目进度时间线  
=======
1.前期准备部分：包括系统的通用要求，登陆，登出，修改密码等    
====
17/10/30
1. 完成从idea到eclipse的项目构建，完成maven的依赖构建，包名划分等工作。并在eclipse中安装Tomcat，把项目发布到Tomcat中。
2. 完成ssh框架配置文件dtd文件的引入和具体内容的书写。包括struts的请求和转发的处理，spring框架的主配置文件，确定使用注解开发模式。
 3.使用easyUI搭建主页面和登录页面，用ztree框架做出了导航栏。  
 
17/10/31     
1.完成daoc层通用操作的抽取，包括基础的增删改查操作。action层的抽取，使得在在获取model对象时具有通用性，减少了重复的代码。  
2.完成用户登录和登出的功能。完成用户修改密码的功能，使用ajax请求，无刷新修改密码。  
3.自定义了一个登陆的拦截器，对所有请求进行拦截，确保未登陆用户不能进行操作   
4.物流系统基础功能部分：主要涉及取派员，区域，定区，分区四个部分。     

2.物流系统基础功能部分：取派员，区域，分区，定区。
====
17/11/01  
1.为整个bos项目的基础部分设计了数据库表，其中包括取派员表，区域表，管理分区，和管理定区表，其中包括设计表与表之间的关系，使用  powerdesiner描述表的关系，并生产创建表的sql语句，使用myeclipse的插件反转生产hibernate的实体类和映射表
2.实现了取派员的添加前端页面，使用easyui的校验规则，并自定义了一个手机号码的校验规则。     
3.完成取派员添加功能的服务器端实现。     

 17/11/02    
 1.使用easyui的datagrid功能来实现取派员的列表展示。使用ajax请求获得取派员信息，并在easyui的datagrid的列表中展示。    
 2.在util层的基础类中封装了一个pagebean，用来表示通用的分页数据的信息。   
 3.在basedao层扩展一个通用的分页查询方法，并在action层定义一个类来接受取派员的列表展示请求。   
 
a. 问题：struts2用属性驱动时无法正确接受到参数，报java.lang.NoSuchFieldException: resourceEntries异常错误，  
上网查找到的时候说Tomcat版本问题，后来仔细检查发现没有问题。  
 解决：在action中在定义属性时，没有对应的set和get方法，导致无法接受参数。   
 b.问题：在使用eclipse的github插件上传代码时出现rejected non fast forward错误。     
   解决：因为代码没有合并，需要pull一下同步，然后在push.     
 
17/11/03    
1.完成取派员基本功能的批量删除功能     
2.完成取派员的基本功能的修改功能，涉及到easyUI的双击触发事件

17/11/04    
 1.主要完成基础功能模块区域部分的添加功能和展示功能。    
 2.使用Apache的POI，POI包能够使得java对微软的办公软件数据进行读写操作，本项目主要使用的是execl表的操作，   
 从execl表单中批量读取数据并保存到数据库相应的表中，并使用pinyin4j包完成城市名称和拼音码的转化，主要是为了定区模块的搜索使用。      
 3.完成了区域模块的分页展示功能，并在此完成分页查询代码的重构工作，由于action层中有多个部分需要分页查询的操作，   
 故把相关的重复代码抽取到baseaction中，具体包括分页bean的创建更具套用性，并抽取了一个函数，实现json和java对象的互相转化。   
    
 17/11/05    
 1.完成了基础数据模块分区数据功能的一小部分，即分区数据添加时与区域数据关联的数据下拉选择框部分  
 2.熟悉使用easyui的combobox功能构建页面，使得分区页面添加窗口有一个下拉栏可以选择区域数据（json数据）  
 3.完成区域数据下拉栏的服务端功能，使得下拉栏可以通过模糊查询过滤区域数据。并用json-lib把数据库的数据封装成json返回到前端。   
 
 a.问题：在json封装时无法返回key为name的数据，因为region实体类没有name这个字段，但是前端的下拉栏需要用到这个字段。  
   解决：只需要在region实体类中添加一个返回getName()即可，因为json-lib是通过get方法来封装数据的   
 b.问题：json异常死循环问题，报there is a cycle in the hierarchy错误信息    
   解决 ：出现这种问题的原因是json里面的对象相互引用，导致死循环，只需要在封装某一个对象时把另一个对象排除在封装之外即可。   
     
 17/11/06
 1.修改了baseaction类中的分页查询方法，使其能够满足带模糊过滤条件的分页查询    
a.问题：json封装的另外一个异常：因为懒加载导致关联查询时无法返回返回关联的对象问题    
  解决：去除hibernate的默认懒加载，立即返回封装对象。注意这种情况下的问题，opensessioninviewfilter过滤器是无法解决    
       只能通过去掉懒加载解决   
     
17/11/07    
1.分区数据的导出功能，再次使用Apache的POI包，把分区数据导出为execl表，注意servlet数据下载时头信息的设置   
2.定区添加功能前端下拉栏选择的数据提供，完成这部分的服务器代码工作，其中包含一个combobox,和一个grid的数据展现形式   
3.完成定区添加功能的服务端实现，比较简单，主要是数据提供时表单id重复的问题的解决。   
4.完成定区数据分页查询功能，因前面做过多次，故比较简单   
a.问题：前端表单多个id重复问题的解决   
  解决：把其中一个重复id改名，在其对应的实体类中提供一个相应的get方法，然后json-lib会根据该get方法返回数据，即能在    
  前端表单获取到数据。   
      
17/11/08--17/11/13       
这段时间由于外出未带个人电脑，故此项目中断一段时间。之前已经完成了整个物流项目的基础数据设置部分，可以为整个项目提供基础数据  
接下来开始取派部分的工作。   
   
 3.物流管理系统取派部分，主要完成取派员与定区数据的的结合，主要涉及业务通知单，工作单和工作，实现取派员取派工作的记录.   
 ====   
17/11//14    
1.bos项目需要一个客户管理系统，为了方便管理，把客户管理系统crm独立为一个子系统，并把crm子系统作为webservice发布供bos使用。    
（这个webservice的源代码在此：https://github.com/caiorngcai/crm_bos）    
2.今日主要使用了Apache的cxf框架发布crm服务，cxf框架可以无缝结合spring使用。使用时注意注册服务对象和注册客户端代理对象。       
3.完成了crm项目的部分工作，并使用java自带的工具wsimport生成客户端接口，crm项目拥有查询客户，查询与定区关联的客户以及未关联的用户。   
17/11/15    
1.完善了crm子部分的查询方法，使其更符合bos主项目的使用。具体  包括定区关联客户方法    
2.调整了decidedzone定区的页面，为其加入js代码，使其具有数据交互功能。注意  select控件只有selected的属性的元素才会随表单提交。   
3.完成定区关联客户的功能。     
4.完成查看定区关联的分区数据和定区关联的客户数据功能。    
5.至此，定区，分区，取派员，区域数据和客户数据已经完成的耦合，整个bos项目的基础数据部分已经初现雏形。接下来时业务受理环节，   
  是整个物流公司的业务的开始，也是重点，主要包括业务通知单，工单，工作单。

   
   
 
 

 
