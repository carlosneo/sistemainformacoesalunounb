/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanLivrosTipo")

/**
 *
 * @author Neo
 */
public class ChartBeanLivrosTipo {
 
    private List<Sale> sales;

	public ChartBeanLivrosTipo() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Obras Literarias(Ficção)", 320));
		sales.add(new Sale("Obras de não ficção", 75));
                sales.add(new Sale("livros Tecnicos", 320));
		sales.add(new Sale("Livros de auto-Ajuda", 75));
                sales.add(new Sale("Outros", 75));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
    
    
}
