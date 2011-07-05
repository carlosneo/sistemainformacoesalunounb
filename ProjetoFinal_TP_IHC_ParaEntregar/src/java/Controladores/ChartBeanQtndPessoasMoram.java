/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.util.logging.Logger;
import java.util.*;
import javax.faces.bean.ManagedBean;
import java.util.ResourceBundle;
import java.util.Locale;
import java.text.MessageFormat;
import javax.faces.context.FacesContext;
@ManagedBean (name="chartBeanQtndPessoasMoram")

/**
 *
 * @author Guilherme
 */
public class ChartBeanQtndPessoasMoram  {

	private List<Sale> sales;

	public ChartBeanQtndPessoasMoram() {
                ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
		sales.add(new Sale(myResource.getString("promptchartbeanqtndpessoasmoramtexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanqtndpessoasmoramtexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanqtndpessoasmoramtexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanqtndpessoasmoramtexto4"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
            