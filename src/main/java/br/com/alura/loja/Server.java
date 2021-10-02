package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        startServer();
        System.in.read();
        stopServer();
    }

    public static void startServer() {
        URI uri = URI.create("http://localhost:8080/");
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        System.out.println("Servidor rodando");
    }

    public static void stopServer() {
        server.stop();
    }
}
