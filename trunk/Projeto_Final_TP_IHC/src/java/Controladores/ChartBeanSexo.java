/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanSexo")

/**
 *
 * @author Guilherme
 */
public class ChartBeanSexo  {
 
	private List<Sale> sales;

	public ChartBeanSexo() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Masculino", 540));
		sales.add(new Sale("Feminino", 325));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            