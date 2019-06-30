### 操作类索引
* <a href="#DefaultExcelReader">com.github.liaochong.myexcel.core.DefaultExcelReader</a>
* <a href="#SaxExcelReader">com.github.liaochong.myexcel.core.SaxExcelReader</a>
* <a href="#DefaultExcelBuilder">com.github.liaochong.myexcel.core.DefaultExcelBuilder</a>
* <a href="#DefaultStreamExcelBuilder">com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder</a>
* <a href="#FreemarkerExcelBuilder">com.github.liaochong.myexcel.core.FreemarkerExcelBuilder</a>
* <a href="#BeetlExcelBuilder">com.github.liaochong.myexcel.core.BeetlExcelBuilder</a>
* <a href="#GroovyExcelBuilder">com.github.liaochong.myexcel.core.GroovyExcelBuilder</a>
* <a href="#HtmlToExcelFactory">com.github.liaochong.myexcel.core.HtmlToExcelFactory（核心类）</a>
* <a href="#HtmlToExcelStreamFactory">com.github.liaochong.myexcel.core.HtmlToExcelStreamFactory（核心类，暂未开放）</a>
* <a href="#AttachmentExportUtil">com.github.liaochong.myexcel.utils.AttachmentExportUtil（附件形式导出工具）</a>
* <a href="#FileExportUtil">com.github.liaochong.myexcel.utils.FileExportUtil（文件形式导出工具）</a>

### 操作类：<a id="DefaultExcelReader">com.github.liaochong.myexcel.core.DefaultExcelReader</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static DefaultExcelReader<T> of(Class<?> dataType)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象
DefaultExcelReader<T> sheet(int index)|设置需要导入的sheet索引，默认为0，即第一个sheet
DefaultExcelReader<T> rowFilter(Predicate<Row> rowFilter)|行过滤器，只导入符合rowFilter的行
SaxExcelReader<T> beanFilter(Predicate<T> beanFilter)|过滤bean
~~DefaultExcelReader<T> parallelRead()~~|~~设置是否并行读取~~ 2.4.0版本废弃
List<T> read(@NonNull InputStream fileInputStream) throws Exception|读取文件流
List<T> read(@NonNull InputStream fileInputStream, String password) throws Exception|使用密码读取文件流
List<T> read(@NonNull File file) throws Exception|读取文件
List<T> read(@NonNull File file, String password) throws Exception|使用密码读取文件
void readThen(@NonNull File file,Consumer<T> consumer) throws Exception|读取一行处理一行
void readThen(@NonNull File file, String password,Consumer<T> consumer) throws Exception|使用密码读取一行处理一行
void readThen(@NonNull InputStream fileInputStream,Consumer<T> consumer) throws Exception|读取一行处理一行
void readThen(@NonNull InputStream fileInputStream, String password,Consumer<T> consumer) throws Exception|使用密码读取一行处理一行

### 操作类：<a id="SaxExcelReader">com.github.liaochong.myexcel.core.SaxExcelReader</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static SaxExcelReader of(Class<?> dataType)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象
SaxExcelReader<T> sheet(int index)|设置需要导入的sheet索引，默认为0，即第一个sheet
SaxExcelReader<T> rowFilter(Predicate<Row> rowFilter)|行过滤器，只导入符合rowFilter的行
SaxExcelReader<T> beanFilter(Predicate<T> beanFilter)|过滤bean
List<T> read(@NonNull InputStream fileInputStream) throws Exception|读取文件流
List<T> read(@NonNull File file) throws Exception|读取文件
void readThen(@NonNull File file,Consumer<T> consumer) throws Exception|读取一行处理一行
void readThen(@NonNull InputStream fileInputStream,Consumer<T> consumer) throws Exception|读取一行处理一行

***

### 操作类：<a id="DefaultExcelBuilder">com.github.liaochong.myexcel.core.DefaultExcelBuilder</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static DefaultExcelBuilder of(Class<?> dataType)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象
static DefaultExcelBuilder of(Class<?> dataType,Workbook workbook)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象，该实例对象的工作簿基于传入的workbook，即实现同一工作簿多sheet导出
~~static DefaultExcelBuilder getInstance()~~|已过期，请使用of方法代替，获取该类的实例对象，所有操作基于该实例对象
DefaultExcelBuilder sheetName(String sheetName)|设置导出的excel的sheet名称，建议在注解@ExcelTable中设置
DefaultExcelBuilder titles(List<String> titles)|设置excel导出的对应的列标题，建议使用注解@ExcelColumn设置
DefaultExcelBuilder fieldDisplayOrder(List<String> fieldDisplayOrder)|设置excel导出的对应的字段，其中包含展示顺序，建议使用注解设置
DefaultExcelBuilder workbookType(WorkbookType workbookType)|设置导出的excel的格式，如XLS（.xls）、XLSX（.xlsx）、SXLSX（.xlsx），其中SXLSX（.xlsx）表明采用低内存占用方式生成excel，海量数据导出时建议采用该方式
DefaultExcelBuilder rowAccessWindowSize(int rowAccessWindowSize)|该方法只在workbookType设置为SXLSX生效，设置允许内存中最大保存行数，降低poi的使用内存
DefaultExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy)|设置列自动宽度策略，共三种：无自动列宽、自动列宽、计算自动列宽度，其中自动列宽效果好，但性能稍差，计算自动列宽与之相反，默认计算列宽
DefaultExcelBuilder noStyle()|设置导出文件无默认样式
DefaultExcelBuilder fixedTitles()|固定标题行
Workbook build(List<?> data)|根据指定的数据列表生成最终的workbook


***

### 操作类：<a id="DefaultStreamExcelBuilder">com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static DefaultStreamExcelBuilder of(Class<?> dataType)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象
static DefaultStreamExcelBuilder of(Class<?> dataType,Workbook workbook)|根据需要操作的数据类型获取该类的实例对象，所有操作基于该实例对象，该实例对象的工作簿基于传入的workbook，即实现同一工作簿多sheet导出
DefaultStreamExcelBuilder sheetName(String sheetName)|设置导出的excel的sheet名称，建议在注解@ExcelTable中设置
DefaultStreamExcelBuilder titles(List<String> titles)|设置excel导出的对应的列标题，建议使用注解@ExcelColumn设置
DefaultStreamExcelBuilder fieldDisplayOrder(List<String> fieldDisplayOrder)|设置excel导出的对应的字段，其中包含展示顺序
DefaultStreamExcelBuilder threadPool(ExecutorService executorService)|设置采用的线程池，如不设置，则不采用线程池
DefaultStreamExcelBuilder workbookType(WorkbookType workbookType)|设置导出的excel的格式，如XLS（.xls）、XLSX（.xlsx）、SXLSX（.xlsx），其中SXLSX（.xlsx）表明采用低内存占用方式生成excel，默认采用该方式
DefaultStreamExcelBuilder capacity(int capacity)|设置导出的Excel容量，若大于该容量，则新建Excel
DefaultStreamExcelBuilder rowAccessWindowSize(int rowAccessWindowSize)|该方法只在workbookType设置为SXLSX生效，设置允许内存中最大保存行数，降低poi的使用内存
DefaultStreamExcelBuilder start(Class<?>... groups)|开始构建标志，用于启动生产者消费者模式，默认使用ArrayBlockingQueue，长度为处理器核心数目，其中groups代表需要导出的字段分组
DefaultStreamExcelBuilder start(int waitQueueSize, Class<?>... groups)|开始构建标志，用于启动生产者消费者模式，默认使用ArrayBlockingQueue，需要自定义使用的阻塞队列长度，其中groups代表需要导出的字段分组
void append(List<?> data)|追加数据方法，实现批次导出数据的关键
DefaultStreamExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy)|设置列自动宽度策略，共三种：无自动列宽、自动列宽、计算自动列宽度，其中自动列宽效果好，但性能稍差，计算自动列宽与之相反，默认无自动列宽
DefaultStreamExcelBuilder hasStyle()|设置使用默认样式，流式导出默认无样式
DefaultStreamExcelBuilder fixedTitles()|固定标题行
Workbook build()|最终构建，调用该方法后不允许再次追加数据，否则会报错
List<Path> buildAsPaths()|构建为文件，如设置Excel容量，则输出为文件列表
Path buildAsZip(String fileName)|构建为zip压缩文件，如设置Excel容量，则压缩文件中包含多个文件


***

### 操作类：<a id="FreemarkerExcelBuilder">com.github.liaochong.myexcel.core.FreemarkerExcelBuilder</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
ExcelBuilder template(String path)|设置html模板路径
ExcelBuilder rowAccessWindowSize(int rowAccessWindowSize)|设置当生成模式为SXSSF模式时，内存中保留的行数，**请务必在设置workbookType之前设置，否则不生效，此时将采用默认配置-100行**
ExcelBuilder workbookType(WorkbookType workbookType)|设定导出的excel的文件类型，如.xls
ExcelBuilder useDefaultStyle()|使用工具包定义的单元格样式，此时自定义的样式将无效
ExcelBuilder freezePanes(FreezePane... freezePanes)|窗口冻结，即哪些行、列被冻结，序号从1开始
ExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy)|设置列自动宽度策略，共三种：无自动列宽、自动列宽、计算自动列宽度，其中自动列宽效果好，但性能稍差，计算自动列宽与之相反，默认计算列宽
Workbook build()|开始构建Workbook


***

### 操作类：<a id="BeetlExcelBuilder">com.github.liaochong.myexcel.core.BeetlExcelBuilder</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
ExcelBuilder template(String path)|设置html模板路径
ExcelBuilder rowAccessWindowSize(int rowAccessWindowSize)|设置当生成模式为SXSSF模式时，内存中保留的行数，**请务必在设置workbookType之前设置，否则不生效，此时将采用默认配置-100行**
ExcelBuilder workbookType(WorkbookType workbookType)|设定导出的excel的文件类型，如.xls
ExcelBuilder useDefaultStyle()|使用工具包定义的单元格样式，此时自定义的样式将无效
ExcelBuilder freezePanes(FreezePane... freezePanes)|窗口冻结，即哪些行、列被冻结，序号从1开始
ExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy)|设置列自动宽度策略，共三种：无自动列宽、自动列宽、计算自动列宽度，其中自动列宽效果好，但性能稍差，计算自动列宽与之相反，默认计算列宽
Workbook build()|开始构建Workbook



***

### 操作类：<a id="GroovyExcelBuilder">com.github.liaochong.myexcel.core.GroovyExcelBuilder</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
ExcelBuilder template(String path)|设置html模板路径
ExcelBuilder rowAccessWindowSize(int rowAccessWindowSize)|设置当生成模式为SXSSF模式时，内存中保留的行数，**请务必在设置workbookType之前设置，否则不生效，此时将采用默认配置-100行**
ExcelBuilder workbookType(WorkbookType workbookType)|设定导出的excel的文件类型，如.xls
ExcelBuilder useDefaultStyle()|使用工具包定义的单元格样式，此时自定义的样式将无效
ExcelBuilder freezePanes(FreezePane... freezePanes)|窗口冻结，即哪些行、列被冻结，序号从1开始
ExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy)|设置列自动宽度策略，共三种：无自动列宽、自动列宽、计算自动列宽度，其中自动列宽效果好，但性能稍差，计算自动列宽与之相反，默认计算列宽
Workbook build()|开始构建Workbook


***

### 操作类：<a id="HtmlToExcelFactory">com.github.liaochong.myexcel.core.HtmlToExcelFactory</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static HtmlToExcelFactory readHtml(File htmlFile) throws Exception|读取Html文件
static HtmlToExcelFactory readHtml(String html)|读取字符串形式Html内容
static HtmlToExcelFactory readHtml(File htmlFile, HtmlToExcelFactory htmlToExcelFactory) throws Exception|指定htmlToExcelFactory读取Html文件
ExcelFactory rowAccessWindowSize(int rowAccessWindowSize)|设置当生成模式为SXSSF模式时，内存中保留的行数，**请务必在设置workbookType之前设置，否则不生效，此时将采用默认配置-100行**
ExcelFactory workbookType(WorkbookType workbookType)|设定导出的excel的文件类型，如.xls
ExcelFactory useDefaultStyle()|使用工具包定义的单元格样式，此时自定义的样式将无效
ExcelFactory freezePanes(FreezePane... freezePanes)|窗口冻结，即哪些行、列被冻结，序号从1开始
Workbook build()|根据Html内容以及其他设置构建Excel


***

### 操作类：<a id="AttachmentExportUtil">com.github.liaochong.myexcel.utils.AttachmentExportUtil</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static void export(Workbook workbook, String fileName, HttpServletResponse response) throws IOException|以附件形式导出Excel，其中 **fileName** 为导出后的文件名称，建议不要携带文件后缀名，代码会根据工作簿类型自动添加，以防止设置错误
static void encryptExport(final Workbook workbook, String fileName, HttpServletResponse response, final String password) throws Exception|以附件形式加密导出Excel，其中 **fileName** 为导出后的文件名称，建议不要携带文件后缀名，代码会根据工作簿类型自动添加，以防止设置错误


***

### 操作类：<a id="FileExportUtil">com.github.liaochong.myexcel.utils.FileExportUtil</a>

方法签名（Method）| 描述（Desc）
----------------|-------------
static void export(Workbook workbook, File file) throws IOException|导出Excel到某个文件中，无需后缀名，代码会根据工作簿类型自动添加，以防止设置错误
static void encryptExport(final Workbook workbook, File file, final String password) throws Exception|加密导出Excel到某个文件中，无需后缀名，代码会根据工作簿类型自动添加，以防止设置错误