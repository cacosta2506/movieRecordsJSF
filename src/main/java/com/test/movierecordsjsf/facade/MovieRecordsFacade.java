/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.movierecordsjsf.facade;

import com.test.movierecordsjsf.model.MovieRecords;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cacosta
 */
@Stateless
public class MovieRecordsFacade extends AbstractFacade<MovieRecords> {

    @PersistenceContext(unitName = "movieRecordsJSF")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovieRecordsFacade() {
        super(MovieRecords.class);
    }
    
}
