package link;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivemqExample {
	public static final void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("activemq.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		try {
			camelContext.start();
			Thread.sleep(2000);
		} finally {
			camelContext.stop();
		}
	}
}
