package ci.atos.apireservationservicedomicile.web.exception;


public class ServiceHomeException extends Exception {
    public ServiceHomeException(String s) {
        super(s);
    }

    public ServiceHomeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceHomeException(Throwable cause) {
        super(cause);
    }
}
