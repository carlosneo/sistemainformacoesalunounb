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
@ManagedBean (name="chartBeanIrmao")

/**
 *
 * @author Guilherme
 */
public class ChartBeanIrmao  {
    
        private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
	private List<Sale> sales;

	public ChartBeanIrmao() {
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a quantidade de irmaoes criado com sucesso");
		sales.add(new Sale("1", 540));
		sales.add(new Sale("2", 325));
                sales.add(new Sale("3", 100));
                sales.add(new Sale("4", 38));
                sales.add(new Sale("5 ou Mais", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            