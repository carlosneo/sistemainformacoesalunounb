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
@ManagedBean (name="chartBeanTrabalho")

/**
 *
 * @author Neo
 */
public class ChartBeanTrabalho {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
    private List<Sale> sales;

	public ChartBeanTrabalho() {
		ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a informacoes sobre trabalho criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeantrabalhotexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeantrabalhotexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeantrabalhotexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeantrabalhotexto4"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
