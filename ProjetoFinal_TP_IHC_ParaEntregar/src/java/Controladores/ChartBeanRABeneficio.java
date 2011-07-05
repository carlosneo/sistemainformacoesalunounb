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
@ManagedBean (name="chartBeanRABeneficio")
/**
 *
 * @author Neo
 */
public class ChartBeanRABeneficio {

	private List<Sale> sales;

	public ChartBeanRABeneficio() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanrabeneficiotexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanrabeneficiotexto1"), 200));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}