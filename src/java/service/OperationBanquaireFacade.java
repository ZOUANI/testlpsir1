/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.OperationBanquaire;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YOUNES
 */
@Stateless
public class OperationBanquaireFacade extends AbstractFacade<OperationBanquaire> {

    @PersistenceContext(unitName = "testlpsir1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<OperationBanquaire> findByCompte(Compte compte) {
        return em.createQuery("SELECT op FROM OperationBanquaire op WHERE op.compte.id='" + compte.getId() + "'").getResultList();

    }

    public void createOperationDebit(Compte compte, Double montant) {
        createOperation(compte, montant, 2);
    }

    public void createOperationCredit(Compte compte, Double montant) {
        createOperation(compte, montant, 1);
    }

    private void createOperation(Compte compte, Double montant, int type) {
        OperationBanquaire operationBanquaire = new OperationBanquaire();
        operationBanquaire.setMontant(montant);
        operationBanquaire.setDateOperationBanquaire(new Date());
        operationBanquaire.setCompte(compte);
        operationBanquaire.setType(1);
        create(operationBanquaire);
    }

    public OperationBanquaireFacade() {
        super(OperationBanquaire.class);
    }

}
