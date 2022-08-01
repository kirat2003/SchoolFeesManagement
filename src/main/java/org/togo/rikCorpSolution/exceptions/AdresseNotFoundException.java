package org.togo.rikCorpSolution.exceptions;

public class AdresseNotFoundException extends Throwable {
    public AdresseNotFoundException(String not_found) {
        super(not_found);
    }
}
