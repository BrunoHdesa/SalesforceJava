package fiap.tds.services;

import fiap.tds.models.Cliente;
import fiap.tds.repositories.ClienteRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClienteService {
    private static final Logger logger = LogManager.getLogger(ClienteService.class);
    private ClienteRepository cadastroRepository;

    public ClienteService(){
        cadastroRepository = new ClienteRepository();
    }

    public void create(Cliente cliente){
        // Verifica se o cliente é válido antes de salvar
        var validation = cliente.validate();

        if(validation.containsKey(false)) {
            logger.error("Erro ao criar o cliente: {}", validation.get(false).toString());
            throw new IllegalArgumentException(validation.get(false).toString());
        } else {
            try {
                cadastroRepository.createCliente(cliente);
                logger.info("Cliente criado com sucesso: {}", cliente);
            } catch (Exception e) {
                logger.error("Erro ao criar o cliente: {}", e.getMessage());
                throw new RuntimeException("Erro ao criar o cliente", e);
            }
        }
    }
}
