##UML类图抽象与检测
###（一）背景说明
**类图抽象**：类图包含类，关系。一个类图展示的是这个系统的抽象。然而当一个系统十分庞大的时候类图中涉及到的类和关系将会很多，很繁杂，不利于设计人员阅读。特别是当我们学习一个开源的项目的时候我们有时候会用Rational Rose， EA这样的建模工具导入项目生成类图，查看整个系统的设计。我们做的就是在这个的基础上再对类图进行抽象，保留更少的更核心的类，以便把握整个项目的结构。我们结合了pagerank算法和类之间关系抽象的规则（请查阅这篇论文 Heterogeneous View Integration and its Automation 了解关系抽象规则）对类图进行抽象获取抽象后的类图。    
**类图检测**：之前搜集数据的过程中，我们发现了一类类图设计中常见的可能出问题的地方，那就是类图中的环，按照关系抽象的规则，一个环中两个类之间必然存在两条路径，通过这两条路径可以得到这两个类之间的两种关系，如果这两种关系不一致的话，那可能会产生冲突，我们要做的就是检测这样的环，判断这样的关系，看是否存在冲突。如果存在冲突的话尽早解决，避免软件开发过程中不必要的麻烦。
###（二）采用的技术
1. mvc 采用的是struts2
2. 由于我们处理的类图文件是用argouml设计的类图是XML类型的文件，所以采用的是DOM和XPath解析这种文件，提取其中的类和关系。
3. 对于前端的类图和抽象后的类图的展示则采用的是jsuml的接口。
4. 个类图的源文件形式的例子可以在fileexample文件夹下找到
###（三）截图
1.主页

![](http://i.imgur.com/Pw3qw4K.jpg)

上传文件后的展示

![](http://i.imgur.com/4G5VTAR.jpg)

2. 检测部分

查找两个类之间的所有路径

![](http://i.imgur.com/6n1jAek.jpg)

对路径进行抽象

![](http://i.imgur.com/l2BD4mP.jpg)

查找环

![](http://i.imgur.com/Suy4xHz.jpg)

3. 抽象部分

计算类之间的相关度

![](http://i.imgur.com/TgJAgdF.jpg)

计算类的rank值

![](http://i.imgur.com/070GBk8.jpg)

类按rank值排序

![](http://i.imgur.com/TVcUvBV.jpg)

生成抽象的类图

![](http://i.imgur.com/uZha038.jpg)

###（四）部署到服务器上
网址：www.yucongduan.org/uml