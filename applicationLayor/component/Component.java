package applicationLayor.component;

public abstract class Component {
    protected Integer posX;
    protected Integer posY;
    private String type;

    public Component(Integer posX, Integer posY, String type) {
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public abstract String saveData();
    public abstract String identify();

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }
}
