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
@ManagedBean (name="chartBeanEscolaridadePai")

/**
 *
 * @author Neo
 */
public class ChartBeanEscolaridadePai {
    
    private static Logger log = Logger.getLogger(ChartBeanSexo.class.getName());
    
    private List<Sale> sales;

	public ChartBeanEscolaridadePai() {
		ResourceBundle myResource = ResourceBundle.getBundle("idiomas.idioma", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		sales = new ArrayList<Sale>();
                log.info("Grafico relativo a escolaridade da mae criado com sucesso");
		sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto1"), 40));
		sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto2"), 25));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto3"), 50));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto4"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto5"), 17));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto6"), 38));
                sales.add(new Sale(myResource.getString("promptchartbeanescolaridadepaitexto7"), 38));
		
	}

	public List<Sale> getSales() {
		return sales;
	}
}
