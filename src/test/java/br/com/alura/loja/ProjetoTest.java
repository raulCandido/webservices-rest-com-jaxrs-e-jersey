package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {
    
    @Before
    public void before() {
	Server.startServer();
    }

    @After
    public void after() {
	Server.stopServer();
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8080");
	String conteudo = target.path("/projetos").request().get(String.class);
	Projeto projeto = (Projeto) new XStream().fromXML(conteudo);

	Assert.assertEquals("Minha loja", projeto.getNome());
    }
}
