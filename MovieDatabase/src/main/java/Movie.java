import java.util.ArrayList;

public class Movie {
  private String name;
  private ArrayList<Actor> actors;
  private double rating;

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
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Actor> getActors() {
    return actors;
  }

  public void setActors(ArrayList<Actor> actors) {
    this.actors = actors;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }
}
