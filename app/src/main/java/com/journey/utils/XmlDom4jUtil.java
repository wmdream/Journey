package com.journey.utils;

import android.content.Context;

import com.journey.data.SlideFrame;
import com.journey.data.Slideshow;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlDom4jUtil {

	/** 将文件转化为document，用dom4j */
	// 从文件读取XML，输入文件名，返回XML文档
	public static Document read(Context ctx, String filename) {
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = null;
		// 获取SD卡对应的存储目录
		// File sdCardDir = Environment.getExternalStorageDirectory();
		File f = new File(ctx.getFilesDir(), filename);
		try {
			// String path =
			// sdCardDir.getCanonicalPath()+"/xinlinglong/"+fileName;
			document = reader.read(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	public static Element getRootElement(Document doc) {
		return doc.getRootElement();
	}


    /** 获取元素名 为 eName 的 值 */
    public static String getElementValue(Element root, String eName) {
        String elementValue = "";
        // 枚举所有子节点
       /* for ( Iterator i = root.elementIterator(eName); i.hasNext();) {
            Element element = (Element) i.next(); //
            if(element.getName().equalsIgnoreCase(eName))
                elementValue = element.getStringValue();
        }*/
        Element element = root.element(eName);
        if(element!=null){
            elementValue = element.getText();
        }
        return elementValue;
    }

	/** 获取属性名 为 attrName 的 属性值 */
	public static String getAttrValue(Element root, String attrName) {
		String attrValue = "";
		for (Iterator i = root.attributeIterator(); i.hasNext();) {
			Attribute attr = (Attribute) i.next();
			if (attr.getName().equalsIgnoreCase(attrName))
				attrValue = attr.getText();
		}
		return attrValue;
	}

	/** 枚举名称为foo的节点 */
	public static List<Element> getElements(Element root, String elName) {
		List<Element> list = new ArrayList<Element>();

		// 枚举名称为foo的节点
		/*for (Iterator i = root.elementIterator(elName); i.hasNext();) {
			Element el = (Element) i.next();
			list.add(el);
		}
		return list;*/
        return root.elements(elName);
	}

	/** 将element转化为对象 */
	public static List<SlideFrame> getFrameList(List<Element> elist) {
		List<SlideFrame> list = null;
		if (elist.size() > 0) {
			list = new ArrayList<SlideFrame>();
			for (int i = 0; i < elist.size(); i++) {
				Element e = elist.get(i);
                SlideFrame frame = new SlideFrame();
//				frame.setSrc(getAttrValue(e, "src"));
//				frame.setBgsrc(getAttrValue(e, "bgsrc"));
//				frame.setHref(getAttrValue(e, "href"));
//				frame.setTarget(getAttrValue(e, "target"));
//				frame.setTitle(getAttrValue(e, "title"));
//				frame.setNativeSrc(getAttrValue(e, "nativeSrc"));
//				frame.setNativeBgsrc(getAttrValue(e, "nativeBgsrc"));

                frame.setSrc(getElementValue(e, "src"));
				frame.setBgsrc(getElementValue(e, "bgsrc"));
				frame.setHref(getElementValue(e, "href"));
				frame.setTarget(getElementValue(e, "target"));
				frame.setTitle(getElementValue(e, "title"));
				frame.setNativeSrc(getElementValue(e, "nativeSrc"));
				frame.setNativeBgsrc(getElementValue(e, "nativeBgsrc"));
				list.add(frame);
			}
		}
		return list;
	}

	/** 将xml文件 通过dom4j 转化为XllSlideshow对象 */
	public synchronized static Slideshow getSlideshow(Context ctx, String fileName) {
		try {
			Slideshow xllSlides = new Slideshow();
			Document doc = read(ctx, fileName);
			Element root = getRootElement(doc);
			String updatetime = getElementValue(root, "updatetime"); //getAttrValue(root, "updatetime");
			xllSlides.setUpdatetime(updatetime);
			List<SlideFrame> frameList = getFrameList(getElements(root, "frame"));
			xllSlides.setLists(frameList);
			return xllSlides;
		} catch (Exception e) {
			return null;
		}
	}

    /** 将xml文件 通过dom4j 转化为XllSlideshow对象 */
    public synchronized static Document getDocument(String str) {
        /*try {
            str = str.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
            Document doc = DocumentHelper.parseText(str);
            return doc;

        } catch (Exception e) {
            MyApplication.htlog.i("getDocument EXCEPTION");
            return null;
        }*/
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = null;

        try {
            document = reader.read(str);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return document;
    }

    public synchronized static Document getDocument(URL url) {
        SAXReader reader = new SAXReader();
        org.dom4j.Document document = null;

        try {
            document = reader.read(url);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return document;
    }

	/** 将文件 通过dom4j 转化为XllSlideshow对象 */
	public synchronized static void saveSlidesInfo(Context ctx, Slideshow slideshow, String filename) {
		Document document = DocumentHelper.createDocument();
		/** 建立XML文档的根slideshow */
		Element slidesElement = document.addElement("slideshow");
        slidesElement.addElement("updatetime", slideshow.getUpdatetime());
		List<SlideFrame> list = slideshow.getLists();
		/** 加入一行注释 */
		// slidesElement.addComment("This is a test for dom4j, holen, 2004.9.11");
		/** 加入frame节点 */
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SlideFrame frame = list.get(i);
				Element frameElement = slidesElement.addElement("frame");
//				frameElement.addAttribute("src", frame.getSrc());
//				frameElement.addAttribute("bgsrc", frame.getBgsrc());
//				frameElement.addAttribute("href", frame.getHref());
//				frameElement.addAttribute("target", frame.getTarget());
//				frameElement.addAttribute("title", frame.getTitle());
//				frameElement.addAttribute("nativeSrc", frame.getNativeSrc());
//				frameElement.addAttribute("nativeBgsrc", frame.getNativeBgsrc());

         /*       frameElement.addElement("src", frame.getSrc());
				frameElement.addElement("bgsrc", frame.getBgsrc());
				frameElement.addElement("href", frame.getHref());
				frameElement.addElement("target", frame.getTarget());
				frameElement.addElement("title", frame.getTitle());
				frameElement.addElement("nativeSrc", frame.getNativeSrc());
				frameElement.addElement("nativeBgsrc", frame.getNativeBgsrc());*/

                Element elesrc = frameElement.addElement("src");
                elesrc.addText(frame.getSrc());
                Element elebgsrc = frameElement.addElement("bgsrc");
                elebgsrc.addText(frame.getBgsrc());
                Element elehref = frameElement.addElement("href");
                elehref.addText(frame.getHref());
                Element eletarget = frameElement.addElement("target");
                eletarget.addText(frame.getTarget());
                Element eletitle = frameElement.addElement("title");
                eletitle.addText(frame.getTitle());
                Element elenativeSrc = frameElement.addElement("nativeSrc");
                elenativeSrc.addText(frame.getNativeSrc());
                Element elenativeBgsrc = frameElement.addElement("nativeBgsrc");
                elenativeBgsrc.addText(frame.getNativeBgsrc());
         //       MyApplication.htlog.i(elenativeBgsrc.getName()+":"+elenativeBgsrc.getText());
			}
		}
		try {

			/** 将document中的内容写入文件中 */
			XMLWriter writer = new XMLWriter(ctx.openFileOutput(filename, Context.MODE_WORLD_WRITEABLE
					+ Context.MODE_WORLD_READABLE));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
