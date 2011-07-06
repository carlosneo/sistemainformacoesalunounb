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
@ManagedBean (name="chartBeanLivrosTipo")

/**
 *
 * @author Neo
 */
public class ChartBeanLivrosTipo {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
 
    private List<Sale> sales;

	public ChartBeanLivrosTipo() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo ao tipo de livros criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeanlivrostipotexto1"), 320));
		sales.add(new Sale(myResource.getString("promptchartbeanlivrostipotexto2"), 75));
                sales.add(new Sale(myResource.getString("promptchartbeanlivrostipotexto3"), 320));
		sales.add(new Sale(myResource.getString("promptchartbeanlivrostipotexto4"), 75));
                sales.add(new Sale(myResource.getString("promptchartbeanlivrostipotexto5"), 75));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
    
    
}
