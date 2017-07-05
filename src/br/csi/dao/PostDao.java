package br.csi.dao;

import java.util.Collection;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.csi.modelo.Posts;
import br.csi.modelo.Usuario;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;

@Repository
public class PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Posts getPostsId(Long id) {
        return (Posts) sessionFactory.getCurrentSession().get(Posts.class, id);
    }

    @Transactional
    public Collection<Posts> listarPosts() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        criteria.addOrder(desc("id"));
        criteria.setMaxResults(10);
        return criteria.list();
    }

    @Transactional
    public Collection<Posts> postsAntigos() {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        c.addOrder(asc("id"));
        return c.list();
    }

    @Transactional
    public void criaPost(Posts post) {
        sessionFactory.getCurrentSession().saveOrUpdate(post);
    }

    @Transactional
    public void removePost(Posts post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    @Transactional
    public Collection<Posts> listarPostsCompleto(Long id) {
        return (Collection<Posts>) sessionFactory.getCurrentSession().get(Posts.class, id);
    }

    @Transactional
    public Collection<Posts> listarPostByUser(Usuario u) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        c.add(Restrictions.eq("usuario.id", u.getId()));
        c.addOrder(org.hibernate.criterion.Order.asc("data"));
        return c.list();
    }
}
