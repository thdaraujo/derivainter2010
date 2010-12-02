package deriva.neg;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Celula {
    private Celula Pai;
    private int Id;
    private boolean visited = false;

    public Celula(Celula Pai, int Id){
        this.Pai = Pai;
        this.Id = Id;
    }

    public Celula(int Id){
        this.Pai = null;
        this.Id = Id;    
    }

    public Celula(){}

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public Celula getPai() {
        return Pai;
    }
    public void setPai(Celula Pai) {
        this.Pai = Pai;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean Visited) {
        this.visited = Visited;
    }

    /***
     * pega o pai de todos.
     * @return
     */
    public Celula getFirst(){
        Celula aux = new Celula();
        aux.setPai(this.getPai());

        while (aux.getPai() != null) aux = aux.getPai();
        return aux;
    }  
}

