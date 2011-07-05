/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.logging.Logger;
import java.util.*;
import javax.faces.bean.ManagedBean;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.MessageFormat;
import javax.faces.context.FacesContext;
@ManagedBean (name="chartBeanRendaMensal")

/**
 *
 * @author Neo
 */
public class ChartBeanRendaMensal {
    
    private List<Sale> sales;

	public ChartBeanRendaMensal() {
		ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanrendamensaltexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanrendamensaltexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanrendamensaltexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanrendamensaltexto4"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
