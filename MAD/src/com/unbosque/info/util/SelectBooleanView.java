package com.unbosque.info.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean (name = "selectBooleanView")
@SessionScoped
public class SelectBooleanView {
 
    private boolean value1;   
    private boolean value2;
 
    public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
 
    public boolean isValue2() {
        return value2;
    }
 
    public void setValue2(boolean value2) {
        this.value2 = value2;
    }
     
    public void addMessage() {
        String summary = value2 ? "Selecciono Si" : "Selecciono No";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
}