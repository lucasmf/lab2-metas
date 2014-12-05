import play.*;
import models.Meta;
import models.dao.GenericDAO;
import play.db.jpa.JPA;

public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    public void onStart(Application app) {

        JPA.withTransaction(new play.libs.F.Callback0() {

            @Override
            public void invoke() throws Throwable {
                System.out.println("!!rodandoGlobal");
                dao.persist(new Meta("Pagar Si1", "aprender", 1, 5));
                dao.persist(new Meta("Pagar Si2", "aprender mais", 1, 5));
                dao.persist(new Meta("Pagar AA1", "se divertir", 1, 5));
                dao.persist(new Meta("Pagar AA2", "se divertir++", 1, 5));
                dao.persist(new Meta("Pagar AA3", "se divertir+=2", 2, 5));
                dao.persist(new Meta("Pagar AA4", "se divertir+=3", 3, 5));
                dao.persist(new Meta("Pagar AA5", "se divertir+=4", 3, 5));
                dao.persist(new Meta("Pagar AA6", "se divertir+=5", 3, 5));
                dao.persist(new Meta("Pagar AA7", "se divertir+=6", 3, 5));
                dao.persist(new Meta("Se formar", "para ganhar dinheiro", 3, 5));
                dao.flush();

            }

        });

    }

    public void onStop(Application app) {
    }

}

