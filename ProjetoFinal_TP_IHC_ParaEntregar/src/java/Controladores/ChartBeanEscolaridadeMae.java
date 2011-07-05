/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanEscolaridadeMae")

/**
 *
 * @author Neo
 */
public class ChartBeanEscolaridadeMae {
    
    private List<Sale> sales;

	public ChartBeanEscolaridadeMae() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Nenhuma", 40));
		sales.add(new Sale("Ensino fundamental", 25));
                sales.add(new Sale("ensino fundamental 5 a 8", 50));
                sales.add(new Sale("ensino medio", 38));
                sales.add(new Sale("Superior", 17));
                sales.add(new Sale("pos", 38));
                sales.add(new Sale("desconheco", 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
