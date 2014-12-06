import models.Meta;
import models.dao.GenericDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import play.twirl.api.Html;
import scala.Option;
import views.html.index;

import javax.persistence.EntityManager;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;


/**
 * Created by lucas on 12/5/14.
 */
public class ViewTest {

    private GenericDAO dao = new GenericDAO();
    public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication();
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }


    @Test
    public void testaAdicionaMeta() {
        Meta meta = new Meta("Meta", "Meta1", 1, 1);
        dao.persist(meta);
        dao.flush();
        List<Meta> metas = dao.findAllByClass(Meta.class);
        Html html = index.render(metas, "Your new application is ready.");
        assertThat(contentAsString(html)).contains("Meta1");
    }

    @Test
    public void testaAlcancaMeta() {
        Meta meta = new Meta("Meta", "Meta1", 1, 1);
        meta.setAlcancada(1);
        dao.persist(meta);
        dao.flush();
        List<Meta> metas = dao.findAllByClass(Meta.class);
        Html html = index.render(metas, "Your new application is ready.");
        assertThat(contentAsString(html)).contains("Alcançada");

    }

}
