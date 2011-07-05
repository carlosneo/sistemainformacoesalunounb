/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanQtndPessoasMoram")

/**
 *
 * @author Guilherme
 */
public class ChartBeanQtndPessoasMoram  {

	private List<Sale> sales;

	public ChartBeanQtndPessoasMoram() {
		sales = new ArrayList<Sale>();
                sales.add(new Sale("Nenuma", 540));
		sales.add(new Sale("1 ou 2", 540));
		sales.add(new Sale("3 ou 4", 325));
                sales.add(new Sale("5 ou 6", 100));
                sales.add(new Sale("6 ou Mais", 17));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            