package br.com.alura.codechella.domain.email;

public interface EnviadorDeEmail {

    void enviar(String destinatario, String assunto, String mensagem);

}
