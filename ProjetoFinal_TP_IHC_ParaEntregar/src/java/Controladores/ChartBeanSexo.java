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
@ManagedBean (name="chartBeanSexo")

/**
 *
 * @author Guilherme
 */
public class ChartBeanSexo  {
    
        private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
 
	private List<Sale> sales;

	public ChartBeanSexo() {
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo ao sexo dos alunos gerado com sucesso");
		sales.add(new Sale("Masculino", 540));
		sales.add(new Sale("Feminino", 325));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            