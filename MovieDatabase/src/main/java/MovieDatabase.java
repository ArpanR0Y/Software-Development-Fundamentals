import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    double highestRating = 0.0;
    for (Movie movie : movieList) {
      if (movie.getRating() > highestRating) {
        bestMovie = movie.getName();
        highestRating = movie.getRating();
      }
    }
    return bestMovie;
  }

  /**
   * Creates a HashMap of all the Movies and their ratings from the given dataset.
   * @return Map of Movie (key) : Rating (value)
   * @throws FileNotFoundException Throws exception when dataset is not found in the resources.
   */
  private Map<String, Double> movieRatings() throws FileNotFoundException {
    String projectPath = Paths.get(".").normalize().toAbsolutePath().toString();
    Scanner scRatings = new Scanner(new File(projectPath + "\\src\\main\\resources\\ratings.txt"));
    Map<String, Double> movieRatings = new HashMap<>();
    while (scRatings.hasNext()) {
    /*
      Regex pattern to separate the name of the movie from the ratings
      1st group - Captures everything 1 or more times
      2nd group - Captures a whitespace and 2 or more digits at the end of the string
      */
      String regex = "(.+)(\\t+\\d{2,}$)";
      Pattern pattern = Pattern.compile(regex);
      String line = scRatings.nextLine();
      Matcher matcher = pattern.matcher(line);
      String movieName = "";
      double rating = 0.0;

      if (matcher.find()) {
        movieName = matcher.group(1);
        rating = Double.parseDouble(matcher.group(2));
      }
      movieRatings.put(movieName, rating);
    }

    return movieRatings;
  }

  /**
   * This method populates the list of movies, ratings and the actors from the dataset.
   *
   * @throws FileNotFoundException Throws exception when dataset is not found in the resources.
   */
  public void dataLoader() throws FileNotFoundException {

    String projectPath = Paths.get(".").normalize().toAbsolutePath().toString();
    Scanner scMovies = new Scanner(new File(projectPath + "\\src\\main\\resources\\movies.txt"));
    Scanner scRatings = new Scanner(new File(projectPath + "\\src\\main\\resources\\ratings.txt"));

    //Populate List of Actors in the MovieDatabase
    while (scMovies.hasNext()) {
      String line = scMovies.nextLine();

      String actor = line.substring(0, line.indexOf(","));
      String movies = line.substring(line.indexOf(",") + 1);

      //Create a new Actor object
      Actor newActor = new Actor();
      newActor.setName(actor);

      //Add all the movies the actor has acted in Actor.movies
      String[] movieArr = movies.split(",");

      for (String movie : movieArr) {
        double rating = 0.0;
        //Handles cases where the movie ratings does not exist.
        if (movieRatings().get(movie.trim()) != null) {
          rating = movieRatings().get(movie.trim());
        }
        Movie newMovie = new Movie(movie, rating);
        newActor.setMovies(newMovie);
      }

      this.actorList.add(newActor);
    }

    //Populate List of Movies in the MovieDatabase
    for (String movieName : movieRatings().keySet()) {

      //Create a new Movie object
      Movie movie = new Movie(movieName, movieRatings().get(movieName));
      for (Actor actor : actorList) {
        if (actor.getMovies().stream()
            .anyMatch(actorMovie -> actorMovie.getName().equals(movieName))) {
          movie.setActors(actor);
        }
      }

      this.movieList.add(movie);
    }

    scMovies.close();
    scRatings.close();
  }

  /**
   * Prints the names of the all the movies, the cast and the movie rating.
   */
  private void getAllMovieDetails() {
    int n = 0;
    while (n < this.movieList.size()) {
      Movie movie = this.movieList.get(n);
      System.out.println("Movie Name: " + movie.getName());
      for (int i = 0; i < movie.getActors().size(); i++) {
        System.out.println("Movie Actor" + (i + 1) + ":" + movie.getActors().get(i).getName());
      }
      System.out.println("Movie Rating: " + movie.getRating());
      n++;
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    MovieDatabase movieDatabase = new MovieDatabase();
    movieDatabase.dataLoader();

    while (true) {
      System.out.println("WELCOME TO ROTTEN POTATOES");
      System.out.println("----------------------------");
      System.out.println("1. View all the movies.");
      System.out.println("2. View the highest rated movie.");
      System.out.println("3. View the highest rated actor.");
      System.out.println("0. Quit");
      System.out.println("----------------------------");
      switch (scanner.nextInt()) {
        case 1:
          movieDatabase.getAllMovieDetails();
          System.out.println();
          break;
        case 2:
          System.out.println("Highest Rated Movie: " + movieDatabase.getBestMovie());
          System.out.println();
          break;
        case 3:
          System.out.println("Highest Rated Actor: " + movieDatabase.getBestActor());
          System.out.println();
          break;
        case 0:
          return;
        default:
          System.out.println("Invalid Option.");
          System.out.println();
          break;
      }
    }
  }
}
