package fiap.tds;

import fiap.tds.models.Produto;
import fiap.tds.repositories.ProdutoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("produtos")
public class ProdutoResource {
    ProdutoRepository produtoRepo = new ProdutoRepository();


    @GET
    public List<Produto> getProdutos(){
        return produtoRepo.getProdutos();
    }

    @GET
    @Path("{id}")
    public Response getProdutos(@PathParam("id") int id){

        var produto = produtoRepo.getProduto(id);
        if(produto == null)
            return  Response.status(404).build();
        return Response.status(200).entity(produto).build();
    }


    @POST
    public Response createProduto (Produto produto){
        if(produto == null)
            return Response.status(400).entity("Produto não pode ser nulo").build();
        produtoRepo.createProduto(produto);
        return Response.status(201).entity(produto).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduto(@PathParam("id") int id, Produto produto){
        ProdutoRepository.produtos.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setNome(produto.getNome());
            p.setDescricao(produto.getDescricao());
            p.setPreco(produto.getPreco());
        });
        return Response.status(204).entity(produto).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        ProdutoRepository.produtos.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
