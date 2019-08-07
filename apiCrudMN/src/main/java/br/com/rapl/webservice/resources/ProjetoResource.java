package br.com.rapl.webservice.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.rapl.webservice.model.domain.Projeto;
import br.com.rapl.webservice.resources.beans.FilterBean;
import br.com.rapl.webservice.service.ProjetoService;

@Path("/projetos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProjetoResource {

	private ProjetoService service = new ProjetoService();

    @GET
    public List<Projeto> getProjetos(@BeanParam FilterBean projetoFilter) {
        if ((projetoFilter.getOffset() >= 0) && (projetoFilter.getLimit() > 0)) {
            return service.getProjetosByPagination(projetoFilter.getOffset(), projetoFilter.getLimit());
        }
        if (projetoFilter.getNome() != null) {
            return service.getProjetosByName(projetoFilter.getNome());
        }

        return service.getProjetos();
    }

    @GET
    @Path("{projetoId}")
    public Projeto getProjeto(@PathParam("projetoId") long id, @Context UriInfo uriInfo) {
        return service.getProjeto(id);
    }

    @DELETE
    @Path("{projetoId}")
    public Response delete(@PathParam("projetoId") long id) {
        service.deleteProjeto(id);
        return Response.noContent().build();
    }

    @POST
    public Response save(Projeto projeto) {
        projeto = service.saveProjeto(projeto);
        return Response.status(Response.Status.CREATED)
                .entity(projeto)
                .build();
    }

    @PUT
    @Path("{projetoId}")
    public Response update(@PathParam("projetoId") long id, Projeto projeto) {
        projeto.setId(id);
        service.updateProjeto(projeto);
        return Response.noContent().build();
    }
    
    @Path("{projetoId}/empregados")
    public ProjetoEmpregadoResource getProjetoEmpregadoResource() {
        return new ProjetoEmpregadoResource();
    }
}
