package ue.edu.co.retrofitbotton.model;

public class Pokemon {
    private String name;
    private String url;

    public String getName(){ return name; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
