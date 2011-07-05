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
@ManagedBean (name="chartBeanEscolaridadeMae")

/**
 *
 * @author Neo
 */
public class ChartBeanEscolaridadeMae {
    
    private List<Sale> sales;

	public ChartBeanEscolaridadeMae() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto4"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto5"), 17));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto6"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridademaetexto7"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
