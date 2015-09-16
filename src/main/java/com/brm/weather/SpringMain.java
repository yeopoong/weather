/*******************************************************************************
 *
 * ���ϸ�      : SpingMain.java
 * ����        : Sping Framework ���� ���� ���α׷�
 *
 *******************************************************************************/
package com.brm.weather;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.Main;

public class SpringMain {

	public static void main(String[] args) throws Exception {
		SpringMain main = new SpringMain();
		// EIP ��� ���� Spring Framework XML
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
