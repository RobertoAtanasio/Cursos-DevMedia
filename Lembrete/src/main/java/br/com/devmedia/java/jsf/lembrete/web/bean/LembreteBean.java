package br.com.devmedia.java.jsf.lembrete.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.devmedia.java.jsf.lembrete.web.dao.LembreteDao;
import br.com.devmedia.java.jsf.lembrete.web.model.Lembrete;
import br.com.devmedia.java.jsf.lembrete.web.model.Prioridade;

@ManagedBean
@ViewScoped
public class LembreteBean {

	private Lembrete lembrete;
	private LembreteDao lembreteDao;
	private List<Lembrete> lembretes;

	@PostConstruct
	public void init() {
		lembreteDao = new LembreteDao();
		lembrete = new Lembrete();

		try {
			lembretes = lembreteDao.listarTodos();
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public List<Lembrete> getLembretes() {
		return lembretes;
	}

	public String inserir() {
		try {
			lembreteDao.inserir(lembrete);

			lembretes = lembreteDao.listarTodos();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Lembrete adicionado com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			System.out.println(">>> Exception: " + e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public String atualizar() {
		try {
			lembreteDao.atualizar(lembrete);

			lembretes = lembreteDao.listarTodos();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Lembrete editado com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public String excluir() {
		try {
			lembreteDao.excluir(lembrete.getId());

			lembretes = lembreteDao.listarTodos();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Lembrete removido com sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}

		return "home";
	}

	public void selecionar() {
		try {
			lembrete = lembreteDao.selecionar(lembrete.getId());

			if (lembrete == null || lembrete.getId() == 0) {
				lembrete = new Lembrete();

				throw new Exception("Lembrete não encontrado.");
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public Prioridade[] getPrioridades() {
		return Prioridade.values();
	}
}