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

@Repository
public class PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional // busca POST pelo ID
    public Posts getPostsId(Long id) {
        return (Posts) sessionFactory.getCurrentSession().get(Posts.class, id);
    }

    @Transactional
    public Collection<Posts> listarPosts() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        criteria.addOrder(org.hibernate.criterion.Order.desc("id"));
        criteria.setMaxResults(10);
        return criteria.list();
    }

    @Transactional
    public Collection<Posts> oldPosts() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));
        return criteria.list();
    }

    //lista um post completo pelo ID
    @Transactional
    public Collection<Posts> listarPostsCompleto(Long id) {
        return (Collection<Posts>) sessionFactory.getCurrentSession().get(Posts.class, id);
    }

    @Transactional
    public Collection<Posts> listarPostByUser(Usuario user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Posts.class);
        criteria.add(Restrictions.eq("usuario.id", user.getId()));
        criteria.addOrder(org.hibernate.criterion.Order.asc("data"));
        return criteria.list();
    }

    @Transactional
    public void criaPost(Posts post) {
        sessionFactory.getCurrentSession().saveOrUpdate(post);
    }

    @Transactional
    public void removePost(Posts post) {
        sessionFactory.getCurrentSession().delete(post);
    }
}
