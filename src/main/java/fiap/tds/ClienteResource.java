package fiap.tds;

import fiap.tds.models.Cliente;
import fiap.tds.models.Consultor;
import fiap.tds.repositories.ClienteRepository;
import fiap.tds.repositories.ConsultorRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("clientes")
public class ClienteResource {
    ClienteRepository clienteRepo = new ClienteRepository();


    @GET
    public List<Cliente> getClientes(){
        return clienteRepo.getClientes();
    }

    @GET
    @Path("{id}")
    public Response getCliente(@PathParam("id") int id){

        var cliente = clienteRepo.getCliente(id);
        if(cliente == null)
            return  Response.status(404).build();
        return Response.status(200).entity(cliente).build();
    }


    @POST
    public Response createCliente (Cliente cliente){
        if(cliente == null)
            return Response.status(400).entity("Cliente não pode ser nulo").build();
        clienteRepo.createCliente(cliente);
        return Response.status(201).entity(cliente).build();
    }

    @PUT
    @Path("{id}")
    public Response updateCliente(@PathParam("id") int id, Cliente cliente){
        ClienteRepository.clientes.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setTelefone(cliente.getTelefone());
            p.setTm_empresa(cliente.getTm_empresa());
            p.setEmail(cliente.getEmail());
            p.setNome(cliente.getNome());
        });
        return Response.status(204).entity(cliente).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCliente(@PathParam("id") int id){
        ClienteRepository.clientes.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
