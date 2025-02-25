package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Carrinho pegarCarrinhoPorIdToXML(@PathParam("id") Long id) {
	Carrinho carrinho = new CarrinhoDAO().busca(id);
	return carrinho;
    }

    @GET
    @Path("{id}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String pegarCarrinhoPorIdToJson(@PathParam("id") Long id) {
	Carrinho carrinho = new CarrinhoDAO().busca(id);
	return carrinho.toJson();
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response persistir(String conteudo) {
	Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
	new CarrinhoDAO().adiciona(carrinho);
	URI uri = URI.create("/carrinhos/" + carrinho.getId());
	return Response.created(uri).build();
    }

    @DELETE
    @Path("{id}/produtos/{produtoId}")
    public Response removerCarrinho(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
	Carrinho carrinho = new CarrinhoDAO().busca(id);
	carrinho.remove(produtoId);
	return Response.noContent().build();
    }

    @Path("{id}/produtos/{produtoId}")
    @PUT
    public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
	Carrinho carrinho = new CarrinhoDAO().busca(id);
	Produto produto = (Produto) new XStream().fromXML(conteudo);
	carrinho.trocaQuantidade(produto);
	return Response.ok().build();
    }
}
