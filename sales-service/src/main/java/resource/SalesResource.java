package resource;

import dao.SaleDAO;
import domain.ErrorMessage;
import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

public class SalesResource extends Jooby {

    public SalesResource(SaleDAO dao) {

        path("/api/sales", () -> {

            get("", ctx -> {
                return dao.getSales();
            });

            post("", ctx -> {
                Sale sales = ctx.body().to(Sale.class);

                if (!dao.doesSaleExist(sales.getId())) {

                    System.out.println(sales);

                    // save the sale
                    dao.save(sales);

                    // return a response containing the complete sale
                    ctx
                            // add the URI for the new resource to the 'location' header in case the client needs to know where the new resource ended up
                            .setResponseHeader("location", ctx.getRequestURL() + "/sale/" + sales.getId())
                            .send(StatusCode.CREATED);
                } else {
                    // Non-unique ID
                    ctx
                            .setResponseCode(StatusCode.UNPROCESSABLE_ENTITY)
                            .render(new ErrorMessage("There is already a sale with that ID."));
                }

                return ctx;

            });

        });

        path("/api/sales/{id}", () -> {
            // Check that the ID is valid so that the other routes don't need to.
            before(ctx -> {
                String id = ctx.path("id").value();

                if (!dao.doesSaleExist(id)) {
                    ctx
                            .setResponseCode(StatusCode.NOT_FOUND)
                            .render(new ErrorMessage(String.format("No sale found with the ID '%s'", id)));
                }
            });
            delete("", ctx -> {
                String id = ctx.path("id").value();
                dao.remove(id);
                return ctx
                        .send(StatusCode.NO_CONTENT);
            });

            get("", ctx -> {
                String id = ctx.path("id").value();
                return dao.getSalesBysaleId(id);
            });

        });

    }

}
