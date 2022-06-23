package org.acme;

import enums.Moneys;
import interfaces.Currency;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import models.CurrencyFactory;
import models.CurrencyModel;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {


    @CheckedTemplate
            public static class Templates{
        public static native TemplateInstance CurrencyTemplate(CurrencyModel model);
    }

    CurrencyFactory factory_USD = new CurrencyFactory(Moneys.US_DOLLAR);
    CurrencyFactory factory_EUR = new CurrencyFactory(Moneys.EURO);
    Currency cur_USD = factory_USD.getCurrency(); // get selected money unit current infos
    Currency cur_EUR = factory_EUR.getCurrency(); // get selected money unit current infos

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET()
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/GET")
    public CurrencyModel getPrices() {
        return new CurrencyModel(cur_USD.getBuyingPrice(),cur_USD.getSellingPrice(),cur_EUR.getBuyingPrice(),cur_EUR.getSellingPrice());

    }

    @GET
    @Path("currency")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        CurrencyModel model = getPrices();
        return Templates.CurrencyTemplate(model);
    }
}