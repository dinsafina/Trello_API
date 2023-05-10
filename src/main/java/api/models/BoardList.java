package api.models;

public class BoardList {
    String name;
    String id;

    public BoardList(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getId() {
        return id;
    }

}
