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
@ManagedBean (name="chartBeanFilhos")

/**
 *
 * @author Neo
 */
public class ChartBeanFilhos {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
    private List<Sale> sales;

	public ChartBeanFilhos() {
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a quantidade de filhos criado com sucesso");
		sales.add(new Sale("Nenhum", 40));
		sales.add(new Sale("1", 25));
                sales.add(new Sale("2", 50));
                sales.add(new Sale("3", 38));
                sales.add(new Sale("4 ou Mais", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
