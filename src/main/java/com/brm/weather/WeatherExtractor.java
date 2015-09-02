/*******************************************************************************
 *
 * 파일명      : WeatherExtractor.java
 * 설명        : 서울 지역 주간 날씨 예보 출력 Bean
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
	 * 주간 날씨 정보를 화면에 출력
	 */
	public void print(Document doc) throws TransformerException {
		// Use a Transformer for output
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();

	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(System.out);
	    transformer.transform(source, result);

		// location 서울 엘리먼트 검색
		String xpath = "//wid/body/location[@city='11B10101']";
		NodeIterator locations = XPathAPI.selectNodeIterator(doc, xpath);
		Node location;
		while ((location = locations.nextNode()) != null) {
			NodeIterator datas = XPathAPI.selectNodeIterator(location, "data");
			Node data;
			while ((data = datas.nextNode()) != null) {
				System.err.println(getText(data, "numEf") + "일 후 예보");
				System.err.println("\t날짜 : " + getText(data, "tmEf"));
				System.err.println("\t날씨예보 : " + getText(data, "wf"));
				System.err.println("\t최저온도 : " + getText(data, "tmn") + " 도");
				System.err.println("\t최고온도 : " + getText(data, "tmx") + " 도");
				System.err.println("\t신뢰도 : " + getText(data, "reliability"));
			}
		}
	}

	private String getText(Node data, String xpath) throws TransformerException {
		return XPathAPI.selectSingleNode(data, xpath).getTextContent();
	}
}
