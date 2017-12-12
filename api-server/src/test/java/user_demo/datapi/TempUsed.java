package user_demo.datapi;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TempUsed {
	public static void main(String[] args) {
		// 创建文档并设置文档的根元素节点
		Element root = DocumentHelper.createElement("books");
		Document doucment = DocumentHelper.createDocument(root);
		// 根节点
		root.addAttribute("name", "bookvalue");
		// 子节点
		Element element1 = root.addElement("id");
		element1.addText("value");
		System.out.println(doucment.asXML());

	}
}
