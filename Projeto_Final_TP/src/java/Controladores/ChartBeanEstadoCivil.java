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
@ManagedBean (name="chartBeanEstadoCivil")

/**
 *
 * @author Neo
 */
public class ChartBeanEstadoCivil {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
    private List<Sale> sales;

	public ChartBeanEstadoCivil() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo ao estado civil criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto4"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto5"), 17));
                sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto6"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanestadociviltexto7"), 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
