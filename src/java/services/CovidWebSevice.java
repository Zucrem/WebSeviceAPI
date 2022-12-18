/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Covidweek;

/**
 *
 * @author Zucrem
 */
@WebService(serviceName = "CovidWebSevice")
public class CovidWebSevice {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CovidSoapPU");

    /**
     * Web service operation
     */
    
    @WebMethod(operationName = "findAllCovidWeek")
    public List <Covidweek> findAllCovidWeek() {
        EntityManager em = emf.createEntityManager();
        List<Covidweek> coList = (List<Covidweek>)em.createNamedQuery("Covidweek.findAll").getResultList();
        return coList;
    }

    @WebMethod(operationName = "insertCovidWeek")
    @Oneway
    public void insertCovidWeek(@WebParam(name = "covidWeek") Covidweek covidWeek) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(covidWeek);
            em.getTransaction().commit();
        } catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }

    /**
     * This is a sample web service operation
     */
    
}
