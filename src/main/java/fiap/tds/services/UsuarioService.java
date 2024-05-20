package fiap.tds.services;

import fiap.tds.models.Usuario;
import fiap.tds.repositories.UsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsuarioService {
    private static final Logger logger = LogManager.getLogger(UsuarioService.class);
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }

    public void create(Usuario usuario){
        // Verifica se o cadastro é válido antes de salvar
        var validation = usuario.validate();

        if(validation.containsKey(false)) {
            logger.error("Erro ao criar o cadastro: {}", validation.get(false).toString());
            throw new IllegalArgumentException(validation.get(false).toString());
        } else {
            try {
                usuarioRepository.createUsuario(usuario);
                logger.info("Cadastro criado com sucesso: {}", usuario);
            } catch (Exception e) {
                logger.error("Erro ao criar o cadastro: {}", e.getMessage());
                throw new RuntimeException("Erro ao criar o cadastro", e);
            }
        }
    }
}
