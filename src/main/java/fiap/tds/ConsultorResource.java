package fiap.tds;

import fiap.tds.models.Consultor;
import fiap.tds.repositories.ConsultorRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("consultores")
public class ConsultorResource {
    ConsultorRepository consultorRepo = new ConsultorRepository();


    @GET
    public List<Consultor> getConsultores(){
        return consultorRepo.getConsultores();
    }

    @GET
    @Path("{id}")
    public Response getConsultor(@PathParam("id") int id){

        var consultor = consultorRepo.getConsultor(id);
        if(consultor == null)
            return  Response.status(404).build();
        return Response.status(200).entity(consultor).build();
    }


    @POST
    public Response createConsultor (Consultor consultor){
        if(consultor == null)
            return Response.status(400).entity("Consultor não pode ser nulo").build();
        consultorRepo.createConsultor(consultor);
        return Response.status(201).entity(consultor).build();
    }

    @PUT
    @Path("{id}")
    public Response updateConsultor(@PathParam("id") int id, Consultor consultor){
        ConsultorRepository.consultores.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setNome(consultor.getNome());
            p.setEmail(consultor.getEmail());
            p.setTelefone(consultor.getTelefone());
        });
        return Response.status(204).entity(consultor).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
        ConsultorRepository.consultores.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
