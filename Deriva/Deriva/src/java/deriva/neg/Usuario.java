
package deriva.neg;

import java.sql.Date;


public class Usuario {
    private int idusuario;
    private String email, senha, nome, sobrenome, nickname, mensagemPessoal;
    private char sexo;
    private String imagemPerfil;
     private Date dtnasc;

    public Usuario(String email, String senha, String nome, String sobrenome, String nickname, String mensagemPessoal, char sexo, String imagemPerfil, Date dtnasc) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nickname = nickname;
        this.mensagemPessoal = mensagemPessoal;
        this.sexo = sexo;
        this.imagemPerfil = imagemPerfil;
        this.dtnasc = dtnasc;
    }

    public Usuario(){
    }


    public Usuario(int id, String email, String senha, String nome, String sobrenome, String nickname, String mensagemPessoal, char sexo, String imagemPerfil, Date dtnasc) {
        this.idusuario = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nickname = nickname;
        this.mensagemPessoal = mensagemPessoal;
        this.sexo = sexo;
        this.imagemPerfil = imagemPerfil;
        this.dtnasc = dtnasc;
    }

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Usuario (int idusuario, String email, String senha, String nickname){
        this.idusuario = idusuario;
        this.email = email;
        this.senha = senha;
        this.nickname = nickname;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getmensagemPessoal() {
        return mensagemPessoal;
    }

    public void setmensagemPessoal(String mensagemPessoal) {
        this.mensagemPessoal = mensagemPessoal;
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
