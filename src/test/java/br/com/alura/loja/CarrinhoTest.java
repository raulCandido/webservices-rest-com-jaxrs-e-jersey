package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoTest {

    @Before
    public void before() {
	Server.startServer();
    }

    @After
    public void after() {
	Server.stopServer();
    }

    private WebTarget servidorPadrao() {
	ClientConfig config = new ClientConfig();
	config.register(new LoggingFilter());

	Client client = ClientBuilder.newClient(config);

	return client.target("http://localhost:8080");

    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

	String conteudo = servidorPadrao().path("/carrinhos/1").request().get(String.class);
	Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);

	Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    }

    @Test
    public void testarStatusCodeSucessoPost() {

	String carrinhoXML = criarCarrinho();

	Entity<String> entity = Entity.entity(carrinhoXML, MediaType.APPLICATION_XML);

	Response response = servidorPadrao().path("/carrinhos").request().post(entity);
	Assert.assertEquals(201, response.getStatus());
    }

    private String criarCarrinho() {
	Carrinho carrinho = new Carrinho();
	carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
	carrinho.setRua("Rua Vergueiro");
	carrinho.setCidade("Sao Paulo");
	return carrinho.toXML();
    }
}
