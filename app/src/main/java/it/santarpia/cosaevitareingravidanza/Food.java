package it.santarpia.cosaevitareingravidanza;

public class Food {
    public static final int POS = 1;
    public static final int NEG = 0;
    public static final int SAFE = 1;
    public static final int NOSAFE = 0;
    public static final int ALMOSTSAFE = 2;

    private int id;
    private String name;
    private String description;
    private int toxoplasmosi;
    private int listeriosi;
    private int salmonellosi;
    private int safe;
    private String category;

    public Food(String name, String description, int toxoplasmosi, int listeriosi, int salmonellosi, int safe, String category) {
        this.name = name;
        this.description = description;
        this.toxoplasmosi = toxoplasmosi;
        this.listeriosi = listeriosi;
        this.salmonellosi = salmonellosi;
        this.safe = safe;
        this.category = category;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getToxoplasmosi() {
        return toxoplasmosi;
    }

    public void setToxoplasmosi(int toxoplasmosi) {
        this.toxoplasmosi = toxoplasmosi;
    }

    public int getListeriosi() {
        return listeriosi;
    }

    public void setListeriosi(int listeriosi) {
        this.listeriosi = listeriosi;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSalmonellosi() {
        return salmonellosi;
    }

    public void setSalmonellosi(int salmonellosi) {
        this.salmonellosi = salmonellosi;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", toxoplasmosi=" + toxoplasmosi +
                ", listeriosi=" + listeriosi +
                ", salmonellosi=" + salmonellosi +
                ", safe=" + safe +
                ", category='" + category + '\'' +
                '}';
    }
}
