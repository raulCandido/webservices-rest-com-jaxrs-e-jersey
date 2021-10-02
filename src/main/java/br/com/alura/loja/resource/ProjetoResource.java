package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

    @GET()
    @Path("{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Projeto pegarProjetoPorIdToXML(@PathParam("id") Long id) {
	Projeto projeto = new ProjetoDAO().busca(id);
	return projeto;
    }

    @GET()
    @Path("{id}/json")
    @Produces(MediaType.APPLICATION_XML)
    public Projeto pegarProjetoPorIdToJSON(@PathParam("id") Long id) {
	Projeto projeto = new ProjetoDAO().busca(id);
	return projeto;
    }

    @POST()
    @Produces(MediaType.APPLICATION_XML)
    public Response persistir(String conteudo) {
	Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
	new ProjetoDAO().adiciona(projeto);
	URI uri = URI.create("/projetos/" + projeto.getId());
	return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response removerProjeto(@PathParam("id") Long id) {
	ProjetoDAO dao = new ProjetoDAO();
	Projeto projeto = dao.busca(id);
	if(projeto != null) {
	    dao.remove(id);
	    return Response.noContent().build();
	}
	return Response.status(Status.BAD_REQUEST).build();
    }

}
