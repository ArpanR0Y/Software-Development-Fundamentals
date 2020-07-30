import java.util.ArrayList;

public class MovieDatabase {

  private ArrayList<Movie> movieList;
  private ArrayList<Actor> actorList;

  public MovieDatabase() {
    this.movieList = new ArrayList<>();
    this.actorList = new ArrayList<>();
  }

  public ArrayList<Movie> getMovieList() {
    return movieList;
  }

  public ArrayList<Actor> getActorList() {
    return actorList;
  }

  /**
   * This method takes in the name of a movie that is not currently in the database along with a
   * list of actors for that movie. If the movie is already in the database, the code ignores this
   * request (this specification is an oversimplification). If the movie is a new movie, a movie
   * object is created and added to the movieList. If any of the actors happen to be new, they will
   * be added to the actorList.
   *
   * @param name   Name of the movie to be added.
   * @param actors Name of the actor to be added.
   */
  public void addMovie(String name, String[] actors) {
    if (movieList.isEmpty() || movieList.stream()
        .noneMatch(movie -> movie.getName().equals(name))) {
      Movie newMovie = new Movie(name, actors);
      movieList.add(newMovie);
      for (String actor : actors) {
        if (actorList.stream().noneMatch(actor1 -> actor1.getName().equals(actor))) {
          Actor newActor = new Actor();
          newActor.setName(actor);
          newActor.setMovies(newMovie);
          actorList.add(newActor);
        }
      }
    }
  }

  /**
   * Add a rating for this movie. Assume that the name argument will definitely be a name that is
   * currently in the database.
   *
   * @param name   Name of the movie in the Database.
   * @param rating Rating for the movie to be added.
   */
  public void addRating(String name, double rating) {
    for (Movie movie : movieList) {
      if (movie.getName().equals(name)) {
        movie.setRating(rating);
      }
    }
  }

  /**
   * Overwrite the current rating of a movie with this new rating. Again, assume that the name
   * argument will definitely be a name that is currently in the database.
   *
   * @param name      Name of the movie in the Database.
   * @param newRating New rating for the movie, overriding the existing one.
   */
  public void updateRating(String name, double newRating) {
    addRating(name, newRating);
  }

  /**
   * Returns the name of the actor that has the best average rating for their movies.
   *
   * @return The name of the best actor based on the movie ratings.
   */
  public String getBestActor() {
    double maxRating = 0.0;
    String bestActor = "";
    for (Actor actor : actorList) {
      double sumRating = 0.0;
      ArrayList<Movie> actorMovies = actor.getMovies();
      for (Movie movie : actorMovies) {
        sumRating += movie.getRating();
      }
      double avgRating = sumRating / actorMovies.size();
      if (avgRating > maxRating) {
        maxRating = avgRating;
        bestActor = actor.getName();
      }
    }

    return bestActor;
  }

  /**
   * Returns the name of the movie with the highest rating.
   *
   * @return The name of the highest rated movie.
   */
  public String getBestMovie() {
    String bestMovie = "";
//    double highestRating = 0.0;
//    for (Movie movie : movieList) {
//      if (movie.getRating() > highestRating) {
//        bestMovie = movie.getName();
//        highestRating = movie.getRating();
//      }
//    }
    return bestMovie;
  }

  public static void main(String[] args) {
    //TODO: 1. You create a new instance of a movieDatabase.
    //      2. Add all the movies in the file movies.txt.
    //      3. Go through the ratings of the movies in the file ratings.txt and add the ratings for
    //         the movies.
    //      4. Now call the methods that you created and print out the name of the best actor and the
    //         name of the highest rated movie.
  }
}
