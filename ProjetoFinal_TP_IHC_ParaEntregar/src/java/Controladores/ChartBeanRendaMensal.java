/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanRendaMensal")

/**
 *
 * @author Neo
 */
public class ChartBeanRendaMensal {
    
    private List<Sale> sales;

	public ChartBeanRendaMensal() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("ate 3", 40));
		sales.add(new Sale("3 ate 10", 25));
                sales.add(new Sale("10 a 20", 50));
                sales.add(new Sale("20 a 30", 38));
                sales.add(new Sale("Mais de 30", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
