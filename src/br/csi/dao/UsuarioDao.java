package br.csi.dao;

import java.util.Collection;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.csi.modelo.Usuario;

@Repository
public class UsuarioDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Usuario getByLoginAndSenha(String l) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        c.add(Restrictions.eqOrIsNull("login", l));
        return (Usuario) c.uniqueResult();
    }

    @Transactional
    public Usuario cadastraUser(Usuario u) {
        sessionFactory.getCurrentSession().save(u);
        return u;
    }

    @Transactional
    public void deletaUser(Usuario u) {
        sessionFactory.getCurrentSession().delete(u);
    }

    @Transactional
    public Collection<Usuario> listaUser() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        return c.list();
    }

    @Transactional
    public Usuario getUsuario(Long id) {
        return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
    }

    @Transactional
    public void alteraUsuario(Usuario u) {
        sessionFactory.getCurrentSession().saveOrUpdate(u);
    }

}
