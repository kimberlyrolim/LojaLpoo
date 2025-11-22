package model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaJPA implements InterfaceBD {

    EntityManager entity;
    EntityManagerFactory factory;

    public PersistenciaJPA() {
        try {
            factory = Persistence.createEntityManagerFactory("pu_loja");
            entity = factory.createEntityManager();
            System.out.println("DEBUG: Conexão JPA inicializada com sucesso.");
        } catch (Exception e) {
            System.err.println("ERRO CRÍTICO NA CONEXÃO JPA: Falha ao inicializar o EntityManagerFactory.");
            System.err.println("Verifique o persistence.xml e a conexão com o banco de dados.");
            e.printStackTrace();
        }
    }

    @Override
    public Boolean conexaoAberta() {
        return entity != null && entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        if (entity != null && entity.isOpen()) {
            entity.close();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (factory == null) {
            throw new Exception("Sistema de Persistência não inicializado.");
        }
        EntityManager em = getEntityManager();
        return em.find(c, id);
    }

    @Override
    public void persist(Object o) throws Exception {
        if (factory == null) {
            throw new Exception("Sistema de Persistência não inicializado.");
        }
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o); 
            }
            entity.persist(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao persistir a entidade: " + o.getClass().getSimpleName(), e);
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (factory == null) {
            throw new Exception("Sistema de Persistência não inicializado.");
        }
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o); 
            }
            entity.remove(o); 
            entity.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover entidade: " + o.getClass().getSimpleName());
            e.printStackTrace();
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            throw e;
        }
    }

    
    public EntityManager getEntityManager() {
        if (factory == null) {
            return null;
        }
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }
}