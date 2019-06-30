1.工具包是否支持jdk6、jdk7？
------------------------
MyExcel所有版本均只支持jdk8+

2.Response流输出文件名称如何支持中文？
------------------------------
版本1.4.1以及之后，请使用AttachmentExportUtil\FileExportUtil工具类导出，无需关心后缀以及中文问题；
其他版本请如下设置Response流：
```java
response.setCharacterEncoding(CharEncoding.UTF_8);
response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, CharEncoding.UTF_8));
```

3.不指定工作簿格式，默认格式是什么？
-----------------------------
如不指定workbookType，则文件的默认格式为.xlsx，也建议使用.xlsx格式

4.模板文件可以存放的位置有哪些？
--------------------------
目前默认支持的模板引擎有：Freemarker、Beetl、Groovy template engine，使用上述默认ExcelBuilder创建Excel时，模板文件只能放在classpath下，如resources下

5.性能说明
---------
第三代酷睿i5，双核，8G内存环境下复杂布局（单元格合并、背景色、边框等等）3万单元格数据处理约3秒，因本工具主要是针对复杂布局的excel（包含样式等）导出，因此性能上会缺失些，在非复杂布局情况下使用DefaultExcelBuilder导出，上述环境下200万单元格3秒左右。

6.如何在html中设置excel的sheet名称
----------------------------------
Set Excel sheet name：Add `<caption>sheet名称</caption>` to Table
```html
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<table>
		<caption>sheet名称</caption>
		<thead>
			<tr>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
```

7.Excel sheet最大行数/列数支持
---------------------------
Excel版本 | 最大行数 | 最大列数
---------|---------|--------
Excel 2013|1,048,576 行|16,384 列
Excel 2010|1,048,576 行|16,384 列
Excel 2007|1,048,576 行|16,384 列
Excel 97-2003|65,536 行|256 列

8.高并发情况下是否会出现安全问题？
----------------------------
在POI较低版本高并发生成Excel时可能会出现如下错误：
```java
Caused by: java.io.IOException: Could not create temporary directory '/home/admin/dio2o/.default/temp/poifiles'
        at org.apache.poi.util.DefaultTempFileCreationStrategy.createTempDirectory(DefaultTempFileCreationStrategy.java:93) ~[poi-3.15.jar:3.15]
        at org.apache.poi.util.DefaultTempFileCreationStrategy.createPOIFilesDirectory(DefaultTempFileCreationStrategy.java:82) ~[poi-3.15.jar:3.15]
```
对应的源代码如下：
```java
 private void createTempDirectory(File directory) throws IOException {
        if (!(directory.exists() || directory.mkdirs()) || !directory.isDirectory()) {
            throw new IOException("Could not create temporary directory '" + directory + "'");
        }
    }
```
MyExcel采用的POI版本已修复了该问题，请放心使用，修改后对应的源代码如下：
```java
private synchronized void createTempDirectory(File directory) throws IOException {
     boolean dirExists = directory.exists() || directory.mkdirs();
     if (!dirExists) {
         throw new IOException("Could not create temporary directory '" + directory + "'");
     } else if (!directory.isDirectory()) {
         throw new IOException("Could not create temporary directory. '" + directory + "' exists but is not a directory.");
     }
}
```
9..xls文件自定义颜色无效问题解决方案
------------------------------
针对.xls文件，POI预定义了56种颜色，如需自定义颜色，需要覆盖56种颜色，目前解决方案暂未确定，因此，目前建议有自定义颜色需求用户使用.xlsx，如只能使用.xls文件，目前只能使用预定义的56种颜色，颜色列表请参照：[颜色索引](https://www.cnblogs.com/haha12/p/4353602.html)

使用方式：
以`HSSFColor.OLIVE_GREEN`为例，设置样式时，设置为`style="background-color:olivegreen"`即可。

10.DefaultExcelReader/SaxExcelReader类readThen接口提示错误解决方案
--------------------------------
在部分场景下使用readThen接口会出现以下错误提示：
```java
Error:(210, 62) java: 对readThen的引用不明确
  com.github.liaochong.myexcel.core.DefaultExcelReader 中的方法 readThen(java.io.InputStream,java.util.function.Consumer<T>) 和 com.github.liaochong.myexcel.core.DefaultExcelReader 中的方法 readThen(java.io.InputStream,java.util.function.Function<T,java.lang.Boolean>) 都匹配
```
为解决该问题，请在方法执行体中增加大括号"{"，如下：
```java
readThen(file,data->{System.out.println(data.name);})
```
