package com.test.movierecordsjsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil {

    public static void msgInfo(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    public static void msgAdvert(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    public static void msgError(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    public static Object obtenerObjetoSesion(String idSesion) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get(idSesion);
    }
}
