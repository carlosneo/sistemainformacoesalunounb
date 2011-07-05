/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanTrabalho")

/**
 *
 * @author Neo
 */
public class ChartBeanTrabalho {
    
    private List<Sale> sales;

	public ChartBeanTrabalho() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("nao trabalho", 40));
		sales.add(new Sale("trabalho e sou sustentad", 25));
                sales.add(new Sale("trabalho e familia ajuda", 50));
                sales.add(new Sale("tralho e me sustento", 38));
                sales.add(new Sale("trabalho e familia sustenta", 17));
                sales.add(new Sale("trabalho e sustento", 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
