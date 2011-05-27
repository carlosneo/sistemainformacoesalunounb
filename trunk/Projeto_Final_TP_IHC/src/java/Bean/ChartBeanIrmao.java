/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanIrmao")

/**
 *
 * @author Guilherme
 */
public class ChartBeanIrmao  {

	private List<Sale> sales;

	public ChartBeanIrmao() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("1", 540));
		sales.add(new Sale("2", 325));
                sales.add(new Sale("3", 100));
                sales.add(new Sale("4", 38));
                sales.add(new Sale("5 ou Mais", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            