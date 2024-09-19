package ci.atos.apireservationservicedomicile.web.exception;

import jakarta.validation.constraints.NotNull;

public class MyRoleNotFoundException extends Exception {
    public MyRoleNotFoundException(@NotNull(message = "Le role est obligatoire") Long roleId) {
        super("Le role est obligatoire: " + roleId);
    }
}
