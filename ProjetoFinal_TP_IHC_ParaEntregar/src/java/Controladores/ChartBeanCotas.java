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
@ManagedBean (name="chartBeanCotas")

/**
 *
 * @author Guilherme
 */
public class ChartBeanCotas  {
    
     private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());

	private List<Sale> sales;

	public ChartBeanCotas() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a cotas criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeancotastexto1"), 220));
		sales.add(new Sale(myResource.getString("promptchartbeancotastexto2"), 577));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            