package controllers;

import controllers.*;
import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

import models.FlightSchedule;
import models.users.User;

public class FlightsController extends Controller {
    private FormFactory formFactory;

    @Inject
    public FlightsController(FormFactory f){
        this.formFactory = f;
    }

    public Result flights() {
        List<FlightSchedule> flightsList = FlightSchedule.findAll();
        return ok(displayFlights.render(flightsList, User.getUserById(session().get("email"))));
    }

    public Result addFlight(){
        Form<FlightSchedule> addFlightForm = formFactory.form(FlightSchedule.class);
        return ok(addFlight.render(addFlightForm, User.getUserById(session().get("email"))));
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result addFlightSubmit(){
        Form<FlightSchedule> newFlightForm = formFactory.form(FlightSchedule.class).bindFromRequest();
        if(newFlightForm.hasErrors()){
            return badRequest(addFlight.render(newFlightForm, User.getUserById(session().get("email"))));
        }
        FlightSchedule newFlight = newFlightForm.get();
        newFlight.save();
        flash("success", "Flight " + newFlight.getFlightID() + " has been created/ updated");
        return redirect(controllers.routes.FlightsController.flights());
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result deleteFlight(int id){
        FlightSchedule.find.ref(id).delete();
        flash("success", "Flight has been deleted");
        return redirect(routes.FlightsController.flights());
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result updateFlight(int id) {
        FlightSchedule f;
        Form<FlightSchedule> flightForm;
        try{
            f = FlightSchedule.find.byId(id);
            flightForm = formFactory.form(FlightSchedule.class).fill(f);
        } catch (Exception ex) {
            return badRequest("error");
        }
        return ok(addFlight.render(flightForm, User.getUserById(session().get("email"))));
    }

}