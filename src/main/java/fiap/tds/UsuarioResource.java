package fiap.tds;

import fiap.tds.models.Usuario;
import fiap.tds.repositories.UsuarioRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("usuarios")
public class UsuarioResource {
    UsuarioRepository usuarioRepo = new UsuarioRepository();

    @GET
    public List<Usuario> getUsuarios(){
        return usuarioRepo.getUsuarios();
    }

    @GET
    @Path("{id}")
    public Response getUsuarios(@PathParam("id") int id){
        var usuario = usuarioRepo.getUsuario(id);
        if(usuario == null)
            return  Response.status(404).build();
        return Response.status(200).entity(usuario).build();
    }


    @POST
    public Response createUsuario(Usuario usuario){
        if(usuario == null)
            return Response.status(400).entity("Usuario não pode ser nulo").build();
        usuarioRepo.createUsuario(usuario);
        return Response.status(201).entity(usuario).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario){
        UsuarioRepository.usuarios.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setNome(usuario.getNome());
            p.setSenha(usuario.getSenha());
        });
        return Response.status(204).entity(usuario).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUsuario(@PathParam("id") int id){
        UsuarioRepository.usuarios.removeIf(p -> p.getId() == id);
        return Response.status(204).build();
    }
}
