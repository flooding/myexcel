# 简介

MyExcel，是一个集导入、导出、加密Excel等多项功能的Java工具包。

定位 | Target
-------------
* **导入**：提供简便的API，读取Excel内容，并转化为List< Bean >。
* **导出**：可快速、低内存导出海量数据，可生成高复杂度布局的Excel，复杂布局指的是包含多种不规则合并单元格、背景色、字体大小、斜体等。

优势 | Advantages
------------------
- **可生成任意复杂表格**：本工具使用迭代单元格方式进行Excel绘制，可生成任意复杂度Excel，提供多种宽度策略；
- **零学习成本**：使用Html作为模板，学习成本几乎为零；
- **支持常用背景色、边框、字体等样式设置**：具体参见[Style-support（样式支持）](style-support.md)部分；
- **支持.XLS、.XLSX**：支持生成.xls、.xlsx后缀的Excel；
- **支持公式导出**：支持Excel模板中设置公式，降低服务端的计算量；
- **支持低内存SXSSF模式**：支持低内存的SXSSF模式，可利用极低的内存生成.xlsx；
- **支持生产者消费者模式导出**：支持生产者消费者模式导出，无需一次性获取所有数据，分批获取数据配合SXSSF模式实现真正意义上海量数据导出；
- **支持多种模板引擎**：已内置Freemarker、Groovy、Beetl、Thymeleaf等常用模板引擎Excel构建器（详情参见文档[Getting started](https://github.com/liaochong/html2excel/wiki/Getting-started)），推荐使用Beetl模板引擎（[Beetl文档](http://ibeetl.com/guide/#beetl)）；
- **提供默认Excel构建器，直接输出简单Excel**：无需编写任何Html，已内置默认模板，可直接根据POJO数据列表输出；
- **支持一次生成多sheet**：以table作为sheet单元，支持一份Excel文档中多sheet导出；


