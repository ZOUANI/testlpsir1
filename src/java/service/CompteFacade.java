/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author YOUNES
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "testlpsir1PU")
    private EntityManager em;

    @EJB
    private OperationBanquaireFacade operationBanquaireFacade;

    public int debiter(Compte compte, Double montant) {
        Compte loadedCompte = find(compte.getId());
        if (loadedCompte == null) {
            return -1;
        } else {
            compte.setSolde(compte.getSolde() + montant);
            edit(compte);
            operationBanquaireFacade.createOperationDebit(compte, montant);
            return 1;
        }
    }

    public int crediter(Compte compte, Double montant) {
        Compte loadedCompte = find(compte.getId());
        if (loadedCompte == null) {
            return -1;
        } else {
            Double nvSolde = compte.getSolde() - montant;
            if (nvSolde < 0) {
                return -2;
            } else {
                compte.setSolde(nvSolde);
                edit(compte);
                operationBanquaireFacade.createOperationCredit(compte, montant);
                return 1;
            }

        }
    }

    public int save(Compte compte) {
        Compte loadedCompte = find(compte.getId());
        if (loadedCompte != null) {
            return -1;
        } else if (compte.getSolde() < 100) {
            return -2;
        } else {
            create(compte);
            operationBanquaireFacade.createOperationDebit(compte, compte.getSolde());
            return 1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

}
