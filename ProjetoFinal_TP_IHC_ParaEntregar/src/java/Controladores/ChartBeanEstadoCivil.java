/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanEstadoCivil")

/**
 *
 * @author Neo
 */
public class ChartBeanEstadoCivil {
    
    private List<Sale> sales;

	public ChartBeanEstadoCivil() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Com os pais", 40));
		sales.add(new Sale("Com os familiares", 25));
                sales.add(new Sale("Com esposa/filho", 50));
                sales.add(new Sale("Amigos", 38));
                sales.add(new Sale("Alojamento Universitario", 17));
                sales.add(new Sale("Sozinho", 38));
                sales.add(new Sale("Outro", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
