package br.com.legado33.app.worshipMaterial.exceptions;

public class WorshipMaterialNotFoundException extends RuntimeException {
    public WorshipMaterialNotFoundException(Long id) {
        super("Worship not found with id " + id);
    }
}
