package fiap.tds;

import fiap.tds.models.Produto;
import fiap.tds.repositories.ProdutoRepository;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in fiap.tds package
        final ResourceConfig rc = new ResourceConfig().packages("fiap.tds");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {

        ProdutoRepository produtoRepository = new ProdutoRepository();

        // Criando um objeto Produto com os dados para inserir
        Produto produto = new Produto(8, "Teste Sitema", "Para testar o comando", 50.30);

        // Chamando o método createProduto() para inserir o produto no banco de dados
        produtoRepository.createProduto(produto);

        // Exibir os produtos
        System.out.println(produtoRepository.getProdutos());

        // Exibir um produto especifico pelo id
        System.out.println(produtoRepository.getProduto(3));

        // Rodando o LocalHost
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();




    }
}

