package br.com.alura.codechella.infra.email;

import br.com.alura.codechella.domain.email.EnviadorDeEmail;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class EnviadorDeEmailFake implements EnviadorDeEmail {

    @Async
    public void enviar(String destinatario, String assunto, String mensagem) {
        System.out.println("===== Simulando envio de email =====");
        System.out.println("Destinatario: " +destinatario);
        System.out.println("Assunto: " +assunto);
        System.out.println("Mensagem: " +mensagem);
    }

}
