package link;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.Main;

public class DirectRoute {
	
	public static void main(String[] args) throws Exception {
		Main camel = new Main();
		camel.setApplicationContextUri("direct.xml");
		camel.start();
		ProducerTemplate producer = camel.getCamelTemplate();
		producer.sendBody("direct:naver", "Hello World!");
		camel.stop();
	}
}
