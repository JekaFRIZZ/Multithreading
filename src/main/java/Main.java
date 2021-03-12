import com.epam.task.sixth.entities.Client;
import com.epam.task.sixth.entities.Clients;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final static String FILE_CLIENTS = "src/main/resources/clients.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Clients clientsWrapper = mapper.readValue(new File(FILE_CLIENTS), Clients.class);
        List<Client> clients = clientsWrapper.getClients();

        ExecutorService executor = Executors.newFixedThreadPool(clients.size());
        clients.forEach(client -> executor.submit(client));
        executor.shutdown();
    }

}
