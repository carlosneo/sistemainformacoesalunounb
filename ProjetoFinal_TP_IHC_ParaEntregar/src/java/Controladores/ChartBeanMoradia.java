/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanMoradia")

/**
 *
 * @author Neo
 */
public class ChartBeanMoradia {
    
    private List<Sale> sales;

	public ChartBeanMoradia() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Branco", 40));
		sales.add(new Sale("Negro", 25));
                sales.add(new Sale("Pardo/Mulato", 50));
                sales.add(new Sale("Amarelo", 38));
                sales.add(new Sale("Ocidental", 17));
                sales.add(new Sale("Indigena", 38));
                sales.add(new Sale("Outro", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
