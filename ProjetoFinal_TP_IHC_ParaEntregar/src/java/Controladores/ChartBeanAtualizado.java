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
@ManagedBean (name="chartBeanAtualizado")

/**
 *
 * @author Neo
 */
public class ChartBeanAtualizado {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
    private List<Sale> sales;

	public ChartBeanAtualizado() {
                 ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanatualizadotexto1"), 20));
		sales.add(new Sale(myResource.getString("promptchartbeanatualizadotexto2"), 100));
                sales.add(new Sale(myResource.getString("promptchartbeanatualizadotexto3"), 420));
		sales.add(new Sale(myResource.getString("promptchartbeanatualizadotexto4"), 200));
		sales.add(new Sale(myResource.getString("promptchartbeanatualizadotexto5"), 500));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
    
}
