package Controladores;

import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped

/**
 * Classe respons�vel pelas mensagens diversas na camada de vis�o.
 */
public class MessagesController {

	/**
	 * Mensagem de informa��o
	 */
	public void addInfo() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sample info message", "PrimeFaces rocks!"));
	}

	/**
	 * Mensagem de aten��o para a senha inv�lida
	 */
	public void addWarn() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Sample warn message", "Senha Inv�lida!!!"));
	}
	/**
	 * Mensagem de aten��o para o usu�rio inv�lido
	 */
	/**
	 * Mensagem de erro
	 */
	public void addError() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sample error message", "PrimeFaces makes no mistakes"));
	}
	/**
	 * Mensagem de um erro muito fatal
	 * @param actionEvent
	 */
	public void addFatal(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sample fatal message", "Fatal Error in System"));
	}
	
	
}
			