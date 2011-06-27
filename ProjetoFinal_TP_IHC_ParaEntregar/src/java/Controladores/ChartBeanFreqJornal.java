/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanFreqJornal")

/**
 *
 * @author Neo
 */
public class ChartBeanFreqJornal {

	private List<Sale> sales;

	public ChartBeanFreqJornal() {
		sales = new ArrayList<Sale>();
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
            