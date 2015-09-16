package link;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivemqExample {
	public static final void main(String[] args) throws Exception {
		/*
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("testBean", new TestBean());
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		*/

		ApplicationContext appContext = new ClassPathXmlApplicationContext("activemq.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		try {
			camelContext.start();
			Thread.sleep(2000);
//			ProducerTemplate camelTemplate = appContext.getBean("camelTemplate", ProducerTemplate.class);
			ProducerTemplate camelTemplate = camelContext.createProducerTemplate();
			System.out.println("Message Sending started");
			camelTemplate.sendBody("activemq:queue:in", "Sample Message");
//			camelTemplate.sendBody("jms:queue:testQSource", "Sample Message");
			System.out.println("Message sent");
		} finally {
//			camelContext.stop();
		}
	}
}
