package client;

import link.Message;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.Main;

public class MessageClient {
	
	public void sendMessage(Message message) {
		Main camel = new Main();
		ProducerTemplate producer = null;
		try {
			camel.setApplicationContextUri("classpath:applicationContext.xml");
			camel.start();
			producer = camel.getCamelTemplate();
			producer.sendBody("direct:seoul", message);
			camel.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
