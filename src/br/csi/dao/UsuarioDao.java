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

    //pega ID e SENHA para efetuar login
    @Transactional
    public Usuario getByLoginAndSenha(String login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eqOrIsNull("login", login));
        return (Usuario) criteria.uniqueResult();
    }

    // cadastrar um novo usuario
    @Transactional
    public Usuario cadastraUser(Usuario user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Transactional
    public void deletaUser(Usuario user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional
    public Collection<Usuario> listaUser() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        return criteria.list();
    }

    @Transactional // busca pelo ID
    public Usuario getUsuario(Long id) {
        return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
    }

    @Transactional
    public void alteraUsuario(Usuario user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

}
