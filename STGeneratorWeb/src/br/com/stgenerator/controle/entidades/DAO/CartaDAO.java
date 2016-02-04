package br.com.stgenerator.controle.entidades.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.stgenerator.entidades.Carta;

public class CartaDAO {

	public static void init(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		em.close();
	}
	
	public static void inserirCarta(Carta c){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void aumentaViewCarta(Carta c){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Query q = em.createQuery("UPDATE Carta c SET c.views=:v where c.id=:id");
		q.setParameter("v", c.getViews()+1);
		q.setParameter("id", c.getId());
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public static Carta getCarta(Integer id) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Query q = em.createQuery("Select c from Carta c where c.id =:id");
		q.setParameter("id",id);
		Carta c = null;
		if(q.getSingleResult()==null)
			throw new Exception();
		
		c = (Carta) q.getSingleResult();
		em.getTransaction().commit();
		em.close();
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Carta> getCartas(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		List<Carta> cartas;
		
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from Carta c");
		cartas = q.getResultList();
		em.getTransaction().commit();
		em.close();
		
		return cartas;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static CartasPaginado getUltimasCartas(Integer pagina, Integer tamanho){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		CartasPaginado resultado = new CartasPaginado();
		List<Carta> cartas;
		
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from Carta c where c.publico = TRUE AND c.aprovado = TRUE Order by c.dataCriacao DESC");
		q.setFirstResult((pagina-1)*tamanho);			
		q.setMaxResults(tamanho);
		
		cartas = q.getResultList();
		resultado.setCartas(cartas);
		
		q = em.createQuery("Select Count(c) from Carta c where c.publico = TRUE AND c.aprovado = TRUE");
		resultado.setNumeroPaginas(((Long)q.getResultList().get(0))/tamanho);
		em.getTransaction().commit();
		em.close();
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static CartasPaginado getTops(Integer pagina, Integer tamanho){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		CartasPaginado resultado = new CartasPaginado();
		List<Carta> cartas;
		
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from Carta c where c.publico = TRUE AND c.aprovado = TRUE Order by c.views DESC");
		q.setFirstResult((pagina-1)*tamanho);
		q.setMaxResults(tamanho);
		
		cartas = q.getResultList();
		resultado.setCartas(cartas);
		
		q = em.createQuery("Select Count(c) from Carta c where c.publico = TRUE AND c.aprovado = TRUE");
		resultado.setNumeroPaginas(((Long)q.getResultList().get(0))/tamanho);
		em.getTransaction().commit();
		em.close();
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static CartasPaginado getAprovacao(Integer pagina, Integer tamanho){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		CartasPaginado resultado = new CartasPaginado();
		List<Carta> cartas;
		
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from Carta c Order by c.dataCriacao DESC");
		q.setFirstResult((pagina-1)*tamanho);
		q.setMaxResults(tamanho);
		
		cartas = q.getResultList();
		resultado.setCartas(cartas);
		
		q = em.createQuery("Select Count(c) from Carta c");
		resultado.setNumeroPaginas(((Long)q.getResultList().get(0))/tamanho);
		em.getTransaction().commit();
		em.close();
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Carta> getCartasOrderByID(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		List<Carta> cartas;
		
		em.getTransaction().begin();
		Query q = em.createQuery("Select c from Carta c Order by c.id ASC");
		
		cartas = q.getResultList();
		
		q = em.createQuery("Select Count(c) from Carta c");
		em.getTransaction().commit();
		em.close();
		
		return cartas;
	}
	
	public static void aprovarCarta(Integer id,boolean flag){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE Carta c SET c.aprovado=:ap where c.id=:id");
		q.setParameter("ap", flag);
		q.setParameter("id", id);
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();		
	}
	
	public static void publicarCarta(Integer id,boolean flag){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("stg");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE Carta c SET c.publico=:ap where c.id=:id");
		q.setParameter("ap", flag);
		q.setParameter("id", id);
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();		
	}	
}
