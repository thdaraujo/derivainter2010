/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author HAL
 */
public class Usuario {

        private int id;
	private String nome;
        private String sobrenome;


        public Usuario(){
        }


        public Usuario(String nome, String sobrenome){
            this.nome = nome;
            this.sobrenome = sobrenome;
        }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

         public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

}
