/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanPreVest")

/**
 *
 * @author Guilherme
 */
public class ChartBeanPreVest  {

	private List<Sale> sales;

	public ChartBeanPreVest() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Sim", 320));
		sales.add(new Sale("Não", 75));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            