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
@ManagedBean (name="chartBeanFreqJornal")

/**
 *
 * @author Neo
 */
public class ChartBeanFreqJornal {
    
        private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());

	private List<Sale> sales;

	public ChartBeanFreqJornal() {
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a frequencia de leitura criado com sucesso");
		sales.add(new Sale("Diariamente", 6));
		sales.add(new Sale("Algumas vezes por semana", 50));
                sales.add(new Sale("Somente Domingos", 100));
		sales.add(new Sale("Raramente", 250));
		sales.add(new Sale("Nunca", 50));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            