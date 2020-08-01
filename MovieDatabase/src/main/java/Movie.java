import java.util.ArrayList;

public class Movie {

  private String name;
  private ArrayList<Actor> actors;
  private double rating;

  public Movie(String name, double rating) {
    this.name = name;
    this.rating = rating;
    this.actors = new ArrayList<>();
  }

  public Movie(String name, String[] actors) {
    this.name = name;
    this.actors = new ArrayList<>();
    for (String actor : actors) {
      Actor newActor = new Actor();
      newActor.setName(actor);
      this.actors.add(newActor);
    }
  }

  public String getName() {
    return name.trim();
  }

  public ArrayList<Actor> getActors() {
    return actors;
  }

  public void setActors(Actor actor) {
    this.actors.add(actor);
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
