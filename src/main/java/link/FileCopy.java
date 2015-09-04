package link;

import org.apache.camel.spring.Main;

public class FileCopy {
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContextUri("fileCopy.xml");
		main.run(args);
	}
}
