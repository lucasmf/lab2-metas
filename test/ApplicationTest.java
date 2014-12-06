import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import models.Meta;
import models.dao.GenericDAO;
import org.junit.*;

import play.GlobalSettings;
import play.db.jpa.JPA;

import play.db.jpa.JPAPlugin;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.twirl.api.Content;
import scala.Option;

import javax.persistence.EntityManager;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

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
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void deveComecarVazio() {
        List<Meta> metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(10);
    }

    @Test
    public void deveAdicionarMetas() {
        Meta meta = new Meta();
        dao.persist(meta);
        List<Meta> metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(11);
        meta = new Meta();
        dao.persist(meta);
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(12);
        meta = new Meta();
        dao.persist(meta);
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(13);
        meta = new Meta();
        dao.persist(meta);
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(14);
    }

    @Test
    public void deveRemoverMetas() {
        Meta meta = new Meta("a", "a", 1, 1);
        dao.persist(meta);
        List<Meta> metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(11);
        long removeId = meta.getId();
        dao.removeById(Meta.class, removeId);
        metas = dao.findAllByClass(Meta.class);
        assertThat(metas.size()).isEqualTo(10);
    }



}
