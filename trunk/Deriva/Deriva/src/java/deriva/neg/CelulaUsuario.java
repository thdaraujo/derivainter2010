package deriva.neg;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.util.ArrayList;
import java.util.List;


public class CelulaUsuario {

    private int contdeusuarios = 1;//Serve para saber qtos ids diferentes ainda existem na lista de usuarios, deixa-se um para fazer a primeira iteração
    private List<Celula> ListadeUsuarios = new ArrayList<Celula>();//Lista q receberá os usuários em célula e só guardará os não repetidos
    private userDAO dao = null;    
    private List<Usuario> ListaCaminho = new ArrayList<Usuario>();

      

	//Checa se o usuário já está na lista
    private boolean nEstaNaLista(int idusuario)
    {
        for (Celula CelUsu : ListadeUsuarios)
        {
            if (idusuario == CelUsu.getId())//Compara os ids do usuario ( q virá do resultset ) com a lista de nao repetidos
            {
                return false;
            }
        }
        return true;
    }

	//Checa o menor Caminho :D
    private void menorCaminho(Celula inicio, Celula fim)//inicio eh o seu perfil normalmente, e fim eh o perfil q vc estah olhando
    {
        Celula celInicio = new Celula(inicio.getId());//Cria a primeira celula com o usuario q estah logado e pai = null!
        if(contdeusuarios > 0)//Numero de usuarios na lista de ids unicos ( pra saber quantas vezes fazer a busca )
        {
            List<Integer> Lista = new ArrayList<Integer>();//Lista q receberá o resultset do banco

            if (dao == null) dao = DAOFactory.getUserDAO();
            Lista = dao.listarAmigosIds(inicio.getId());

            List<Celula> Amigos = new ArrayList<Celula>();//Lista q receberá os amigos de cada usuário q ainda não estão na lista comlpeta de IDs
            for (Integer idusuario : Lista)
            {
                if (ListadeUsuarios.isEmpty() || nEstaNaLista(idusuario))//Se a lista for vazia ou o usuario n existir na lista, adiciona-se ele
                {
                    Celula celUsuario = new Celula(celInicio, idusuario);//Monta a celula com o usuario
                    ListadeUsuarios.add(celUsuario);//Adiciona à lista completa
                    Amigos.add(celUsuario);
                    contdeusuarios += 1;//Aumenta o contador em 1 pra fazer mais uma busca
                    if(idusuario == fim.getId())//Checa se chegou ao usuario final
                    {
                        //retornaCaminho();//Imprime se for o ultimo, pq aih nao precisa mais buscar
						return;
                    }
                }
				contdeusuarios -= 1;//Diminui o contador em 1, pois foi feita uma busca
                for (Celula celU : Amigos) {
                    menorCaminho(celU, fim);//Repete o metodo para cada um da lista de amigos
                }
            }
        }
    }

        public List<Usuario> retornaCaminho(Usuario inicio, Usuario fim){
            Celula celInicio = new Celula(inicio.getIdusuario()); //fica sem pai
            Celula celFim = new Celula(fim.getIdusuario());//fica sem pai
            menorCaminho(celInicio, celFim);
            
            Celula imp = ListadeUsuarios.get(ListadeUsuarios.size() - 1);//Pega a ultima celula de usuario na lista comlpeta e guarda em imp
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
            if (dao == null) dao = DAOFactory.getUserDAO();
             while(imp.getPai() != null){
                 try{
                     Usuario u = dao.FindLoginById(imp.getId());
                     ListaCaminho.add(u);
                     imp = imp.getPai();
                 }catch(Exception ex){}
             }
            return ListaCaminho;
        }




}