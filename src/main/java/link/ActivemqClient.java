package link;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivemqClient {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("activemq-client.xml");
		ProducerTemplate camelTemplate = context.getBean("camelTemplate", ProducerTemplate.class);
		System.out.println("Message Sending started");
		camelTemplate.sendBody("jms:queue:testQSource", "Sample Message");
		System.out.println("Message sent");
	}
}
