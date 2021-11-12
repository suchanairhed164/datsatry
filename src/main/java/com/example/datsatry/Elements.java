package com.example.datsatry;

public class Elements {
    private int id;
    private int id_sorting;
    private int element;

    public Elements() {

    }

    public Elements(int id, int id_sorting, int element) {
        this.id = id;
        this.id_sorting = id_sorting;
        this.element = element;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSortId() {
        return id_sorting;
    }

    public void setSortId(int sort_name) {
        this.id_sorting = sort_name;
    }

    public int getName() {
        return element;
    }

    public void setName(int sort_name) {
        this.element = sort_name;
    }
}
