package deriva.neg;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.util.ArrayList;
import java.util.List;


public class CelulaUsuario {

    private int contdeusuarios = 1;//Serve para saber qtos ids diferentes ainda existem na lista de usuarios, deixa-se um para fazer a primeira iteração
    private Usuario user;
    private CelulaUsuario pai;
    private List<CelulaUsuario> ListadeUsuarios = new ArrayList<CelulaUsuario>();//Lista q receberá os usuários em célula e só guardará os não repetidos
    private userDAO dao = DAOFactory.getUserDAO();
    private List<Usuario> retorno = new ArrayList<Usuario>();
   private List<Usuario> ListaCaminho = new ArrayList<Usuario>();

   

//Construtores, Getters e Setters"
    public CelulaUsuario(CelulaUsuario pai, Usuario usuario) {
        this.user = usuario;
        this.pai = pai;
    }

    public CelulaUsuario() {
    }

    public CelulaUsuario(Usuario usuario) {
        this.user = usuario;
    }

     public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario usuario) {
        this.user = usuario;
    }

    public CelulaUsuario getPai() {
        return pai;
    }

    public void setPai(CelulaUsuario pai) {
        this.pai = pai;
    }

	//Checa se o usuário já está na lista
    private boolean nEstaNaLista(Usuario usuario)
    {
        for (CelulaUsuario CelUsu : ListadeUsuarios)
        {
            if (usuario.getIdusuario() == CelUsu.getUsuario().getIdusuario())//Compara os ids do usuario ( q virá do resultset ) com a lista de nao repetidos
            {
                return false;
            }
        }
        return true;
    }

	//Checa o menor Caminho :D
    public void menorCaminho(Usuario inicio, Usuario fim)//inicio eh o seu perfil normalmente, e fim eh o perfil q vc estah olhando
    {
        CelulaUsuario celInicio = new CelulaUsuario(null, inicio);//Cria a primeira celula com o usuario q estah logado
        if(contdeusuarios > 0)//Numero de usuarios na lista de ids unicos ( pra saber quantas vezes fazer a busca )
        {
            List<Usuario> Lista = new ArrayList<Usuario>();//Lista q receberá o resultset do banco


            Lista = dao.listarAmigos(inicio.getIdusuario());

            List<CelulaUsuario> Amigos = new ArrayList<CelulaUsuario>();//Lista q receberá os amigos de cada usuário q ainda não estão na lista comlpeta de IDs
            for (Usuario usuario : Lista)
            {
                if (ListadeUsuarios.isEmpty() || nEstaNaLista(usuario))//Se a lista for vazia ou o usuario n existir na lista, adiciona-se ele
                {
                    CelulaUsuario celUsuario = new CelulaUsuario(celInicio, usuario);//Monta a celula com o usuario
                    ListadeUsuarios.add(celUsuario);//Adiciona à lista completa
                    Amigos.add(celUsuario);
                    contdeusuarios += 1;//Aumenta o contador em 1 pra fazer mais uma busca
                    if(usuario.getIdusuario() == fim.getIdusuario())//Checa se chegou ao usuario final
                    {
                        //retornaCaminho();//Imprime se for o ultimo, pq aih nao precisa mais buscar
						return;
                    }
                }
				contdeusuarios -= 1;//Diminui o contador em 1, pois foi feita uma busca
                for (CelulaUsuario celU : Amigos) {
                    menorCaminho(celU.getUsuario(), fim);//Repete o metodo para cada um da lista de amigos
                }
            }
        }
    }

        public List<Usuario> retornaCaminho(){
            CelulaUsuario imp = ListadeUsuarios.get(ListadeUsuarios.size());//Pega a ultima celula de usuario na lista comlpeta e guarda em imp
//

           // while(imp.getPai() != null){
//                System.out.println(">" + imp.getUsuario().getNickname());//Imprime imp
//
//                Usuario atual = imp.getUsuario();
//                if (atual != null) retorno.add(atual);
//
//                imp = imp.getPai();//imp recebe o pai dele
//            }
//            return retorno;

             while(imp.getPai() != null){
                 Usuario u = imp.getUsuario();
                 ListaCaminho.add(u);
                 imp = imp.getPai();
             }
            return ListaCaminho;
        }




}