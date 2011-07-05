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
@ManagedBean (name="chartBeanPreVestTempo")

/**
 *
 * @author Neo
 */
public class ChartBeanPreVestTempo  {
    
        private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());

	private List<Sale> sales;

	public ChartBeanPreVestTempo() {
	ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a informacoes de tempo do pre-vestibular criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeanprevesttempotexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanprevesttempotexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanprevesttempotexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanprevesttempotexto4"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            