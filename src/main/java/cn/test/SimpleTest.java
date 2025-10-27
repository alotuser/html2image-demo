package cn.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

import org.w3c.dom.Element;

import cn.alotus.HtmlRender;
import cn.alotus.util.ImageCropUtil;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		 
		
		
		ClassLoader classLoader = new SimpleTest().getClass().getClassLoader();
		
		URL resourceUrl = classLoader.getResource("1.html");
		String path = resourceUrl.getPath();
		
		String html = HtmlRender.readHtml(path);

		HtmlRender htmlRender = HtmlRender.create(BufferedImage.TYPE_INT_RGB);
		htmlRender.addFontDirectory("D:/myfonts");
		htmlRender.setPageWidth(400f);
		htmlRender.setPageHeight(300f);
		htmlRender.setScale(1f);

		// htmlRender.toImage(html, BuilderConfig.WITH_CUSTOM);

//		htmlRender.toImage(html, builder->{
//			 builder.useFont(new File("myfont"), "myfont");
//		});
//		
		htmlRender.toPng(html, "D://1.png");

		
		
		
		String className="original-price";

		Map<Element, Rectangle> mers = htmlRender.findByClass(className);
 
		System.out.println(mers);

		Rectangle f = mers.values().stream().findFirst().get();

		BufferedImage original = ImageIO.read(new File("D:\\1.png"));

		Rectangle rect = new Rectangle(f.x, f.y, f.width, f.height);

		BufferedImage cropped = ImageCropUtil.cropImage(original, rect);
		ImageIO.write(cropped, "png", new File("D:\\1-cropped.png"));

	}

}
