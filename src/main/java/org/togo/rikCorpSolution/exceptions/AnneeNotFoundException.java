package org.togo.rikCorpSolution.exceptions;

public class AnneeNotFoundException extends Exception {
    public AnneeNotFoundException(String year_not_found) {
        super(year_not_found);
    }
}
