/*******************************************************************************
 *
 * 파일명      : Main.java
 * 설명        : 메인 프로그램
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
	 * 날씨 정보 획득 실행
	 */
	public void run() throws Exception {
		System.setProperty("org.apache.camel.jmx.disabled", "true");
		DefaultCamelContext ctx = new DefaultCamelContext();
		// 호출 Bean 등록
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("weatherExtractor", new WeatherExtractor());
		ctx.setRegistry(registry);
		// 경로 매개 정보 등록
		ctx.addRoutes(this);
		ctx.start();

		// 종점 경로 호출 시작
		ProducerTemplate producer = ctx.createProducerTemplate();
		producer.sendBody("direct:seoul", "");

		ctx.stop();
	}

	/*
	 * 경로 매개 기술 (Endpoint routing mediation description)
	 * 직접 호출 종점 --> 기상청 URL 종점 --> dom 형식 변환기
	 * --> Bean 종점
	 */
	public void configure() throws Exception {
		 from("direct:seoul").
		// 기상청 서울 경기도 주간 날씨 XML 조회 URL
				to("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109").
				convertBodyTo(Document.class).
				to("bean:weatherExtractor?method=print");
	}
}
