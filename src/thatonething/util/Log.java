package thatonething.util;

import com.sun.istack.internal.logging.Logger;
import java.util.logging.Level;

public class Log {
    
    private Logger logger;
    
    public Log() {
        this.logger = Logger.getLogger(this.getClass());
    }
    
    public Log(Class<?> classToUse) {
        this.logger = Logger.getLogger(classToUse);
    }
    
    public void log(Level level, String message) {
        logger.log(level, message);
    }
}