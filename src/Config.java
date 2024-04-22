public class Config {
    private static  Config instance = null;

    private boolean loggingEnabled;

    private Config(){
        loggingEnabled = false;
    }

    public static synchronized Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public void disableLogging() {
        this.loggingEnabled = false;
    }

    public void enableLogging() {
        this.loggingEnabled = true;
    }
}
