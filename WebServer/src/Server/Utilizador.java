package Server;

public class Utilizador
{
    private String nome;
    private String email;
    private String password;
    private Double altura;
    private Experiencia experiencia;

    public Utilizador(String nome, String email, String password, Double altura, Experiencia experiencia) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.altura = altura;
        this.experiencia = experiencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    
}
