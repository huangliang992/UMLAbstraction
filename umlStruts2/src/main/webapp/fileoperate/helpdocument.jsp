<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UserDocument</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
	<s:include value="head.jsp"></s:include>
	<div class="container-fluid">
	<h1>使用说明</h1>
	<h2>1. 概述</h2>
	<p>本系统是在ArgoUML开源建模的软件基础上开发的，我们对输入的UML类图文件有格式上的要求，它必须是用ArgoUML工具绘制的UML类图并保存成的.uml文件格式(如example.uml)。我们的系统目前具备的功能有（1）查找类图中的环（2）查找类图中任意两个类之间的所有路径（3）就算和获取两个类之间的关系，并给出相应的计算过程。</p>
	<h2>2. ArgoUML工具介绍</h2>
	<p>AgoUML与其他的建模工具类似，如Rational Rose，Enterprise Architect， Star
		UML等，它是一个开源的项目，具备UML建模工具的所有功能。下面是该软件的一个截图。</p>
	<p>
		<img src="http://i.imgur.com/DBYU380.png" />
	</p>
	<h2>3. 导入文件</h2>
	<p>点击Upload进入导入文件页面，从本地选择一个类图（用ArgoUML创建并保存为&quot;xx.uml&quot;格式的文件）</p>
	<p>比如我们导入如下的类图，点击导入（文件名为example.uml）</p>
	<p>
		<img src="http://i.imgur.com/Z9hXvty.png" />
	</p>
	<p>导入后如图所示</p>
	<h2>4. 获取两个类之间所有的路径</h2>
	<p>导入完成后，点击FindCirculation菜单下的FindPath，输入类名Cash和类名Check，表示查找这两个类之间的所有路径。结果如下图所示。</p>
	<p>
		<img src="http://i.imgur.com/lUrqeW0.png" />
	</p>
	<h2>5. 查找类图中包含某个类的所有的不重复的环</h2>
	<p>查找类图中的环十分重要，因为类图中的问题经常出现在环里面。输入类名Check，查找类图中包含Check的环</p>
	</p>
	<img src="http://i.imgur.com/PcTvOao.png" />
	</p>
	<h2>6. 获取任意两个类之间的关系，并给出计算过程</h2>
	<p>例如，输入类Payment和类OrderDetail，要得到这两个类之间的关系。对于这两个类之间的这条路径 For path:
		Payment--AS--Order--AGr--OrderDetail，运用规则AS x Order x AGr equals AS
		70，得到Payment--AS--OrderDetail，也就是说这两个类之间是关联关系，可信是是0.7.</p>
	<p>
		<img src="http://i.imgur.com/B843iVh.png" />
	</p>
	<h2>7. 计算类图中类与类之间关系和相关度矩阵</h2>
	<p>点击GetBigPicture菜单下的CountCorrelations。进入到计算的页面，在页面中点击Count按钮，得到相关度矩阵，和关系矩阵。（example.uml类图的如下所示）</p>
	<p>
		<img src="http://i.imgur.com/xAYg4Ij.png" />
	</p>
	<p>
		<img src="http://i.imgur.com/463LS3m.png" />
	</p>
	<h2>8. 计算类的rank值</h2>
	<p>类的rank值表示了，在类图中这个类的重要性。rank值越高，表示这个类越重要。点击GetBigPicture菜单下的CountClassRanks。然后进入计算rank的界面，点击CountRank得到rank矩阵。（example.uml的rank矩阵如下所示）</p>
	<p>
		<img src="http://i.imgur.com/zaXSZup.png" />
	</p>
	<h2>9. 对rank进行排序</h2>
	<p>依据rank值大小，从小到大排序。点击GetBigPicture菜单下的SortClass，进入排序页面。然后点击sort按钮得到排序后的rank矩阵。（example.uml的如下所示）</p>
	<p>
		<img src="http://i.imgur.com/O4X5tma.png" />
	</p>
	<h2>10. 获取类图的Big Picture</h2>
	<p>big picture是类图的抽象，它包含若干类图中的重要的类。通过重要的类来了解类图的设计。
		点击GetBigPicture菜单下的GetBigPicture，进入获取big picture的页面。</p>
	<ol>
		<li>
			<p>
				可以设定抽象的层次（5层） <br /> 如输入，4，点击提交按钮。（对于example.uml如下所示）
			</p>
			<p>
				<img src="http://i.imgur.com/uve6Eyg.png" />
			</p>
		</li>
		<li>
			<p>
				可以设定保留几个类 <br /> 如输入2，保留两个重要的类（对于example.uml如下所示）
			</p>
			<p>
				<img src="http://i.imgur.com/Q2l2Bzh.png" />
			</p>
		</li>
		<li>
			<p>
				系统自动抽象 <br /> 点击默认的他提交按钮（对于example.uml如下所示）
			</p>
			<p>
				<img src="http://i.imgur.com/4K2UdJ5.png" />
			</p>
		</li>
	</ol>
	<br>
	<hr>
	</div>
	<s:include value="bottom.html"></s:include>
</body>
</html>