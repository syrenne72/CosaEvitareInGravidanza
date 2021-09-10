package it.santarpia.cosaevitareingravidanza;

public class User {
    private String name;

    //0 if toxoplasmosi antobodies aren't present, 1 otherwise
    private int toxoplasmosi;

    public User() {
    }

    public User(String name, int toxoplasmosi) {
        this.name = name;
        this.toxoplasmosi = toxoplasmosi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int isToxoplasmosi() {
        return toxoplasmosi;
    }

    public void setToxoplasmosi(int toxoplasmosi) {
        this.toxoplasmosi = toxoplasmosi;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", toxoplasmosi=" + toxoplasmosi +
                '}';
    }
}
