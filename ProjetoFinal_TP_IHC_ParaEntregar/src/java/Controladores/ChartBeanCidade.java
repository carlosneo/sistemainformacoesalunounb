/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.*;
import javax.faces.bean.ManagedBean;
@ManagedBean (name="chartBeanCidade")


/**
 *
 * @author Neo
 */
public class ChartBeanCidade {
    
private List<Sale> sales;

	public ChartBeanCidade() {
		sales = new ArrayList<Sale>();
		sales.add(new Sale("Aguas Claras", 120));
		sales.add(new Sale("Asa Norte", 200));
                sales.add(new Sale("Asa Sul", 420));
		sales.add(new Sale("Brazlandia", 300));
		sales.add(new Sale("Candangolandia", 500));
		sales.add(new Sale("Ceilandia", 120));
		sales.add(new Sale("Cidade Ocidental/GO", 200));
                sales.add(new Sale("Cruzeiro", 420));
		sales.add(new Sale("Gama", 300));
		sales.add(new Sale("Guara", 500));
		sales.add(new Sale("Itapoã", 120));
		sales.add(new Sale("Lago Norte", 200));
                sales.add(new Sale("Lago Sul", 420));
		sales.add(new Sale("Luziania/GO", 300));
		sales.add(new Sale("Novo Gama/GO", 500));
		sales.add(new Sale("Nucleo Bandeirante", 500));
		sales.add(new Sale("Paranoa", 120));
		sales.add(new Sale("Park Way", 200));
                sales.add(new Sale("Planaltina", 420));
		sales.add(new Sale("Recanto das Emas", 300));
		sales.add(new Sale("Riacho Fundo", 500));
		sales.add(new Sale("Riacho Fundo II", 120));
		sales.add(new Sale("Samambaia", 200));
                sales.add(new Sale("Santa Maria", 420));
		sales.add(new Sale("Santo Antonio do Descoberto;GO", 300));
		sales.add(new Sale("Sao Sebastiao", 500));
		sales.add(new Sale("Sobradinho", 300));
		sales.add(new Sale("Sobradinho II", 500));
		sales.add(new Sale("Sudoeste e Octogonal", 200));
                sales.add(new Sale("Taguatinga", 420));
		sales.add(new Sale("Valparaizo/GO", 300));
		sales.add(new Sale("Varjão", 500));
		sales.add(new Sale("Outro", 300));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
    
}