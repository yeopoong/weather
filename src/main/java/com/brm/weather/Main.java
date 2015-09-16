/*******************************************************************************
 *
 * ���ϸ�      : Main.java
 * ����        : ���� ���α׷�
 *
 *******************************************************************************/
package com.brm.weather;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import org.w3c.dom.Document;

public class Main extends RouteBuilder {

	public static void main(String[] args) throws Exception {
		Main weather = new Main();
		weather.run();
	}

	/*
	 * ���� ���� ȹ�� ����
	 */
	public void run() throws Exception {
		System.setProperty("org.apache.camel.jmx.disabled", "true");
		DefaultCamelContext ctx = new DefaultCamelContext();
		// ȣ�� Bean ���
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("weatherExtractor", new WeatherExtractor());
		ctx.setRegistry(registry);
		// ��� �Ű� ���� ���
		ctx.addRoutes(this);
		ctx.start();

		// ���� ��� ȣ�� ����
		ProducerTemplate producer = ctx.createProducerTemplate();
		producer.sendBody("direct:seoul", "");

		ctx.stop();
	}

	/*
	 * ��� �Ű� ��� (Endpoint routing mediation description)
	 * ���� ȣ�� ���� --> ���û URL ���� --> dom ���� ��ȯ��
	 * --> Bean ����
	 */
	public void configure() throws Exception {
		 from("direct:seoul").
		// ���û ���� ��⵵ �ְ� ���� XML ��ȸ URL
				to("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109").
				convertBodyTo(Document.class).
				to("bean:weatherExtractor?method=print");
	}
}
