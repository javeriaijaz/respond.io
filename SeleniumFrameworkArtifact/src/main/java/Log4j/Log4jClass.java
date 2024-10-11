package Log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jClass {

	private static Logger logger = LogManager.getLogger(Log4jClass.class);
	
	public static void main(String[] args) {
		
		logger.info("This is information message");
		logger.error("This is an error message");
		


	}

}
