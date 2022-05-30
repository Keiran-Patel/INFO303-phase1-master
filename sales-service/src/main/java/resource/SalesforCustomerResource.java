package resource;

import dao.SaleDAO;
import domain.ErrorMessage;
import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

public class SalesforCustomerResource extends Jooby {

    public SalesforCustomerResource(SaleDAO dao) {

        path("/api/sales/customer/{id}", () -> {
            // Check that the ID is valid so that the other routes don't need to.
            before(ctx -> {
                String id = ctx.path("id").value();

                if (!dao.doesCustomerExist(id)) {
                    ctx
                            .setResponseCode(StatusCode.NOT_FOUND)
                            .render(new ErrorMessage(String.format("No sale found with the ID '%s'", id)));
                }
            });
            //get a customer sale
            get("", ctx -> {
                String id = ctx.path("id").value();
                return dao.getSalesBycustomerId(id);
            });
            //get a customer summary  sale
            get("/summary", ctx -> { //api/sales/customer/{id}/summary
                String id = ctx.path("id").value();
                return dao.getSummary(id);
            });
        });
    }
}
