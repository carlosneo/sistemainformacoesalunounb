/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanAtualizado")

/**
 *
 * @author Neo
 */
public class ChartBeanAtualizado {
    
    private List<Sale> sales;

	public ChartBeanAtualizado() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Jornais", 20));
		sales.add(new Sale("Revistas", 100));
                sales.add(new Sale("TV", 420));
		sales.add(new Sale("Rádio", 200));
		sales.add(new Sale("Internet", 500));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
    
}
