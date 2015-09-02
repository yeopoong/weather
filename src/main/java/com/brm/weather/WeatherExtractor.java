/*******************************************************************************
 *
 * ���ϸ�      : WeatherExtractor.java
 * ����        : ���� ���� �ְ� ���� ���� ��� Bean
 *
 *******************************************************************************/
package com.brm.weather;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import com.sun.org.apache.xpath.internal.XPathAPI;

public class WeatherExtractor {
	/*
	 * �ְ� ���� ������ ȭ�鿡 ���
	 */
	public void print(Document doc) throws TransformerException {
		// Use a Transformer for output
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();

	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(System.out);
	    transformer.transform(source, result);

		// location ���� ������Ʈ �˻�
		String xpath = "//wid/body/location[@city='11B10101']";
		NodeIterator locations = XPathAPI.selectNodeIterator(doc, xpath);
		Node location;
		while ((location = locations.nextNode()) != null) {
			NodeIterator datas = XPathAPI.selectNodeIterator(location, "data");
			Node data;
			while ((data = datas.nextNode()) != null) {
				System.err.println(getText(data, "numEf") + "�� �� ����");
				System.err.println("\t��¥ : " + getText(data, "tmEf"));
				System.err.println("\t�������� : " + getText(data, "wf"));
				System.err.println("\t�����µ� : " + getText(data, "tmn") + " ��");
				System.err.println("\t�ְ�µ� : " + getText(data, "tmx") + " ��");
				System.err.println("\t�ŷڵ� : " + getText(data, "reliability"));
			}
		}
	}

	private String getText(Node data, String xpath) throws TransformerException {
		return XPathAPI.selectSingleNode(data, xpath).getTextContent();
	}
}
