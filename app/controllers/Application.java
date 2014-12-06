package controllers;

import models.Meta;
import models.dao.GenericDAO;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.Collections;
import java.util.List;

public class Application extends Controller {

    private static Form<Meta> metaForm = Form.form(Meta.class);
    public static GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        //dao.persist(new Meta("minha meta", null, 1));
        System.out.println("rodandoIndex");
        List<Meta> metas = dao.findAllByClass(Meta.class);
        Collections.sort(metas);
        return ok(index.render(metas, "Your new application is ready."));
    }

    @Transactional
    public static Result metas() {
        return redirect(routes.Application.index());
        //return ok(index.render(metas, null));
        //return badRequest();
    }

    @Transactional
    public static Result novaMeta() {
        Form<Meta> filledForm = metaForm.bindFromRequest();
        Meta novaMeta = filledForm.get();
        dao.persist(novaMeta);
        dao.flush();
        return redirect(routes.Application.index());
        //return ok(index.render(metas, null));
    }

    @Transactional
    public static Result deletaMeta(Long id) {
        dao.removeById(Meta.class, id);
        dao.flush();
        return redirect(routes.Application.index());
        //return ok(index.render(metas, null));

    }

    @Transactional
    public static Result alcancarMeta(Long id) {
        Meta meta = dao.findByEntityId(Meta.class, id);
        meta.setAlcancada(1);
        dao.merge(meta);
        dao.flush();
        return redirect(routes.Application.index());
    }

}
