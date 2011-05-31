/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanRA")
/**
 *
 * @author Neo
 */
public class ChartBeanRA {

	private List<Sale> sales;

	public ChartBeanRA() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Sim", 40));
		sales.add(new Sale("Não", 200));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}