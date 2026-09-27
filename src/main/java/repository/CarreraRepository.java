package repository;

import main.java.dto.CarreraDTO;
import java.util.List;

public interface CarreraRepository{
    void insertarDesdeCSV(String rutaArchivo);
    void matricularEstudiante(); //punto 2-b
    //...
}