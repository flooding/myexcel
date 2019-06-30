```java
URL htmlToExcelEampleURL = this.getClass().getResource("/templates/read_example.xlsx");
Path path = Paths.get(htmlToExcelEampleURL.toURI());

// 方式一：全部读取后处理
List<ArtCrowd> result = DefaultExcelReader.of(ArtCrowd.class)
        .sheet(0) // 0代表第一个，如果为0，可省略该操作
        .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
        .beanFilter(ArtCrowd::isDance) // bean过滤
        .read(path.toFile());// 可接收inputStream

// 方式二：读取一行处理一行，可自行决定终止条件
DefaultExcelReader.of(ArtCrowd.class)
        .sheet(0) // 0代表第一个，如果为0，可省略该操作
        .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
        .beanFilter(ArtCrowd::isDance) // bean过滤
        .readThen(path.toFile() ,artCrowd -> System.out.println(artCrowd.getName));// 可接收inputStream

// 方式三：全部读取后处理，SAX模式，避免OOM，建议大量数据使用
List<ArtCrowd> result = SaxExcelReader.of(ArtCrowd.class)
        .sheet(0) // 0代表第一个，如果为0，可省略该操作
        .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
        .beanFilter(ArtCrowd::isDance) // bean过滤
        .read(path.toFile());// 可接收inputStream

// 方式四：读取一行处理一行，可自行决定终止条件，SAX模式，避免OOM，建议大量数据使用
SaxExcelReader.of(ArtCrowd.class)
        .sheet(0) // 0代表第一个，如果为0，可省略该操作
        .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
        .beanFilter(ArtCrowd::isDance) // bean过滤
        .readThen(path.toFile() ,artCrowd -> System.out.println(artCrowd.getName));// 可接收inputStream

public class ArtCrowd {
    // index代表列索引，从0开始
    @ExcelColumn(index = 0)
    private String name;

    @ExcelColumn(index = 1)
    private String age;

    @ExcelColumn(index = 2,dateFormatPattern="yyyy-MM-dd")
    private Date birthday;
}
```
导入`必须`使用注解： @ExcelColumn(index,dateFormatPattern)

对应注解详情请见：[注解](https://github.com/liaochong/myexcel/wiki/Annotation)

操作API请参见 [API](api.md)