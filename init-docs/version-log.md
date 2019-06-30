### release-2.7.0（2019/06/29）
- 修复在SXSSF模式下因提前写入磁盘导致合并单元格错误问题；
- 新增DefaultExcelBuilder、DefaultStreamExcelBuilder多级表头功能；
- 新增读取Excel时间戳（Timestamp）的支持；
- 新增DefaultStreamExcelBuilder导出zip压缩文件的支持（DefaultStreamExcelBuilder.buildAsZip("xxxx")）；
- 新增AttachmentExportUtil对一般性Path导出支持；
- 优化DefaultStreamExcelBuilder转化workbook为path流程，使用提供的线程池异步转化，提升导出效率；
- 优化SXSSF默认窗口内存保存行数为1；
- 优化默认workbook类型为SXSSF，降低实际生产使用中内存占用；
- 优化DefaultStreamExcelBuilder模型，降低一次性追加数据导致的内存急剧增长问题（使用Jprofiler测试，一百万数据，除首次导出所占内存较高外(三四百兆内)，稳定占用内存一百兆左右）；
- 优化导出Excel模型边界定义，降低代码错误的可能性；

### release-2.6.2（2019/06/22）
- 修复使用自定义方式导致空指针问题；
- 新增读取自定义终止接口；

### release-2.6.1（2019/06/19）
- 修复SaxExcelReader的sheet设置无法生效问题；

### release-2.6.0（2019/06/18）
- DefaultStreamExcelBuilder新增文件分割，允许设定生成的Excel容量，超过该容量后会自动创建新的Excel；

### release-2.5.1（2019/06/15）
- 修复追加sheet的数据为空时导致的追加失败问题；
- 修复追加sheet导致的原配置失效问题；
- 修复使用@ExcelTable导致的静态字段被导出问题，默认静态字段不被导出，也可通过ignoreStaticFields=false取消；
- 修改使用同一类类型，sheet名称无法自增长问题；
- 修复选择自动列宽设置错误问题；
- 修复设置workbook类型部分情况下不生效问题；
- 修复DefaultStreamExcelBuilder无法直接固定标题行问题；
- 新增DefaultExcelBuilder\DefaultStreamExcelBuilder自动换行，默认为true，可通过@ExcelTable中wrapText取消；
- 修改DefaultExcelBuilder\DefaultStreamExcelBuilder部分数据结构为LinkedList，降低内存要求；
- 修改快速移除数据逻辑，提升数据处理效率；
- 优化部分转换操作，避免海里数据列表整体复制；


### release-2.5.0（2019/05/30）
- 模板导出支持自动换行（word-break）；
- DefaultExcelBuilder、DefaultStreamExcelBuilder支持固定标题；
- DefaultExcelBuilder、DefaultStreamExcelBuilder支持Bean属性类型强转为String，避免诸如Long类型数据变成科学计数法等问题；

### release-2.3.4（2019/05/30）
- 修复ThymeleafExcelBuilder导出时FileSystemNotFound异常问题；

### release-2.3.3（2019/05/29）
- 修复workbook读取未及时关闭相关资源问题；

### release-2.3.2（2019/05/27）
- 修复workbook导出时部分excel提示“由于一些内容不可读取，Excel 无法打开 订单信息.xlsx。是否要打开并修复此工作簿?”问题；

### release-2.3.1（2019/05/26）
- 模板引擎单例，提升模板构建性能；
- 模板所需模板由文件读写改为StringWriter，提升模板构建性能；
- 读取转换抽象

### release-2.3.0（2019/05/18）
- 新增SaxExcelReader，支持sax方式读取，避免OOM；
- 优化读取转换方式，性能提升；
- DefaultExcelReader新增beanFilter方法，支持bean过滤；
- 增加Bean反射缓存；
- 升级jsoup为1.12.1，模板方式导出性能提升，内存占用降低；

### release-2.2.1（2019/05/10）
- 修正DefaultExcelReader读取Boolean型错误问题（该问题仅在2.2.0版本存在）；

### release-2.2.0（2019/05/04）
- 模板导出支持公式导出；
- 支持字段原生类型导出（非全部为String）；
- 支持行隐藏；

### release-2.1.4（2019/04/27）
- 新增ThymeleafExcelBuilder；
- 允许DefaultExcelBuilder\DefaultStreamExcelBuilder、FreemarkerExcelBuilder等自定义单元格宽度；
- 新增获取workbook输入流接口；
- Html文件解析性能优化；
- poi依赖包版本升级-4.1.0；
- beetl依赖包设置为可选；

### release-2.1.3（2019/04/13）
- 修复FreemarkerExcelBuilder等对sxlsx文件导出时错误问题；
- 新增DefaultExcelReader 读取每一行然后处理接口；
- 修改部分ArrayList数据结构为LinkedList，降低对内存的依赖性；
- 设置.xls文件自定义颜色不可用，只允许使用预定义背景色；
- 新增DefaultExcelReader导入时日志；

### release-2.1.2（2019/03/30）
- 修复单元格位置调整错误问题；
- BeetlExcelBuilder\GroovyExcelBuilder\FreemarkerExcelBuilder默认采用自动列宽策略；

### release-2.1.1（2019/03/28）
- 修复单元格位置调整错误问题

### release-2.1.0（2019/03/23）
- 新增DefaultExcelReader，支持Excel导入为List<Bean>；
- DefaultExcelBuilder、DefaultStreamExcelBuilder新增接口，允许基于指定的workbook构建，方便一个workbook多sheet、多类型数据导出；
- DefaultExcelBuilder、DefaultStreamExcelBuilder新增noStyle（hasStyle）接口，允许无样式导出；
- 新增自动列宽策略，允许用户选择策略导出，策略包含：无自动列宽、自动列宽（时间消耗严重，效果好）、组件自动列宽（由工具包计算，性能好，但效果略差，此策略为默认策略）；
- 修改DefaultStreamExcelBuilder默认样式为：无样式、无自动列宽；
- 支持在html文件中进行字体颜色设置；
- 导出工具类（AttachmentExport\FileExport）新增加密导出接口（只针对.xlsx文件有效）；
- @ExcelTable新增defaultValue属性，允许当导出属性为NULL时设置默认值；
- @ExcelColumn新增defaultValue属性，允许当导出属性为NULL时设置默认值，覆盖@ExcelTable的defaultValue；
- 优化部分代码，减少内存占用以及性能提升；

### release-2.0.0（2019/03/11）
- 项目名称修改为myexcel，去除原名称带来的歧义-html仅仅只是转化为excel的工具包。html的作用只是作为模板；

### release-1.4.1（2019/02/23）
- 新增AttachmentExportUtil、FileExportUtil工具类，导出文件名称无需后缀，主动关闭工作簿，使导出更容易；
- 代码常量优化；
- example中示例代码优化；
- example中模板文件优化，去除非table元素，模板更清晰；

### release-1.4.0（2019/02/13）
- 新增DefaultStreamExcelBuilder流式导出海量数据功能，生产者消费者模式，数据批次构建，极大降低内存占用以及导出时间；
- @ExcelTable新增useFieldNameAsTitle属性，默认为false，允许使用字段名字作为标题；
- @ExcelColumn新增分组属性，允许DefaultExcelBuilder传入分组字段，字段动态选择性导出；
- 缓存由LinkedHashMap修改为WeakHashMap，降低内存占用导致的可能的问题；
- 修正表格因字体大小、加粗导致的自适应宽度失效问题；
- DefaultExcelBuilder新增指定类构建Excel，使用该方式，数据为空时也可导出标题列；
- 修改DefaultExcelBuilder字段格式化方式；
- 修正DefaultExcelBuilder构建Excel数据为空时无法导出标题问题；
- 修正DefaultExcelBuilder设置条件限制不严格问题；
- 修正错误注释；
- 修正模板文件路径必须包含“/”问题，允许只提供文件名称；
- 优化GroovyExcelBuilder设置模板流程，解决模板文件路径可能误加“/”导致的无法找到模板问题；
- 优化ExcelBuilder的build接口参数，不强制要求参数类型为Map<String,Object>，参数改为Map<String,T>；
- 项目基础模块重构，完善接口；
- example项目中示例重构，更清晰、完整；

### release-1.3.1
- 优化精简数据结构，降低内存占用；
- 修复非首行高度无法自适应问题；
- 修复注解@ExcelTable中includeAllField与excludeParent共存问题；
- excel生成流程优化；

### release-1.3.0
- 支持POI的SXSSF模式；
- 优化生成Excel时的内存占用；
- 优化生成Excel时的流程；