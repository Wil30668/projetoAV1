import java.time.LocalDateTime;

// comunicaçao
abstract class CanalComunicacao {
    abstract void enviarMensagem(String mensagem);
}

// exp
class WhatsApp extends CanalComunicacao {
    private String numeroTelefone;

    WhatsApp(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    void enviarMensagem(String mensagem) {
        System.out.println("Enviando mensagem via WhatsApp para " + numeroTelefone + ": " + mensagem);
    }
}

class Telegram extends CanalComunicacao {
    private String usuario;

    Telegram(String usuario) {
        this.usuario = usuario;
    }

    @Override
    void enviarMensagem(String mensagem) {
        System.out.println("Enviando mensagem via Telegram para @" + usuario + ": " + mensagem);
    }
}

// Mensagem
abstract class Mensagem {
    protected String texto;

    Mensagem(String texto) {
        this.texto = texto;
    }

    abstract String formatar();
}

class MensagemTexto extends Mensagem {
    private LocalDateTime dataEnvio;

    MensagemTexto(String texto, LocalDateTime dataEnvio) {
        super(texto);
        this.dataEnvio = dataEnvio;
    }

    @Override
    String formatar() {
        return "Texto: " + texto + ", Data de Envio: " + dataEnvio;
    }
}

class MensagemVideo extends Mensagem {
    private String arquivo;
    private String formato;
    private int duracao; // Em segundos

    MensagemVideo(String texto, String arquivo, String formato, int duracao) {
        super(texto);
        this.arquivo = arquivo;
        this.formato = formato;
        this.duracao = duracao;
    }

    @Override
    String formatar() {
        return "Vídeo: " + texto + ", Arquivo: " + arquivo + ", Formato: " + formato + ", Duração: " + duracao + "s";
    }
}

public class Main {
    public static void main(String[] args) {
        CanalComunicacao whatsapp = new WhatsApp("123456789");
        CanalComunicacao telegram = new Telegram("exemplo_usuario");

        Mensagem mensagemTexto = new MensagemTexto("ola William teste de mensagem.", LocalDateTime.now());
        Mensagem mensagemVideo = new MensagemVideo("Vídeo legal", "video.mp4", "MP4", 60);

        enviarMensagem(whatsapp, mensagemTexto);
        enviarMensagem(telegram, mensagemVideo);
    }

    static void enviarMensagem(CanalComunicacao canal, Mensagem mensagem) {
        canal.enviarMensagem(mensagem.formatar());
    }
}
