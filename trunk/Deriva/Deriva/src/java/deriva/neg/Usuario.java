
package deriva.neg;

import java.sql.Date;


public class Usuario {
    private int idusuario;
    private String email, senha, nome, sobrenome, nickname, mensagempessoal;
    private char sexo;
    private String imagemPerfil;
     private Date dtnasc;

//    public Usuario(String email, String senha, String nome, String sobrenome, String nickname, String mensagempessoal, char sexo, String imagemPerfil, Date dtnasc) {
//        this.email = email;
//        this.senha = senha;
//        this.nome = nome;
//        this.sobrenome = sobrenome;
//        this.nickname = nickname;
//        this.mensagempessoal = mensagempessoal;
//        this.sexo = sexo;
//        this.imagemPerfil = imagemPerfil;
//        this.dtnasc = dtnasc;
//    }

    public Usuario(){
    }
    
    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha, String nome, String sobrenome, Date dtnasc) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dtnasc = dtnasc;
    }
    
    public Date getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(Date dtnasc) {
        this.dtnasc = dtnasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getMensagempessoal() {
        return mensagempessoal;
    }

    public void setMensagempessoal(String mensagempessoal) {
        this.mensagempessoal = mensagempessoal;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Usuario.class.getSimpleName());
		sb.append('[');
		sb.append(email);
		sb.append(',');
		sb.append(senha);
		sb.append(',');
		sb.append(nome);
		sb.append(',');
		sb.append(sobrenome);
		sb.append(',');
		sb.append(dtnasc);
		sb.append(']');
		return sb.toString();
	}
}
