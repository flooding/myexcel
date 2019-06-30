> 如只是简单列表导出，无需选定模板引擎，请直接跳至下方<a href="#DefaultExcelBuilder">DefaultExcelBuilder/DefaultStreamExcelBuilder</a>部分

1.导出模板引擎选定
---------------
1. 以下模板引擎默认均未被引入，使用者可根据自身需要选择在pom.xml中声明引入。

    The following template engine is not introduced by default except Beetl. Users can choose to introduce the introduction in pom.xml according to their needs.

2. 以下模板引擎版本为最低版本号。

    The following template engine version is the lowest version number.

```xml
<dependency>
    <groupId>com.ibeetl</groupId>
    <artifactId>beetl</artifactId>
    <version>2.7.23</version>
</dependency>

<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.23</version>
</dependency>

<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-templates</artifactId>
    <version>2.4.13</version>
</dependency>

<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>2.1.6.RELEASE</version>
</dependency>
```
2.Workbook生成
--------------
1. 已存在Html文件时，使用这种方式，Html文件不局限于放在项目的classpath（如：resources）下，也无需模板引擎

```java
// get html file
File htmlFile = new File("/Users/liaochong/Downloads/example.html");

// read the html file and use default excel style to create excel
Workbook workbook = HtmlToExcelFactory.readHtml(htmlFile).useDefaultStyle().build();

// this is a example,you can write the workbook to any valid outputstream
FileExportUtil.export(workbook, new File("/Users/liaochong/Downloads/excel.xlsx"));
```
2. 使用内置的Freemarker等模板引擎Excel构建器，模板文件应当存放在classpath下，具体请参照项目中的example

```java
/*
* use non-default-style excel builder
* 模板文件放置在resources下
*
* @param response response
*/
@GetMapping("/freemarker/example")
public void build(HttpServletResponse response) {
     Map<String, Object> dataMap = this.getDataMap();

     ExcelBuilder excelBuilder = new FreemarkerExcelBuilder();
     Workbook workbook = excelBuilder
                         .template("/templates/freemarkerToExcelExample.ftl")
                         .build(dataMap);
     AttachmentExportUtil.export(workbook, "freemarker_excel", response);
}

/*
* use default-style excel builder
* 模板文件放置在resources下
*
* @param response response
*/
@GetMapping("/freemarker/defaultStyle/example")
public void buildWithDefaultStyle(HttpServletResponse response) {
    Map<String, Object> dataMap = this.getDataMap();

    ExcelBuilder excelBuilder = new FreemarkerExcelBuilder();
    Workbook workbook = excelBuilder
                .template("/templates/freemarkerToExcelExample.ftl")
                .useDefaultStyle()
                .build(dataMap);
    AttachmentExportUtil.export(workbook, "freemarker_excel", response);
}

private Map<String, Object> getDataMap() {
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("sheetName", "freemarker_excel_example");

    List<String> titles = new ArrayList<>();
    titles.add("Category");
    titles.add("Product Name");
    titles.add("Count");
    dataMap.put("titles", titles);

    List<Product> data = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        Product product = new Product();
        if (i % 2 == 0) {
            product.setCategory("蔬菜");
            product.setName("小白菜");
            product.setCount(100);
        } else {
            product.setCategory("电子产品");
            product.setName("ipad");
            product.setCount(999);
        }
        data.add(product);
    }
    dataMap.put("data", data);
    return dataMap;
}
```
3. 默认模板构建器（<a id="DefaultExcelBuilder">DefaultExcelBuilder/DefaultStreamExcelBuilder</a>）使用，无需模板引擎
- 3.1 注解方式（推荐）

```java
/**
* 方式一：普通方式导出
*/
@GetMapping("/default/excel/example")
public void defaultBuild(HttpServletResponse response) throws Exception {
    List<ArtCrowd> dataList = this.getDataList();
    Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class)
            .build(dataList);
    AttachmentExportUtil.export(workbook, "艺术生信息", response);
    // 加密导出 AttachmentExportUtil.encryptExport(workbook, "艺术生信息", response,"123456");
}

/**
* 方式二：生产者消费者模式导出，分批获取数据，分批写入Excel，真正意义上实现海量数据导出
*/
@GetMapping("/default/excel/stream/example")
public void streamBuild(HttpServletResponse response) throws Exception {
    // 显式标明开始构建
    DefaultStreamExcelBuilder defaultExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class)
            .threadPool(Executors.newFixedThreadPool(10))
            .start();
    // 多线程异步获取数据并追加至excel，join等待线程执行完成
    List<CompletableFuture> futures = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            List<ArtCrowd> dataList = this.getDataList();
            // 数据追加
            defaultExcelBuilder.append(dataList);
        });
        futures.add(future);
    }
    futures.forEach(CompletableFuture::join);
    // 最终构建
    Workbook workbook = defaultExcelBuilder.build();
    AttachmentExportUtil.export(workbook, "艺术生信息", response);
}

// 数据获取
private List<ArtCrowd> getDataList() {
    List<ArtCrowd> dataList = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
        ArtCrowd artCrowd = new ArtCrowd();
        if (i % 2 == 0) {
            artCrowd.setName("张三");
            artCrowd.setAge(19);
            artCrowd.setGender("Man");
            artCrowd.setPaintingLevel("一级证书");
            artCrowd.setDance(false);
            artCrowd.setAssessmentTime(LocalDateTime.now());
            artCrowd.setHobby("摔跤");
        } else {
            artCrowd.setName("李四");
            artCrowd.setAge(18);
            artCrowd.setGender("Woman");
            artCrowd.setPaintingLevel("一级证书");
            artCrowd.setDance(true);
            artCrowd.setAssessmentTime(LocalDateTime.now());
            artCrowd.setHobby("钓鱼");
        }
        dataList.add(artCrowd);
    }
    return dataList;
}

public class People {

    @ExcelColumn(order = 1, title = "姓名",groups = People.class)
    private String name;

    @ExcelColumn(order = 2, title = "年龄")
    private Integer age;

    @ExcelColumn(order = 3, title = "性别")
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

@ExcelTable(sheetName = "艺术生", workbookType = WorkbookType.SXLSX, rowAccessWindowSize = 100, useFieldNameAsTitle = true)
public class ArtCrowd extends People {

    @ExcelColumn(order = 4,groups = String.class)
    private String paintingLevel;

    @ExcelColumn(order = 5, title = "是否会跳舞", groups = {People.class})
    private boolean dance;

    @ExcelColumn(order = 6, title = "考核时间", dateFormatPattern = "yyyy-MM-dd HH:mm:ss", groups = {People.class, String.class})
    private LocalDateTime assessmentTime;

    @ExcludeColumn
    private String hobby;

    public String getPaintingLevel() {
        return paintingLevel;
    }

    public void setPaintingLevel(String paintingLevel) {
        this.paintingLevel = paintingLevel;
    }

    public boolean isDance() {
        return dance;
    }

    public void setDance(boolean dance) {
        this.dance = dance;
    }

    public LocalDateTime getAssessmentTime() {
        return assessmentTime;
    }

    public void setAssessmentTime(LocalDateTime assessmentTime) {
        this.assessmentTime = assessmentTime;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
```
导出使用注解 
1. @ExcelTable(includeAllField,excludeParent,workbookType,sheetName,rowAccessWindowSize,useFieldNameAsTitle,defaultValue)
2. @ExcludeColumn
3. @ExcelColumn(title,order,dateFormatPattern,groups,defaultValue)

对应注解详情请见：[注解](https://github.com/liaochong/myexcel/wiki/Annotation)

#### 生成示例
![avatar](https://raw.githubusercontent.com/wiki/liaochong/html2excel/default-excel-example.png)
> DefaultExcelBuilder生成的Excel默认自适应宽度、高度，双行淡绿背景色，标题行字体加粗、居中
- 3.2 自定义方式

```java
// title
List<String> titles = new ArrayList<>();
titles.add("姓名");
titles.add("年龄");

// field display order
List<String> order = new ArrayList<>();
order.add("name");
order.add("age");

// display data
List<TestDO> dataList = this.getData();

Workbook workbook = DefaultExcelBuilder.getInstance()
                                    .sheetName("default example")
                                    .titles(titles)
                                    .fieldDisplayOrder(order)
                                    .build(dataList);

private List<TestDO> getData(){
  TestDO testDO = new TestDO();
  testDO.setName("张三");
  TestDO testDO1 = new TestDO();
  testDO1.setName("李四");

  TestDO testDO2 = new TestDO();
  testDO2.setName("王五");
  testDO2.setAge(15);
  TestDO testDO3 = new TestDO();
  testDO3.setName("陈六");
  testDO3.setAge(25);

  List<TestDO> dataList = new ArrayList<>();
  dataList.add(testDO);
  dataList.add(testDO1);
  dataList.add(testDO2);
  dataList.add(testDO3);
  
  return dataList;
}
```