# html2image-demo

## 功能概述
**核心功能**：将HTML内容渲染为图片，并通过CSS类名和ID等定位页面中的特定元素区域，然后裁剪出该区域的图片。

## 详细流程说明

### 1. 资源加载阶段
```java
ClassLoader classLoader = new SimpleTest().getClass().getClassLoader();
URL resourceUrl = classLoader.getResource("1.html");
String path = resourceUrl.getPath();
String html = HtmlRender.readHtml(path);
```
**流程**：
- 获取当前类的类加载器
- 从classpath中加载HTML资源文件（1.html）
- 读取HTML文件内容到字符串

### 2. 渲染器配置阶段
```java
HtmlRender htmlRender = HtmlRender.create(BufferedImage.TYPE_INT_RGB);
htmlRender.addFontDirectory("D:/myfonts");
htmlRender.setPageWidth(400f);
htmlRender.setPageHeight(300f);
htmlRender.setScale(1f);
```
**配置项**：
- **图像类型**：`TYPE_INT_RGB` - 使用RGB颜色模式
- **字体目录**：添加自定义字体目录
- **页面尺寸**：设置渲染页面宽400px、高300px
- **缩放比例**：1倍缩放

### 3. HTML渲染阶段
```java
htmlRender.toPng(html, "D://1.png");
```
**功能**：将HTML内容渲染成PNG格式图片并保存到指定路径

### 4. 元素定位阶段
```java
String className = "original-price";
Map<Element, Rectangle> mers = htmlRender.findByClass(className);
Rectangle f = mers.values().stream().findFirst().get();
```
**功能**：
- 通过CSS类名`original-price`查找页面中所有匹配的元素
- 获取第一个匹配元素的坐标和尺寸信息（Rectangle对象）

### 5. 图片裁剪阶段
```java
BufferedImage original = ImageIO.read(new File("D:\\1.png"));
Rectangle rect = new Rectangle(f.x, f.y, f.width, f.height);
BufferedImage cropped = ImageCropUtil.cropImage(original, rect);
ImageIO.write(cropped, "png", new File("D:\\1-cropped.png"));
```
**流程**：
- 读取已渲染的完整图片
- 根据定位到的元素坐标创建裁剪区域
- 执行图片裁剪操作
- 保存裁剪后的图片

## 应用场景
1. **网页截图自动化** - 自动生成网页特定区域的截图
2. **UI测试验证** - 验证页面中特定元素的显示效果
3. **内容提取** - 从渲染结果中提取特定内容区域
4. **报告生成** - 自动生成包含网页特定区域截图的报告

## 关键特性
- **精确定位**：通过CSS类名准确定位页面元素
- **字体支持**：支持自定义字体渲染
- **灵活配置**：可调整页面尺寸和缩放比例
- **批量处理**：可处理多个类名对应的多个区域

这个流程特别适合需要将网页内容以图片形式保存，并重点突出特定功能区域的自动化处理场景。



## @page高级用法 

``` java
@page {
			size: A4;
			margin: 2.5cm 1.8cm 2cm 1.9cm;
			@bottom-left {
				content: element(footer);
			}
			@top-left {
				content: element(header);
			}
			@top-left-corner {
				content: element(topleftcorner);
			}
			@left-middle {
				content: element(left);
			}
			@bottom-right {
				content: element(bottomright);
			}
		}
		
		
@page {
  size: 100px 100px;
  margin: 20px;
}



@page {
  size: 180px 100px;
  margin: 18px;
  -fs-max-overflow-pages: 10;
  -fs-overflow-pages-direction: ltr;
  
  @top-left {
    content: "Page " counter(page) -fs-if-cut-off(" (continued)") " of " counter(pages);
    font-family: 'TestFont';
    font-size: 12px;
  }
}
```
