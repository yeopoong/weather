/*******************************************************************************
 *
 * 파일명      : SpingMain.java
 * 설명        : Sping Framework 결합 메인 프로그램
 *
 *******************************************************************************/
package com.brm.weather;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.Main;

public class SpringMain {

	public static void main(String[] args) throws Exception {
		SpringMain main = new SpringMain();
		// EIP 경로 정의 Spring Framework XML
		main.run("classpath:weatherContext.xml");
	}

	public void run(String conf) throws Exception {
		Main camel = new Main();
		camel.setApplicationContextUri(conf);
		camel.start();
		ProducerTemplate producer = camel.getCamelTemplate();
		producer.sendBody("direct:seoul", "");
		camel.stop();
	}
}
