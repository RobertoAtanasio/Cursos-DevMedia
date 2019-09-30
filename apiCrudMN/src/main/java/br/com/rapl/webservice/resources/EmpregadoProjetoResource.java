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

import br.com.rapl.webservice.model.domain.Projeto;
import br.com.rapl.webservice.service.RelationshipService;


// OBS.: APESAR DA IDE APRESNTAR 'ERRO' ABAIXO, EM TEMPO DE EXECUÇÃO A APLICAÇÃO RECONHECE O CÓDIGO FAZENDO 
// O LINK COM O MÉTODO 'getEmpregadoProjetoResource' EM EmpregadoResource.java


//Indica que nesta classe temos métodos que podem retornar dados no formato json.
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class EmpregadoProjetoResource {

	private RelationshipService service = new RelationshipService();

    @POST
    @Path("{projetoId}")
    public Response save(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.saveRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }

    @GET
    @Path("{empregadoId}")
    public List<Projeto> getProjetos(@PathParam("empregadoId") long empregadoId) {
        return service.getProjetos(empregadoId);
    }

    @DELETE
    @Path("{projetoId}")
    public Response delete(@PathParam("projetoId") long projetoId, @PathParam("empregadoId") long empregadoId) {
        service.deleteRelationshipProjetoEmpregado(projetoId, empregadoId);
        return Response.noContent().build();
    }
}
