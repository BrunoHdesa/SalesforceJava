package fiap.tds;

import fiap.tds.models.Pedido;
import fiap.tds.repositories.PedidoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("pedidos")
public class PedidoResource {
    PedidoRepository pedidoRepo = new PedidoRepository();


    @GET
    public List<Pedido> getPedidos(){
        return pedidoRepo.getPedidos();
    }

    @GET
    @Path("{id}")
    public Response getPedidos(@PathParam("id") int id){

        var pedido = pedidoRepo.getPedido(id);
        if(pedido == null)
            return  Response.status(404).build();
        return Response.status(200).entity(pedido).build();
    }


    @POST
    public Response createPedido (Pedido pedido){
        if(pedido == null)
            return Response.status(400).entity("Pedido não pode ser nulo").build();
        pedidoRepo.createProduto(pedido);
        return Response.status(201).entity(pedido).build();
    }

    @PUT
    @Path("{id}")
    public Response updatePedido(@PathParam("id") int id, Pedido pedido){
        PedidoRepository.pedidos.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setData(pedido.getData());
        });
        return Response.status(204).entity(pedido).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePedido(@PathParam("id") int id){
        PedidoRepository.pedidos.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
