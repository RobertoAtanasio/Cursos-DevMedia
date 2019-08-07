package br.com.rapl.webservice.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.rapl.webservice.model.domain.Empregado;
import br.com.rapl.webservice.service.RelationshipService;

//OBS.: APESAR DA IDE APRESNTAR 'ERRO' ABAIXO, EM TEMPO DE EXECU��O A APLICA��O RECONHECE O C�DIGO FAZENDO 
//O LINK COM O M�TODO 'getProjetoEmpregadoResource' EM ProjetooResource.java

// Indica que nesta classe temos m�todos que podem retornar dados no formato json.
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProjetoEmpregadoResource {

	private RelationshipService service = new RelationshipService();

    @POST
    @Path("{empregadoId}")
    public Response save(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.saveRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }

    @GET
    public List<Empregado> getEmpregados(@PathParam("projetoId") long projetoId) {
        return service.getEmpregados(projetoId);
    }

    @DELETE
    @Path("{empregadoId}")
    public Response delete(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.deleteRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }
}
