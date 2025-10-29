package cn.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.openhtmltopdf.mathmlsupport.MathMLDrawer;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;

import cn.alotus.HtmlRender;
import cn.alotus.config.BuilderConfig;
import cn.alotus.config.BuilderConfig.SimpleTextBreaker;
import cn.alotus.core.io.FileUtil;
import cn.alotus.core.io.resource.ResourceUtil;

public class TestCases {

	public static void main(String[] args) throws IOException {
	

		String resHtml="testcases/text-decoration.html";

		URL fonts= ResourceUtil.getResource("testcases/fonts");
		
		String outPath="D://"+resHtml+".png";
		 
		FileUtil.mkParentDirs(outPath);
		
 
		
		String html = ResourceUtil.readUtf8Str(resHtml);

		HtmlRender htmlRender = HtmlRender.create(BufferedImage.TYPE_INT_RGB);
		htmlRender.addFontDirectory(fonts.getPath());
		htmlRender.setPageWidth(1024f);
		htmlRender.setPageHeight(300f);
		htmlRender.setScale(1f);
		htmlRender.setLoggingEnabled(true);
		htmlRender.setBaseDocumentUri(ResourceUtil.getResource("testcases/").toString());
		
		 
 
		BufferedImage image = htmlRender.toImage(html, BuilderConfig.WITH_BASE,builder->{
			
			builder.useSVGDrawer(new BatikSVGDrawer());
			builder.useMathMLDrawer(new MathMLDrawer());
			builder.useFont(new File(ResourceUtil.getResource("fonts/Karla-Bold.ttf").getPath()), "TestFont");
			builder.useUnicodeLineBreaker(new SimpleTextBreaker());
			
		});
		 

		ImageIO.write(image, "PNG", new File(outPath));
		
		
	}

}
