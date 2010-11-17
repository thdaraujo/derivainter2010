//package deriva.neg;
//
//import deriva.db.DAOFactory;
//import deriva.db.userDAO;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Celula {
//    private Celula Pai;
//    private int Id;
//    private boolean Visited;
//    private List<Celula> ListaCelulas = new ArrayList<Celula>();
//    private List<Usuario> ListaAux = new ArrayList<Usuario>();
//    private List<Usuario> ListaAmigos = new ArrayList<Usuario>();
//    private userDAO dao = DAOFactory.getUserDAO();
//
//
//
//    public Celula(Celula Pai, int Id){
//        this.Pai = Pai;
//        this.Id = Id;
//    }
//
//    public Celula(int Id){
//        this.Pai = null;
//        this.Id = Id;
//    }
//
//    private Celula() {
//    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int Id) {
//        this.Id = Id;
//    }
//
//    public Celula getPai() {
//        return Pai;
//    }
//
//    public void setPai(Celula Pai) {
//        this.Pai = Pai;
//    }
//
//    public boolean isVisited() {
//        return Visited;
//    }
//
//    public void setVisited(boolean Visited) {
//        this.Visited = Visited;
//    }
//
//    public Celula getFirst(){
//        Celula aux = new Celula();
//        aux.setPai(this.getPai());
//
//        while (aux.getPai() != null) aux = aux.getPai();
//        return aux;
//    }
//
//   public boolean IsListed(Celula c){
//       boolean islisted = false;
//
//       for (Celula celula : ListaCelulas) {
//           if (celula.getId() == c.getId()) islisted = true;
//       }
//       return islisted;
//   }
//
//   public List<Celula> menorCaminho(Celula inicio, Celula fim) throws SQLException{
//
//       if (inicio.getId() == fim.getId()){
//           ListaCelulas.add(fim);
//           return ListaCelulas;
//       }
//
//       else{
//           ListaAmigos = dao.listarAmigos(inicio.getId());
//
//           for (Usuario usuario : ListaAmigos) {
//               menorCaminho
//           }
//       }
//   }
//
//}

