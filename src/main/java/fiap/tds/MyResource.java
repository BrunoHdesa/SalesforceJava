package fiap.tds;

import fiap.tds.models.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }


    //Produto GET POST PUT DELETE
    @GET
    @Path("produto-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutoTest(){
        return new Produto(1, "CRM", "Produto com grande ",100.0);
    }

    @POST
    @Path("produto-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProdutoTest(Produto produto){
        if(produto.getNome() == null)
            return Response.status(400).entity("Produto não pode ser nulo").build();
        System.out.println(produto.toString());
        return Response.status(201).entity(produto).build();
    }

    @PUT
    @Path("produto-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Produto updateProdutoTest(@PathParam("id") int id, Produto produto){
        return produto;
    }

    @DELETE
    @Path("produto-teste/{id}")
    public Response deleteProdutoTest(@PathParam("id") int id){
        return Response.status(204).build();
    }

    //Usuario GET POST PUT DELETE
    @GET
    @Path("usuario-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioTest(){
        return new Usuario(1, "bruno", "123456bru");
    }

    @POST
    @Path("usuario-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuarioTest(Usuario usuario){
        if(usuario.getNome() == null)
            return Response.status(400).entity("Usuario não pode ser nulo").build();
        System.out.println(usuario.toString());
        return Response.status(201).entity(usuario).build();
    }

    @PUT
    @Path("usuario-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario updateUsuarioTest(@PathParam("id") int id, Usuario usuario){
        return usuario;
    }

    @DELETE
    @Path("usuario-teste/{id}")
    public Response deleteUsuarioTest(@PathParam("id") int id){
        return Response.status(204).build();
    }

    //Pedido GET POST PUT DELETE
    @GET
    @Path("pedido-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido getPedidoTest(){
        return new Pedido(1, LocalDateTime.now());
    }

    @POST
    @Path("pedido-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPedidoTest(Pedido pedido){
        if(pedido.getData() == null)
            return Response.status(400).entity("Pedido não pode ser nulo").build();
        System.out.println(pedido.toString());
        return Response.status(201).entity(pedido).build();
    }

    @PUT
    @Path("pedido-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Pedido updatePedidoTest(@PathParam("id") int id, Pedido pedido){
        return pedido;
    }

    @DELETE
    @Path("pedido-teste/{id}")
    public Response deletePedidoTest(@PathParam("id") int id){
        return Response.status(204).build();
    }

    //Consultor GET POST PUT DELETE
    @GET
    @Path("consultor-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Consultor getConsultorTest(){
        return new Consultor(1, "Bruno", "bruno@gmail.com","11947837691");
    }

    @POST
    @Path("consultor-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createConsultorTest(Consultor consultor){
        if(consultor.getNome() == null)
            return Response.status(400).entity("Consultor não pode ser nulo").build();
        System.out.println(consultor.toString());
        return Response.status(201).entity(consultor).build();
    }

    @PUT
    @Path("consultor-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Consultor updateConsultorTest(@PathParam("id") int id, Consultor consultor){
        return consultor;
    }

    @DELETE
    @Path("consultor-teste/{id}")
    public Response deleteConsultorTest(@PathParam("id") int id){
        return Response.status(204).build();
    }

    //Cliente GET POST PUT DELETE
    @GET
    @Path("cliente-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClienteTest(){
        return new Cliente(1, "11957835794", 50, "arthur@gmail.com", "Arthur");
    }

    @POST
    @Path("cliente-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClienteTest(Cliente cliente){
        if(cliente.getNome() == null)
            return Response.status(400).entity("Cliente não pode ser nulo").build();
        System.out.println(cliente.toString());
        return Response.status(201).entity(cliente).build();
    }

    @PUT
    @Path("cliente-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente updateClienteTest(@PathParam("id") int id, Cliente cliente){
        return cliente;
    }

    @DELETE
    @Path("cliente-teste/{id}")
    public Response deleteClienteTest(@PathParam("id") int id) {
        return Response.status(204).build();
    }

    //Avaliacao GET POST PUT DELETE
    @GET
    @Path("avaliacao-teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Avaliacao getAvaliacaoTest(){
        return new Avaliacao(1, LocalDateTime.of(2004, 10, 11, 20, 10), 10, "gostei do produto");
    }

    @POST
    @Path("avaliacao-teste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAvaliacaoTest(Avaliacao avaliacao){
        if(avaliacao.getNota() < 0)
            return Response.status(400).entity("Avaliação não pode ser negativa").build();
        System.out.println(avaliacao.toString());
        return Response.status(201).entity(avaliacao).build();
    }

    @PUT
    @Path("avaliacao-teste/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Avaliacao updateAvaliacaoTest(@PathParam("id") int id, Avaliacao avaliacao){
        return avaliacao;
    }

    @DELETE
    @Path("avaliacao-teste/{id}")
    public Response deleteAvaliacaoTest(@PathParam("id") int id) {
        return Response.status(204).build();
    }
}
