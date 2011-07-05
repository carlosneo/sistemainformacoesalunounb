/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanPreVestTempo")

/**
 *
 * @author Neo
 */
public class ChartBeanPreVestTempo  {

	private List<Sale> sales;

	public ChartBeanPreVestTempo() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("1 Semestre", 320));
		sales.add(new Sale("Menos de 1 Semestre", 75));
                sales.add(new Sale("1 Ano", 320));
		sales.add(new Sale("Mais de 1 ano", 75));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            