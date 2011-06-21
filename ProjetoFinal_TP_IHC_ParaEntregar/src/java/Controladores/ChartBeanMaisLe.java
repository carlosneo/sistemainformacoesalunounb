/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanMaisLe")

/**
 *
 * @author Neo
 */
public class ChartBeanMaisLe {
    
	private List<Sale> sales;

	public ChartBeanMaisLe() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Todos os Assuntos", 20));
		sales.add(new Sale("Politica/Economia", 50));
                sales.add(new Sale("Cultura/Arte", 120));
		sales.add(new Sale("Esportes", 250));
		sales.add(new Sale("Outros", 90));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            
