package fiap.tds;

import fiap.tds.models.Avaliacao;
import fiap.tds.repositories.AvaliacaoRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("avaliacoes")
public class AvaliacaoResource {
    AvaliacaoRepository avaliacaoRepo = new AvaliacaoRepository();

    @GET
    public List<Avaliacao> getAvaliacoes(){
        return avaliacaoRepo.getAvaliacoes();
    }

    @GET
    @Path("{id}")
    public Response getAvaliacoes(@PathParam("id") int id){

        var avaliacao = avaliacaoRepo.getAvaliacao(id);
        if(avaliacao == null)
            return  Response.status(404).build();
        return Response.status(200).entity(avaliacao).build();
    }


    @POST
    public Response createAvaliacao (Avaliacao avaliacao){
        if(avaliacao == null)
            return Response.status(400).entity("Avaliação não pode ser nula").build();
        avaliacaoRepo.createAvaliacao(avaliacao);
        return Response.status(201).entity(avaliacao).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduto(@PathParam("id") int id, Avaliacao avaliacao){
        AvaliacaoRepository.avaliacoes.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setData(avaliacao.getData());
            p.setNota(avaliacao.getNota());
            p.setComentario(avaliacao.getComentario());
        });
        return Response.status(204).entity(avaliacao).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAvaliacao(@PathParam("id") int id){
        AvaliacaoRepository.avaliacoes.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
