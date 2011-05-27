/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanCotas")

/**
 *
 * @author Guilherme
 */
public class ChartBeanCotas  {

	private List<Sale> sales;

	public ChartBeanCotas() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Sim", 420));
		sales.add(new Sale("Não", 50));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            