/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanFilhos")

/**
 *
 * @author Neo
 */
public class ChartBeanFilhos {
    
    private List<Sale> sales;

	public ChartBeanFilhos() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Nenhum", 40));
		sales.add(new Sale("1", 25));
                sales.add(new Sale("2", 50));
                sales.add(new Sale("3", 38));
                sales.add(new Sale("4 ou Mais", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
