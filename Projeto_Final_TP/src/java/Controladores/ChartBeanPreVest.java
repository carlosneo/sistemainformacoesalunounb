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
@ManagedBean (name="chartBeanPreVest")

/**
 *
 * @author Guilherme
 */
public class ChartBeanPreVest  {
    
        private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());

	private List<Sale> sales;

	public ChartBeanPreVest() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a informacoes do pre-vestibular criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeanprevesttexto1"), 320));
		sales.add(new Sale(myResource.getString("promptchartbeanprevesttexto2"), 75));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            