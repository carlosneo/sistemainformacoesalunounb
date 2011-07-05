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
@ManagedBean (name="chartBeanMoradia")

/**
 *
 * @author Neo
 */
public class ChartBeanMoradia {
    
    private List<Sale> sales;

	public ChartBeanMoradia() {
		ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto4"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto5"), 17));
                sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto6"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanmoradiatexto7"), 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
