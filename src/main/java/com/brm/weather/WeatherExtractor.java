/*******************************************************************************
 *
 * ���ϸ�      : WeatherExtractor.java
 * ����        : ���� ���� �ְ� ���� ���� ��� Bean
 *
 *******************************************************************************/
package com.brm.weather;

import com.sun.org.apache.xpath.internal.XPathAPI;

import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

public class WeatherExtractor {
	/*
	 * �ְ� ���� ������ ȭ�鿡 ���
	 */
	public void print(Document doc) throws TransformerException {
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
