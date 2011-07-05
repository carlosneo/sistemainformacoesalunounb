/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.logging.Logger;
import java.util.*;
import javax.faces.bean.ManagedBean;
import java.util.ResourceBundle;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean (name="chartBeanCor")

/**
 *
 * @author Guilherme
 */
public class ChartBeanCor  {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());

	private List<Sale> sales;

	public ChartBeanCor() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo ao tipo de pele gerado com sucesso");
		sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto1"), 540));
		sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto2"), 325));
                sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto3"), 100));
                sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto4"), 38));
                sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto5"), 17));
                sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto6"), 38));
                sales.add(new Sale(myResource.getString("prompt.chartbeancor.texto7"), 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            