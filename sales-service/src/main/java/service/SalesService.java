package service;

import java.io.IOException;
import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.ServerOptions;
import io.jooby.json.GsonModule;
import dao.SaleDAO;
import resource.SalesResource;
import resource.SalesforCustomerResource;

public class SalesService extends Jooby {

    public SalesService() {

        SaleDAO dao = new SaleDAO();
        setServerOptions(new ServerOptions().setPort(8081));

        install(new GsonModule());
        install(new OpenAPIModule());

        assets("/openapi.json", "sales.json");
        assets("/openapi.yaml", "sales.yaml");
        //Mounted
        mount(new SalesResource(dao));
        mount(new SalesforCustomerResource(dao));

        get("/", ctx -> ctx.sendRedirect("/swagger"));
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\n\n****** Sales Service ******\n\n");
        new SalesService().start();
    }

}
